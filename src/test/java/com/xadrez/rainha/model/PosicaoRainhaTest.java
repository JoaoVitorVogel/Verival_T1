package com.xadrez.rainha.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PosicaoRainhaTest {

    @Test
    public void testConstrutorVazio() {
        PosicaoRainha posicao = new PosicaoRainha();
        assertEquals(0, posicao.getX1());
        assertEquals(0, posicao.getY1());
        assertEquals(0, posicao.getX2());
        assertEquals(0, posicao.getY2());
    }
    
    @Test
    public void testConstrutorComParametros() {
        PosicaoRainha posicao = new PosicaoRainha(4, 4, 6, 2);
        assertEquals(4, posicao.getX1());
        assertEquals(4, posicao.getY1());
        assertEquals(6, posicao.getX2());
        assertEquals(2, posicao.getY2());
    }
    
    @Test
    public void testSettersGetters() {
        PosicaoRainha posicao = new PosicaoRainha();
        
        posicao.setX1(3);
        posicao.setY1(5);
        posicao.setX2(3);
        posicao.setY2(5);
        
        assertEquals(3, posicao.getX1());
        assertEquals(5, posicao.getY1());
        assertEquals(3, posicao.getX2());
        assertEquals(5, posicao.getY2());
    }
}