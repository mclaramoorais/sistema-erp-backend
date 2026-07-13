package br.com.sistemaerp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCompraDTO {

    private Long id;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    @NotNull(message = "O valor unitário é obrigatório")
    private BigDecimal valorUnitario;

}
