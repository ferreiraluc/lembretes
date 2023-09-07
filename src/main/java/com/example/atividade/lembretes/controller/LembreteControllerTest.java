package com.example.atividade.lembretes.controller;

import com.example.atividade.lembretes.entity.Lembrete;
import com.example.atividade.lembretes.service.LembreteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class LembreteControllerTest {

    @InjectMocks
    private LembreteController lembreteController;

    @Mock
    private LembreteService lembreteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLembreteTest_Found() {
        Lembrete lembreteMock = new Lembrete();
        when(lembreteService.findById(1L)).thenReturn(Optional.of(lembreteMock));

        ResponseEntity<Lembrete> response = lembreteController.getLembrete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lembreteMock, response.getBody());
    }

    @Test
    void getLembreteTest_NotFound() {
        when(lembreteService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Lembrete> response = lembreteController.getLembrete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void cadastrarLembreteTest_Success() {
        Lembrete lembreteMock = new Lembrete();

        ResponseEntity<String> response = lembreteController.cadastrarLembrete(lembreteMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lembrete cadastrado com sucesso!", response.getBody());
    }

}

