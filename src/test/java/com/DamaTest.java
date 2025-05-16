package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DamaTest {

    // --- PARTIÇÕES ---
    // Testa as três classes principais de comportamento: 0, 1 ou 2 movimentos
    
    @Test
    void testParticoes_MesmaCasa() {
        // Partição: mesma casa → 0 movimentos
        assertEquals(0, Dama.menorNumeroMovimentos(4, 4, 4, 4));
    }

    @Test
    void testParticoes_MesmaLinhaColunaOuDiagonal() {
        // Partição: mesma linha, coluna ou diagonal → 1 movimento
        assertEquals(1, Dama.menorNumeroMovimentos(3, 5, 3, 8)); // mesma linha
        assertEquals(1, Dama.menorNumeroMovimentos(4, 2, 7, 2)); // mesma coluna
        assertEquals(1, Dama.menorNumeroMovimentos(1, 1, 4, 4)); // diagonal
    }

    @Test
    void testParticoes_DoisMovimentos() {
        // Partição: resto dos casos → 2 movimentos
        assertEquals(2, Dama.menorNumeroMovimentos(1, 1, 4, 5));
        assertEquals(2, Dama.menorNumeroMovimentos(2, 3, 7, 6));
    }

    // --- VALOR-LIMITE ---
    // Testa movimentos em valores nas extremidades do tabuleiro 8x8

    @Test
    void testValorLimite_CantosDoTabuleiro() {
        assertEquals(0, Dama.menorNumeroMovimentos(1, 1, 1, 1));   // mesma casa canto superior esquerdo
        assertEquals(1, Dama.menorNumeroMovimentos(1, 1, 1, 8));   // mesma linha canto esquerdo até direito
        assertEquals(1, Dama.menorNumeroMovimentos(1, 1, 8, 1));   // mesma coluna canto superior até inferior
        assertEquals(1, Dama.menorNumeroMovimentos(1, 1, 8, 8));   // diagonal canto superior esquerdo até inferior direito
        assertEquals(2, Dama.menorNumeroMovimentos(1, 8, 8, 7));   // fora das linhas, colunas e diagonais, próximo limite
    }

    // --- COBERTURA DE CÓDIGO ---
    // Garante que todas as condições e caminhos do método são executados

    @Test
    void testCobertura_Codigo() {
        // 0 movimentos
        assertEquals(0, Dama.menorNumeroMovimentos(5, 5, 5, 5));

        // 1 movimento (linha)
        assertEquals(1, Dama.menorNumeroMovimentos(2, 3, 2, 8));

        // 1 movimento (coluna)
        assertEquals(1, Dama.menorNumeroMovimentos(3, 6, 8, 6));

        // 1 movimento (diagonal)
        assertEquals(1, Dama.menorNumeroMovimentos(4, 4, 1, 7));

        // 2 movimentos (caso geral)
        assertEquals(2, Dama.menorNumeroMovimentos(1, 1, 4, 6));
    }

    // --- PROPRIEDADES ---
    // Testa propriedades gerais que a função deve satisfazer para quaisquer entradas válidas

    
}
