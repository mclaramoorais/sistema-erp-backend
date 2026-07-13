package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.UsuarioDTO;
import br.com.sistemaerp.entity.Usuario;
import br.com.sistemaerp.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {

        return ResponseEntity.ok(usuarioService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Usuario> cadastrar(
            @RequestBody @Valid UsuarioDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.cadastrar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioDTO dto
    ) {

        return ResponseEntity.ok(
                usuarioService.atualizar(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        usuarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
