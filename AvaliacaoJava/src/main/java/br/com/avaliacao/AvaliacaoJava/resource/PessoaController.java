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

import br.com.avaliacao.AvaliacaoJava.dto.PessoaMalaDiretaDTO;
import br.com.avaliacao.AvaliacaoJava.model.Pessoa;
import br.com.avaliacao.AvaliacaoJava.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Criar nova pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.ok(novaPessoa);
    }

    @Operation(summary = "Retorne os dados de uma pessoa pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.obterPessoaPorId(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Retorne os dados de uma pessoa por ID para mala direta")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<PessoaMalaDiretaDTO> obterPessoaMalaDiretaPorId(@PathVariable Long id) {
        Optional<PessoaMalaDiretaDTO> pessoaMalaDiretaDTO = pessoaService.obterPessoaMalaDiretaPorId(id);
        return pessoaMalaDiretaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todas as pessoas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasAsPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasAsPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Atualizar uma pessoa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        Pessoa pessoa = pessoaService.atualizarPessoa(id, pessoaAtualizada);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remover uma pessoa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}