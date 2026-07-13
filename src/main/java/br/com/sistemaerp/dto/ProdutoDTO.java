package br.com.sistemaerp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "O código do produto é obrigatório")
    private String codigo;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    private BigDecimal preco;

    private BigDecimal custo;

    @NotNull(message = "A quantidade do produto é obrigatória")
    private Integer quantidade;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;

    private Long fornecedorId;

}
