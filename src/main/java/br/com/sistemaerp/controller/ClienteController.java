package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.ClienteDTO;
import br.com.sistemaerp.entity.Cliente;
import br.com.sistemaerp.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {

        return ResponseEntity.ok(clienteService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Cliente> cadastrar(
            @RequestBody @Valid ClienteDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.cadastrar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteDTO dto
    ) {

        return ResponseEntity.ok(
                clienteService.atualizar(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
