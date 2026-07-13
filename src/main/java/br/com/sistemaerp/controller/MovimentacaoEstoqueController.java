package br.com.sistemaerp.controller;

import br.com.sistemaerp.entity.MovimentacaoEstoque;
import br.com.sistemaerp.service.MovimentacaoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;


    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoque>> listarTodas() {

        return ResponseEntity.ok(
                movimentacaoEstoqueService.listarTodas()
        );
    }


    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<MovimentacaoEstoque>> listarPorProduto(
            @PathVariable Long produtoId
    ) {

        return ResponseEntity.ok(
                movimentacaoEstoqueService.listarPorProduto(produtoId)
        );
    }


    @PostMapping
    public ResponseEntity<MovimentacaoEstoque> registrar(
            @RequestParam Long produtoId,
            @RequestParam String tipo,
            @RequestParam Integer quantidade
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        movimentacaoEstoqueService.registrar(
                                produtoId,
                                tipo,
                                quantidade
                        )
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        movimentacaoEstoqueService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
