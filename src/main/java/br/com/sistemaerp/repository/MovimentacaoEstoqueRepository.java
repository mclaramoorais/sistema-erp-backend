package br.com.sistemaerp.repository;

import br.com.sistemaerp.entity.MovimentacaoEstoque;
import br.com.sistemaerp.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    List<MovimentacaoEstoque> findByProduto(Produto produto);

    List<MovimentacaoEstoque> findByTipo(String tipo);

}
