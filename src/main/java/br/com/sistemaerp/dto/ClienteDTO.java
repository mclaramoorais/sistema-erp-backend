package br.com.sistemaerp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;

    private String cpfCnpj;

    private String telefone;

    @Email(message = "Informe um e-mail válido")
    private String email;

    private String endereco;

    private String cidade;

    private String estado;

}
