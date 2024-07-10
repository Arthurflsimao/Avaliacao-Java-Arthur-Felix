package br.com.avaliacao.AvaliacaoJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.AvaliacaoJava.dto.PessoaMalaDiretaDTO;
import br.com.avaliacao.AvaliacaoJava.model.Pessoa;
import br.com.avaliacao.AvaliacaoJava.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> obterPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> listarTodasAsPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setEndereco(pessoaAtualizada.getEndereco());
            pessoa.setCep(pessoaAtualizada.getCep());
            pessoa.setCidade(pessoaAtualizada.getCidade());
            pessoa.setUf(pessoaAtualizada.getUf());
            return pessoaRepository.save(pessoa);
        } else {
            return null;
        }
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Optional<PessoaMalaDiretaDTO> obterPessoaMalaDiretaPorId(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(this::convertToMalaDiretaDTO);
    }

    private PessoaMalaDiretaDTO convertToMalaDiretaDTO(Pessoa pessoa) {
        String malaDireta = String.format("%s, %s – CEP: %s – %s/%s",
                pessoa.getEndereco(),
                pessoa.getId(),
                pessoa.getCep(),
                pessoa.getCidade(),
                pessoa.getUf());
        return new PessoaMalaDiretaDTO(pessoa.getId(), pessoa.getNome(), malaDireta);
    }
}