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
public class VendaDTO {

    private Long id;

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;

    private BigDecimal valorTotal;

    @NotEmpty(message = "A venda deve possuir pelo menos um item")
    private List<ItemVendaDTO> itens;

}
