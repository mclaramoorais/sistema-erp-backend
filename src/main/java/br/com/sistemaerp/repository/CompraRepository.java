package br.com.sistemaerp.repository;

import br.com.sistemaerp.entity.Compra;
import br.com.sistemaerp.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByFornecedor(Fornecedor fornecedor);

    List<Compra> findByDataCompraBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

}
