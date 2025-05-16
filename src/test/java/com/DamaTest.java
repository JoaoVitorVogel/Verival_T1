package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DamaTest {

    @Nested
    @DisplayName("Testes de validação de entrada e parsing")
    class ValidacaoParseTests {

        @Test
        @DisplayName("parseEntrada: entrada válida retorna array correto")
        void testParseEntradaValida() {
            assertArrayEquals(new int[]{1, 2, 3, 4}, Dama.parseEntrada("1 2 3 4"));
            assertArrayEquals(new int[]{8, 7, 6, 5}, Dama.parseEntrada("8 7 6 5"));
            assertArrayEquals(new int[]{8, 1, 8, 1}, Dama.parseEntrada("8 1 8 1"));
            assertArrayEquals(new int[]{0, 0, 0, 0}, Dama.parseEntrada("0 0 0 0"));  // condição de parada válida
        }

        @Test
        @DisplayName("parseEntrada: entradas inválidas retornam null")
        void testParseEntradaInvalida() {
            assertNull(Dama.parseEntrada(null));
            assertNull(Dama.parseEntrada(""));
            assertNull(Dama.parseEntrada("1 2 3"));             // menos de 4 valores
            assertNull(Dama.parseEntrada("1 2 3 4 5"));         // mais de 4 valores
            assertNull(Dama.parseEntrada("1 2 três 4"));        // palavra inválida
            assertNull(Dama.parseEntrada("1 2 0 4"));           // zero não permitido aqui
            assertNull(Dama.parseEntrada("1 0 1 1"));           // zero não permitido
            assertNull(Dama.parseEntrada("0 1 1 1"));           // zero não permitido
            assertNull(Dama.parseEntrada("1 2 9 4"));           // valor fora do intervalo
            assertNull(Dama.parseEntrada("1 2 -1 4"));          // valor negativo
            assertNull(Dama.parseEntrada("1 2 8 9"));           // valor fora do intervalo
        }

        @Test
        @DisplayName("validarValores: valores dentro do intervalo retornam true (1-8) e condição de parada (0 0 0 0)")
        void testValidarValoresValido() {
            assertTrue(Dama.validarValores(new int[] {0,0,0,0})); // parada
            assertTrue(Dama.validarValores(new int[] {1, 1, 1, 1}));
            assertTrue(Dama.validarValores(new int[] {1, 5, 8, 3}));
            assertTrue(Dama.validarValores(new int[] {8, 8, 8, 8}));
        }

        @Test
        @DisplayName("validarValores: valores fora do intervalo ou zeros isolados retornam false")
        void testValidarValoresInvalido() {
            assertFalse(Dama.validarValores(new int[] {0, 0, 0}));   // tamanho errado
            assertFalse(Dama.validarValores(new int[] {0, 0, 1, 1})); // zero isolado
            assertFalse(Dama.validarValores(new int[] {1, 0, 1, 1})); // zero isolado
            assertFalse(Dama.validarValores(new int[] {1, 2, 3, 0})); // zero isolado
            assertFalse(Dama.validarValores(null));
            assertFalse(Dama.validarValores(new int[] {1, 2, 3, 4, 5}));
            assertFalse(Dama.validarValores(new int[] {9, 1, 1, 1}));
            assertFalse(Dama.validarValores(new int[] {-1, 1, 1, 1}));
        }
    }

    @Nested
    @DisplayName("Testes da lógica de movimento da dama")
    class MovimentoTests {

        @Test
        @DisplayName("Movimentos: mesma casa retorna 0")
        void testMesmaCasa() {
            assertEquals(0, Dama.menorNumeroMovimentos(3,3,3,3));
            assertEquals(0, Dama.menorNumeroMovimentos(1,1,1,1));
            assertEquals(0, Dama.menorNumeroMovimentos(8,8,8,8));
        }

        @Test
        @DisplayName("Movimentos: mesma linha retorna 1")
        void testMesmaLinha() {
            assertEquals(1, Dama.menorNumeroMovimentos(5,3,5,7));
            assertEquals(1, Dama.menorNumeroMovimentos(1,1,1,8));
        }

        @Test
        @DisplayName("Movimentos: mesma coluna retorna 1")
        void testMesmaColuna() {
            assertEquals(1, Dama.menorNumeroMovimentos(3,4,7,4));
            assertEquals(1, Dama.menorNumeroMovimentos(8,1,1,1));
        }

        @Test
        @DisplayName("Movimentos: mesma diagonal retorna 1")
        void testMesmaDiagonal() {
            assertEquals(1, Dama.menorNumeroMovimentos(2,2,5,5));
            assertEquals(1, Dama.menorNumeroMovimentos(6,3,3,6));
        }

        @Test
        @DisplayName("Movimentos: casos gerais retornam 2")
        void testCasosGerais() {
            assertEquals(2, Dama.menorNumeroMovimentos(1,1,2,5));
            assertEquals(2, Dama.menorNumeroMovimentos(1,1,7,5));
            assertEquals(2, Dama.menorNumeroMovimentos(3,7,7,4));
        }
    }

    @Nested
    @DisplayName("Testes condição de parada")
    class CondicaoParadaTests {

        @Test
        @DisplayName("Condição de parada: retorna true apenas se for 0 0 0 0")
        void testCondicaoParada() {
            assertTrue(Dama.isCondicaoParada(new int[]{0,0,0,0}));
            assertFalse(Dama.isCondicaoParada(new int[]{0,0,0,1}));
            assertFalse(Dama.isCondicaoParada(new int[]{1,0,0,0}));
            assertFalse(Dama.isCondicaoParada(null));
            assertFalse(Dama.isCondicaoParada(new int[]{0,0,0}));
        }
    }
}
