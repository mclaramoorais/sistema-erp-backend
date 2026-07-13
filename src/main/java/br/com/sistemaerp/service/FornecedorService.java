package br.com.sistemaerp.service;

import br.com.sistemaerp.dto.FornecedorDTO;
import br.com.sistemaerp.entity.Fornecedor;
import br.com.sistemaerp.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;


    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }


    public Fornecedor buscarPorId(Long id) {

        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }


    public Fornecedor cadastrar(FornecedorDTO dto) {

        Fornecedor fornecedor = Fornecedor.builder()
                .nome(dto.getNome())
                .cnpj(dto.getCnpj())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .contato(dto.getContato())
                .endereco(dto.getEndereco())
                .build();

        return fornecedorRepository.save(fornecedor);
    }


    public Fornecedor atualizar(Long id, FornecedorDTO dto) {

        Fornecedor fornecedor = buscarPorId(id);

        fornecedor.setNome(dto.getNome());
        fornecedor.setCnpj(dto.getCnpj());
        fornecedor.setTelefone(dto.getTelefone());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setContato(dto.getContato());
        fornecedor.setEndereco(dto.getEndereco());

        return fornecedorRepository.save(fornecedor);
    }


    public void excluir(Long id) {

        Fornecedor fornecedor = buscarPorId(id);

        fornecedorRepository.delete(fornecedor);
    }

}
