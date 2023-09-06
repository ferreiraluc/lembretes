package com.example.atividade.lembretes.controller;

import com.example.atividade.lembretes.entity.Lembrete;
import com.example.atividade.lembretes.service.LembreteService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/lembrete")
public class LembreteController {

    private final LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> getLembrete(@PathVariable Long id) {
        return lembreteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> cadastrarLembrete(@RequestBody Lembrete lembrete) {
        try {
            lembreteService.cadastrar(lembrete);
            return ResponseEntity.ok("Lembrete cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o lembrete.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarLembrete(@PathVariable @NotNull Long id, @RequestBody Lembrete lembrete) {
        try {
            lembreteService.atualizar(id, lembrete);
            return ResponseEntity.ok("Lembrete atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o lembrete.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirLembrete(@PathVariable @NotNull Long id) {
        try {
            lembreteService.excluir(id);
            return ResponseEntity.ok("Lembrete excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o lembrete.");
        }
    }
}
