package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.FornecedorDTO;
import br.com.sistemaerp.entity.Fornecedor;
import br.com.sistemaerp.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;


    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {

        return ResponseEntity.ok(fornecedorService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(
            @RequestBody @Valid FornecedorDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fornecedorService.cadastrar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FornecedorDTO dto
    ) {

        return ResponseEntity.ok(
                fornecedorService.atualizar(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        fornecedorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
