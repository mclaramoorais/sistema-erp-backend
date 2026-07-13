package br.com.sistemaerp.security;

import br.com.sistemaerp.entity.Usuario;
import br.com.sistemaerp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {


        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Usuário não encontrado"
                        )
                );


        return usuario;
    }

}
