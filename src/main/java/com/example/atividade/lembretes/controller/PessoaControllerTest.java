package com.example.atividade.lembretes.controller;

import com.example.atividade.lembretes.entity.Pessoa;
import com.example.atividade.lembretes.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPessoaTest() {
        Pessoa pessoaMock = new Pessoa();
        when(pessoaService.findById(1L)).thenReturn(Optional.of(pessoaMock));

        ResponseEntity<Pessoa> result = pessoaController.getPessoa(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pessoaMock, result.getBody());

        verify(pessoaService, times(1)).findById(1L);
    }

    @Test
    void getPessoasByAtivoTest() {
        Pessoa pessoaMock = new Pessoa();
        when(pessoaService.findByAtivo(true)).thenReturn(Collections.singletonList(pessoaMock));

        ResponseEntity<List<Pessoa>> result = pessoaController.getPessoasByAtivo(true);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());

        verify(pessoaService, times(1)).findByAtivo(true);
    }

}
