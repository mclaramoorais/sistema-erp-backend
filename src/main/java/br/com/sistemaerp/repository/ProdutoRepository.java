package br.com.sistemaerp.repository;

import br.com.sistemaerp.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigo(String codigo);

    Optional<Produto> findByNome(String nome);

    boolean existsByCodigo(String codigo);

}
