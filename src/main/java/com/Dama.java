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

    // Função separada para leitura e interação com usuário
    public static void lerEntradaEProcessar() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0) {
                break;
            }

            int movimentos = menorNumeroMovimentos(x1, y1, x2, y2);
            System.out.println(movimentos);
        }

        sc.close();
    }

    public static void main(String[] args) {
        lerEntradaEProcessar();
    }
}
