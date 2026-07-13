package br.com.sistemaerp.service;

import br.com.sistemaerp.entity.MovimentacaoEstoque;
import br.com.sistemaerp.entity.Produto;
import br.com.sistemaerp.repository.MovimentacaoEstoqueRepository;
import br.com.sistemaerp.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;


    public List<MovimentacaoEstoque> listarTodas() {
        return movimentacaoEstoqueRepository.findAll();
    }


    public List<MovimentacaoEstoque> listarPorProduto(Long produtoId) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return movimentacaoEstoqueRepository.findByProduto(produto);
    }


    public MovimentacaoEstoque registrar(
            Long produtoId,
            String tipo,
            Integer quantidade
    ) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));


        if (tipo.equalsIgnoreCase("ENTRADA")) {

            produto.setQuantidade(
                    produto.getQuantidade() + quantidade
            );

        } else if (tipo.equalsIgnoreCase("SAIDA")) {

            if (produto.getQuantidade() < quantidade) {
                throw new RuntimeException("Estoque insuficiente");
            }

            produto.setQuantidade(
                    produto.getQuantidade() - quantidade
            );

        } else {
            throw new RuntimeException("Tipo de movimentação inválido");
        }


        produtoRepository.save(produto);


        MovimentacaoEstoque movimentacao = MovimentacaoEstoque.builder()
                .tipo(tipo.toUpperCase())
                .quantidade(quantidade)
                .produto(produto)
                .build();


        return movimentacaoEstoqueRepository.save(movimentacao);
    }


    public void excluir(Long id) {

        MovimentacaoEstoque movimentacao = movimentacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacaoEstoqueRepository.delete(movimentacao);
    }

}
