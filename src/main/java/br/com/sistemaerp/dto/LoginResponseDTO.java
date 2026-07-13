package br.com.sistemaerp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String token;

    private Long id;

    private String nome;

    private String email;

    private String perfil;

}
