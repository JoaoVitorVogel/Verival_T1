package com.xadrez.rainha.service;

import org.springframework.stereotype.Service;

@Service
public class RainhaService {

    /**
     * Calcula o número mínimo de movimentos necessários para a rainha se deslocar
     * de uma posição inicial (x1, y1) até uma posição final (x2, y2)
     * 
     * @param x1 Coordenada X inicial
     * @param y1 Coordenada Y inicial
     * @param x2 Coordenada X final
     * @param y2 Coordenada Y final
     * @return Número mínimo de movimentos
     */
    public int calcularMovimentosMinimos(int x1, int y1, int x2, int y2) {
        // Se já está na posição de destino
        if (x1 == x2 && y1 == y2) {
            return 0;
        }
        
        // Se está na mesma linha, coluna ou diagonal, precisa apenas de 1 movimento
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return 1;
        }
        
        // Em qualquer outro caso, são necessários exatamente 2 movimentos
        return 2;
    }
} 