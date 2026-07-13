package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.ProdutoDTO;
import br.com.sistemaerp.entity.Produto;
import br.com.sistemaerp.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;


    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {

        return ResponseEntity.ok(produtoService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Produto> cadastrar(
            @RequestBody @Valid ProdutoDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.cadastrar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoDTO dto
    ) {

        return ResponseEntity.ok(
                produtoService.atualizar(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
