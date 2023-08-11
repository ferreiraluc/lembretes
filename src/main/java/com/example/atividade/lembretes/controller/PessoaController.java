package com.example.atividade.lembretes.controller;
import com.example.atividade.lembretes.entity.Pessoa;
import com.example.atividade.lembretes.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lembrete/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        return pessoa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<Pessoa>> getPessoasByAtivo(@PathVariable boolean ativo) {
        List<Pessoa> pessoasAtivos = pessoaService.findByAtivo(ativo);
        return ResponseEntity.ok(pessoasAtivos);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoaService.cadastrar(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        try {
            pessoaService.atualizar(id, pessoa);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o registro.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPessoa(@PathVariable Long id) {
        try {
            pessoaService.excluir(id);
            return ResponseEntity.ok("Pessoa excluída com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a pessoa.");
        }
    }
}