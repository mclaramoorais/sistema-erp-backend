package br.com.sistemaerp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "venda_id",
            nullable = false
    )
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "produto_id",
            nullable = false
    )
    private Produto produto;

}
