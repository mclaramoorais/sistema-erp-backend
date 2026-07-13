package br.com.sistemaerp.repository;

import br.com.sistemaerp.entity.Cliente;
import br.com.sistemaerp.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByCliente(Cliente cliente);

    List<Venda> findByDataVendaBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

}
