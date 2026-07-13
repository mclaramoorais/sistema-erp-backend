package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.ClienteDTO;
import br.com.sistemaerp.entity.Cliente;
import br.com.sistemaerp.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }


    public Cliente buscarPorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }


    public Cliente cadastrar(ClienteDTO dto) {

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .cpfCnpj(dto.getCpfCnpj())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .endereco(dto.getEndereco())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .build();

        return clienteRepository.save(cliente);
    }


    public Cliente atualizar(Long id, ClienteDTO dto) {

        Cliente cliente = buscarPorId(id);

        cliente.setNome(dto.getNome());
        cliente.setCpfCnpj(dto.getCpfCnpj());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCidade(dto.getCidade());
        cliente.setEstado(dto.getEstado());

        return clienteRepository.save(cliente);
    }


    public void excluir(Long id) {

        Cliente cliente = buscarPorId(id);

        clienteRepository.delete(cliente);
    }

}
