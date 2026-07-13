package br.com.sistemaerp.controller;

import br.com.sistemaerp.dto.LoginRequestDTO;
import br.com.sistemaerp.dto.LoginResponseDTO;
import br.com.sistemaerp.entity.Usuario;
import br.com.sistemaerp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto
    ) {

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }


        LoginResponseDTO response = LoginResponseDTO.builder()
                .token("TOKEN_JWT_GERADO_AQUI")
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getRole().getNome())
                .build();


        return ResponseEntity.ok(response);
    }

}
