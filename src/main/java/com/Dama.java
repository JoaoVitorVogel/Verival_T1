package com;

import java.util.Scanner;

public class Dama {

    /**
     * Verifica se os valores são válidos para o tabuleiro (1 a 8),
     * exceto o caso especial 0 0 0 0 para parada.
     * @param valores array de 4 inteiros representando x1,y1,x2,y2
     * @return true se todos os valores estiverem entre 1 e 8, 
     * ou se for o caso especial 0 0 0 0 (finalização).
     */
    public static boolean validarValores(int[] valores) {
        if (valores == null || valores.length != 4) return false;

        // Se for a condição de parada 0 0 0 0, aceita
        if (isCondicaoParada(valores)) return true;

        // Para todos os outros casos, valores devem ser entre 1 e 8
        for (int v : valores) {
            if (v < 1 || v > 8) return false;
        }
        return true;
    }

    /**
     * Verifica se a condição de parada foi acionada: todos zeros.
     */
    public static boolean isCondicaoParada(int[] valores) {
        return valores != null &&
               valores.length == 4 &&
               valores[0] == 0 &&
               valores[1] == 0 &&
               valores[2] == 0 &&
               valores[3] == 0;
    }

    /**
     * Lógica para calcular menor número de movimentos da dama.
     * @param x1 posição x inicial
     * @param y1 posição y inicial
     * @param x2 posição x final
     * @param y2 posição y final
     * @return 0, 1 ou 2 movimentos
     */
    public static int menorNumeroMovimentos(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) return 0;
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) return 1;
        return 2;
    }

    /**
     * Método que converte string de entrada em array de inteiros,
     * validando o formato e valores.
     * @param linha string contendo 4 números separados por espaço
     * @return array de 4 ints se válido, ou null se inválido
     */
    public static int[] parseEntrada(String linha) {
        if (linha == null || linha.trim().isEmpty()) return null;
        String[] partes = linha.trim().split("\\s+");
        if (partes.length != 4) return null;
        int[] valores = new int[4];
        try {
            for (int i = 0; i < 4; i++) {
                valores[i] = Integer.parseInt(partes[i]);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        if (!validarValores(valores)) return null;
        return valores;
    }

    /**
     * Lê do console as entradas, processa e imprime resultado.
     */
    public static void lerEntradaEProcessar() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Digite 4 números entre 1 e 8 separados por espaço (0 0 0 0 para sair): ");
            String linha = sc.nextLine();

            int[] valores = parseEntrada(linha);
            if (valores == null) {
                System.out.println("Erro na entrada. Digite exatamente 4 números entre 1 e 8, ou 0 0 0 0 para sair.");
                continue;
            }

            if (isCondicaoParada(valores)) {
                break;
            }

            int movimentos = menorNumeroMovimentos(valores[0], valores[1], valores[2], valores[3]);
            System.out.println(movimentos);
        }

        sc.close();
    }

    public static void main(String[] args) {
        lerEntradaEProcessar();
    }
}
