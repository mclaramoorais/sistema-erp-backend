package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.CategoriaDTO;
import br.com.sistemaerp.entity.Categoria;
import br.com.sistemaerp.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }


    public Categoria buscarPorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }


    public Categoria cadastrar(CategoriaDTO dto) {

        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        return categoriaRepository.save(categoria);
    }


    public Categoria atualizar(Long id, CategoriaDTO dto) {

        Categoria categoria = buscarPorId(id);

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaRepository.save(categoria);
    }


    public void excluir(Long id) {

        Categoria categoria = buscarPorId(id);

        categoriaRepository.delete(categoria);
    }

}
