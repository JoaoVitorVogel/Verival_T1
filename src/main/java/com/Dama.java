package com;

import java.util.Scanner;

public class Dama {

    // Lógica para calcular menor número de movimentos da dama
    public static int menorNumeroMovimentos(int x1, int y1, int x2, int y2) {
        // mesma casa
        if (x1 == x2 && y1 == y2) {
            return 0;
        }
        // mesma linha, coluna ou diagonal
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return 1;
        }
        // caso geral, sempre 2 movimentos
        return 2;
    }

    public static void lerEntradaEProcessar() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Digite 4 números entre 0 e 8 separados por espaço (0 0 0 0 para sair): ");
            String linha = sc.nextLine().trim();

            if (linha.isEmpty()) {
                System.out.println("Entrada vazia! Tente novamente.");
                continue;
            }

            String[] partes = linha.split("\\s+");
            if (partes.length != 4) {
                System.out.println("Erro: Você deve digitar exatamente 4 valores.");
                continue;
            }

            int[] valores = new int[4];
            boolean valido = true;
            for (int i = 0; i < 4; i++) {
                try {
                    valores[i] = Integer.parseInt(partes[i]);
                    if (valores[i] < 0 || valores[i] > 8) {
                        System.out.println("Erro: Os valores devem estar entre 0 e 8.");
                        valido = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Entrada inválida. Apenas números inteiros são aceitos.");
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                continue; // volta para pedir a entrada de novo
            }

            // condição de parada
            if (valores[0] == 0 && valores[1] == 0 && valores[2] == 0 && valores[3] == 0) {
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
