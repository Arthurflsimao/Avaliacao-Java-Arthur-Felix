package br.com.avaliacao.AvaliacaoJava.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.AvaliacaoJava.model.Contato;
import br.com.avaliacao.AvaliacaoJava.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Operation(summary = "Adicionar um novo contato a uma pessoa")
    @PostMapping
    public Contato adicionarContato(@RequestBody Contato contato) {
        return contatoService.adicionarContato(contato);
    }

    @Operation(summary = "Retorne os dados de um contato pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Contato> obterContatoPorId(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.obterContatoPorId(id);
        return contato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todos os contatos de uma Pessoa")
    @GetMapping("/pessoa/{idPessoa}")
    public List<Contato> listarContatosPorPessoaId(@PathVariable Long idPessoa) {
        return contatoService.listarContatosPorPessoaId(idPessoa);
    }

    @Operation(summary = "Atualizar um contato existente")
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contatoAtualizado) {
        Contato contato = contatoService.atualizarContato(id, contatoAtualizado);
        if (contato != null) {
            return ResponseEntity.ok(contato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um Contato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatoService.deletarContato(id);
        return ResponseEntity.noContent().build();
    }
}