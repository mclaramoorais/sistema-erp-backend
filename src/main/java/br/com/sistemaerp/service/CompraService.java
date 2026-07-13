package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.CompraDTO;
import br.com.sistemaerp.dto.ItemCompraDTO;
import br.com.sistemaerp.entity.*;
import br.com.sistemaerp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;


    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }


    public Compra buscarPorId(Long id) {

        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }


    public Compra cadastrar(CompraDTO dto) {

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));


        Compra compra = Compra.builder()
                .fornecedor(fornecedor)
                .itens(new ArrayList<>())
                .valorTotal(BigDecimal.ZERO)
                .build();


        BigDecimal valorTotal = BigDecimal.ZERO;


        for (ItemCompraDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));


            ItemCompra item = ItemCompra.builder()
                    .produto(produto)
                    .quantidade(itemDTO.getQuantidade())
                    .valorUnitario(itemDTO.getValorUnitario())
                    .compra(compra)
                    .build();


            compra.getItens().add(item);


            valorTotal = valorTotal.add(
                    itemDTO.getValorUnitario()
                            .multiply(
                                    BigDecimal.valueOf(itemDTO.getQuantidade())
                            )
            );


            movimentacaoEstoqueService.registrar(
                    produto.getId(),
                    "ENTRADA",
                    itemDTO.getQuantidade()
            );
        }


        compra.setValorTotal(valorTotal);

        return compraRepository.save(compra);
    }


    public void excluir(Long id) {

        Compra compra = buscarPorId(id);

        compraRepository.delete(compra);
    }

}
