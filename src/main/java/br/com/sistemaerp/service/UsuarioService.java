package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.UsuarioDTO;
import br.com.sistemaerp.entity.Role;
import br.com.sistemaerp.entity.Usuario;
import br.com.sistemaerp.repository.RoleRepository;
import br.com.sistemaerp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }


    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


    public Usuario cadastrar(UsuarioDTO dto) {

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));


        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .role(role)
                .ativo(true)
                .build();


        return usuarioRepository.save(usuario);
    }


    public Usuario atualizar(Long id, UsuarioDTO dto) {

        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        if (dto.getRoleId() != null) {

            Role role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

            usuario.setRole(role);
        }

        return usuarioRepository.save(usuario);
    }


    public void excluir(Long id) {

        Usuario usuario = buscarPorId(id);

        usuarioRepository.delete(usuario);
    }

}
