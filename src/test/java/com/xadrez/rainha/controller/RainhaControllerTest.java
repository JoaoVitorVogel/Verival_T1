package com.xadrez.rainha.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xadrez.rainha.model.PosicaoRainha;
import com.xadrez.rainha.service.RainhaService;

@WebMvcTest(RainhaController.class)
public class RainhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RainhaService rainhaService;

    @Test
    public void testCalcularMovimentos() throws Exception {
        // Configuração do mock
        when(rainhaService.calcularMovimentosMinimos(4, 4, 6, 2)).thenReturn(1);

        // Criação do objeto de requisição
        PosicaoRainha posicao = new PosicaoRainha(4, 4, 6, 2);

        // Execução do teste
        mockMvc.perform(post("/api/rainha/movimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posicao)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void testCalcularMovimentosMesmaPosicao() throws Exception {
        // Configuração do mock
        when(rainhaService.calcularMovimentosMinimos(3, 5, 3, 5)).thenReturn(0);

        // Criação do objeto de requisição
        PosicaoRainha posicao = new PosicaoRainha(3, 5, 3, 5);

        // Execução do teste
        mockMvc.perform(post("/api/rainha/movimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posicao)))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void testCalcularMovimentosDoisPassos() throws Exception {
        // Configuração do mock
        when(rainhaService.calcularMovimentosMinimos(5, 5, 4, 3)).thenReturn(2);

        // Criação do objeto de requisição
        PosicaoRainha posicao = new PosicaoRainha(5, 5, 4, 3);

        // Execução do teste
        mockMvc.perform(post("/api/rainha/movimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posicao)))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
} 