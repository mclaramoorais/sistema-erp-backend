package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.ProdutoDTO;
import br.com.sistemaerp.entity.Categoria;
import br.com.sistemaerp.entity.Fornecedor;
import br.com.sistemaerp.entity.Produto;
import br.com.sistemaerp.repository.CategoriaRepository;
import br.com.sistemaerp.repository.FornecedorRepository;
import br.com.sistemaerp.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;


    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }


    public Produto buscarPorId(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }


    public Produto cadastrar(ProdutoDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));


        Fornecedor fornecedor = null;

        if (dto.getFornecedorId() != null) {
            fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        }


        Produto produto = Produto.builder()
                .codigo(dto.getCodigo())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .custo(dto.getCusto())
                .quantidade(dto.getQuantidade())
                .categoria(categoria)
                .fornecedor(fornecedor)
                .build();


        return produtoRepository.save(produto);
    }


    public Produto atualizar(Long id, ProdutoDTO dto) {

        Produto produto = buscarPorId(id);


        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));


        produto.setCodigo(dto.getCodigo());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCusto(dto.getCusto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setCategoria(categoria);


        if (dto.getFornecedorId() != null) {

            Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

            produto.setFornecedor(fornecedor);
        }


        return produtoRepository.save(produto);
    }


    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }

}
