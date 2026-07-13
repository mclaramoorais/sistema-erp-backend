package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.CategoriaDTO;
import br.com.sistemaerp.entity.Categoria;
import br.com.sistemaerp.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos() {

        return ResponseEntity.ok(categoriaService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Categoria> cadastrar(
            @RequestBody @Valid CategoriaDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.cadastrar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaDTO dto
    ) {

        return ResponseEntity.ok(
                categoriaService.atualizar(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
