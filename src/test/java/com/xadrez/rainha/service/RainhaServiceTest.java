package com.xadrez.rainha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RainhaServiceTest {

    @Autowired
    private RainhaService rainhaService;
    
    @Test
    public void testMesmaPosicao() {
        // Quando a rainha já está na posição de destino
        assertEquals(0, rainhaService.calcularMovimentosMinimos(3, 5, 3, 5));
    }
    
    @Test
    public void testMovimentoDiagonal() {
        // Quando a rainha pode se mover na diagonal (1 movimento)
        assertEquals(1, rainhaService.calcularMovimentosMinimos(4, 4, 6, 2));
    }
    
    @Test
    public void testMovimentoLinha() {
        // Quando a rainha pode se mover na mesma linha (1 movimento)
        assertEquals(1, rainhaService.calcularMovimentosMinimos(3, 5, 3, 8));
    }
    
    @Test
    public void testMovimentoColuna() {
        // Quando a rainha pode se mover na mesma coluna (1 movimento)
        assertEquals(1, rainhaService.calcularMovimentosMinimos(3, 5, 7, 5));
    }
    
    @Test
    public void testDoisMovimentos() {
        // Quando são necessários 2 movimentos (movimento em L)
        assertEquals(2, rainhaService.calcularMovimentosMinimos(5, 5, 4, 3));
    }
    
    @Test
    public void testExemploEntrada() {
        // Testes de exemplo da entrada
        assertEquals(1, rainhaService.calcularMovimentosMinimos(4, 4, 6, 2));
        assertEquals(0, rainhaService.calcularMovimentosMinimos(3, 5, 3, 5));
        assertEquals(2, rainhaService.calcularMovimentosMinimos(5, 5, 4, 3));
    }
} 