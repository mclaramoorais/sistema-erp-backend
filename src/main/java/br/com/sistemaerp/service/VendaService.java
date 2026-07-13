package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.ItemVendaDTO;
import br.com.sistemaerp.dto.VendaDTO;
import br.com.sistemaerp.entity.*;
import br.com.sistemaerp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;


    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }


    public Venda buscarPorId(Long id) {

        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }


    public Venda cadastrar(VendaDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        Venda venda = Venda.builder()
                .cliente(cliente)
                .itens(new ArrayList<>())
                .valorTotal(BigDecimal.ZERO)
                .build();


        BigDecimal valorTotal = BigDecimal.ZERO;


        for (ItemVendaDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));


            if (produto.getQuantidade() < itemDTO.getQuantidade()) {
                throw new RuntimeException(
                        "Quantidade insuficiente em estoque para o produto: "
                                + produto.getNome()
                );
            }


            ItemVenda item = ItemVenda.builder()
                    .produto(produto)
                    .quantidade(itemDTO.getQuantidade())
                    .valorUnitario(itemDTO.getValorUnitario())
                    .venda(venda)
                    .build();


            venda.getItens().add(item);


            valorTotal = valorTotal.add(
                    itemDTO.getValorUnitario()
                            .multiply(
                                    BigDecimal.valueOf(itemDTO.getQuantidade())
                            )
            );


            movimentacaoEstoqueService.registrar(
                    produto.getId(),
                    "SAIDA",
                    itemDTO.getQuantidade()
            );
        }


        venda.setValorTotal(valorTotal);

        return vendaRepository.save(venda);
    }


    public void excluir(Long id) {

        Venda venda = buscarPorId(id);

        vendaRepository.delete(venda);
    }

}
