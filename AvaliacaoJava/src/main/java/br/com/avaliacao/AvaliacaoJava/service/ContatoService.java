package br.com.avaliacao.AvaliacaoJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.AvaliacaoJava.model.Contato;
import br.com.avaliacao.AvaliacaoJava.repository.ContatoRepository;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato adicionarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Optional<Contato> obterContatoPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public List<Contato> listarContatosPorPessoaId(Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }

    public Contato atualizarContato(Long id, Contato contatoAtualizado) {
        Optional<Contato> contatoExistente = contatoRepository.findById(id);
        if (contatoExistente.isPresent()) {
            Contato contato = contatoExistente.get();
            contato.setTipoContato(contatoAtualizado.getTipoContato());
            contato.setContato(contatoAtualizado.getContato());
            return contatoRepository.save(contato);
        } else {
            return null;
        }
    }

    public void deletarContato(Long id) {
        contatoRepository.deleteById(id);
    }
}
