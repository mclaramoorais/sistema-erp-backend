package br.com.sistemaerp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {

    private Long id;

    @NotNull(message = "O fornecedor é obrigatório")
    private Long fornecedorId;

    private BigDecimal valorTotal;

    @NotEmpty(message = "A compra deve possuir pelo menos um item")
    private List<ItemCompraDTO> itens;

}
