package br.com.sistemaerp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "O nome do fornecedor é obrigatório")
    private String nome;

    private String cnpj;

    private String telefone;

    @Email(message = "Informe um e-mail válido")
    private String email;

    private String contato;

    private String endereco;

}
