package br.com.sistemaerp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "compra_id",
            nullable = false
    )
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "produto_id",
            nullable = false
    )
    private Produto produto;

}
