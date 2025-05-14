package com.xadrez.rainha.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xadrez.rainha.service.RainhaService;

@Component
public class RainhaConsoleRunner implements CommandLineRunner {

    @Autowired
    private RainhaService rainhaService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Calculadora de Movimentos da Rainha no Xadrez");
        System.out.println("Para cada caso de teste, digite 4 números inteiros: X1 Y1 X2 Y2");
        System.out.println("Para encerrar, digite 0 0 0 0");
        
        while (true) {
            String linha = scanner.nextLine().trim();
            if (linha.equals("0 0 0 0")) {
                break;
            }
            
            String[] valores = linha.split(" ");
            if (valores.length != 4) {
                System.out.println("Entrada inválida. Digite 4 números separados por espaço.");
                continue;
            }
            
            try {
                int x1 = Integer.parseInt(valores[0]);
                int y1 = Integer.parseInt(valores[1]);
                int x2 = Integer.parseInt(valores[2]);
                int y2 = Integer.parseInt(valores[3]);
                
                if (x1 < 1 || x1 > 8 || y1 < 1 || y1 > 8 || x2 < 1 || x2 > 8 || y2 < 1 || y2 > 8) {
                    System.out.println("Coordenadas devem estar entre 1 e 8.");
                    continue;
                }
                
                int movimentos = rainhaService.calcularMovimentosMinimos(x1, y1, x2, y2);
                System.out.println(movimentos);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
        
        scanner.close();
        System.exit(0);
    }
} 