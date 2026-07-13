package br.com.sistemaerp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(precision = 10, scale = 2)
    private BigDecimal custo;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "categoria_id",
            nullable = false
    )
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "fornecedor_id"
    )
    private Fornecedor fornecedor;

}
