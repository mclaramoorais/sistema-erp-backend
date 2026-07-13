package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.VendaDTO;
import br.com.sistemaerp.entity.Venda;
import br.com.sistemaerp.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;


    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {

        return ResponseEntity.ok(
                vendaService.listarTodas()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                vendaService.buscarPorId(id)
        );
    }


    @PostMapping
    public ResponseEntity<Venda> cadastrar(
            @RequestBody @Valid VendaDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        vendaService.cadastrar(dto)
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        vendaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
