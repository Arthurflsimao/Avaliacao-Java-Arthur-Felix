package br.com.avaliacao.AvaliacaoJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.AvaliacaoJava.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByPessoaId(Long pessoaId);
}
