package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.CompraDTO;
import br.com.sistemaerp.entity.Compra;
import br.com.sistemaerp.service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;


    @GetMapping
    public ResponseEntity<List<Compra>> listarTodas() {

        return ResponseEntity.ok(
                compraService.listarTodas()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                compraService.buscarPorId(id)
        );
    }


    @PostMapping
    public ResponseEntity<Compra> cadastrar(
            @RequestBody @Valid CompraDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        compraService.cadastrar(dto)
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        compraService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
