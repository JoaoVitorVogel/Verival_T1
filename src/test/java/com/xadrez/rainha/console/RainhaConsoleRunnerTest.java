package com.xadrez.rainha.console;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xadrez.rainha.service.RainhaService;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class RainhaConsoleRunnerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContentForValidInput = new ByteArrayInputStream(
            "4 4 6 2\n3 5 3 5\n5 5 4 3\n0 0 0 0\n".getBytes());
    private final ByteArrayInputStream inContentForInvalidInput = new ByteArrayInputStream(
            "4 4 x 2\n0 0 0 0\n".getBytes());
    private final ByteArrayInputStream inContentForOutOfBoundsInput = new ByteArrayInputStream(
            "9 9 8 8\n0 0 0 0\n".getBytes());
    
    @Mock
    private RainhaService rainhaService;
    
    private RainhaConsoleRunner rainhaConsoleRunner;
    
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }
    
    @Test
    public void testRunWithValidInput() throws Exception {
        // Configurar o comportamento do mock
        when(rainhaService.calcularMovimentosMinimos(4, 4, 6, 2)).thenReturn(1);
        when(rainhaService.calcularMovimentosMinimos(3, 5, 3, 5)).thenReturn(0);
        when(rainhaService.calcularMovimentosMinimos(5, 5, 4, 3)).thenReturn(2);
        
        // Configurar entrada simulada
        System.setIn(inContentForValidInput);
        
        // Criar instância com mock
        rainhaConsoleRunner = new RainhaConsoleRunner();
        // Injetar o mock
        java.lang.reflect.Field field = RainhaConsoleRunner.class.getDeclaredField("rainhaService");
        field.setAccessible(true);
        field.set(rainhaConsoleRunner, rainhaService);
        
        // Executar método run
        try {
            rainhaConsoleRunner.run();
        } catch (Exception e) {
            // Ignorar erro de System.exit() chamado pelo método
        }
        
        // Verificar que o serviço foi chamado com os parâmetros corretos
        verify(rainhaService, times(1)).calcularMovimentosMinimos(4, 4, 6, 2);
        verify(rainhaService, times(1)).calcularMovimentosMinimos(3, 5, 3, 5);
        verify(rainhaService, times(1)).calcularMovimentosMinimos(5, 5, 4, 3);
    }
    
    @Test
    public void testRunWithInvalidInput() throws Exception {
        // Configurar entrada simulada
        System.setIn(inContentForInvalidInput);
        
        // Criar instância com mock
        rainhaConsoleRunner = new RainhaConsoleRunner();
        // Injetar o mock
        java.lang.reflect.Field field = RainhaConsoleRunner.class.getDeclaredField("rainhaService");
        field.setAccessible(true);
        field.set(rainhaConsoleRunner, rainhaService);
        
        // Executar método run
        try {
            rainhaConsoleRunner.run();
        } catch (Exception e) {
            // Ignorar erro de System.exit() chamado pelo método
        }
        
        // Verificar que a mensagem de erro foi exibida
        String output = outContent.toString();
        assert(output.contains("Entrada inválida"));
        
        // Verificar que o serviço não foi chamado
        verify(rainhaService, times(0)).calcularMovimentosMinimos(anyInt(), anyInt(), anyInt(), anyInt());
    }
    
    @Test
    public void testRunWithOutOfBoundsInput() throws Exception {
        // Configurar entrada simulada
        System.setIn(inContentForOutOfBoundsInput);
        
        // Criar instância com mock
        rainhaConsoleRunner = new RainhaConsoleRunner();
        // Injetar o mock
        java.lang.reflect.Field field = RainhaConsoleRunner.class.getDeclaredField("rainhaService");
        field.setAccessible(true);
        field.set(rainhaConsoleRunner, rainhaService);
        
        // Executar método run
        try {
            rainhaConsoleRunner.run();
        } catch (Exception e) {
            // Ignorar erro de System.exit() chamado pelo método
        }
        
        // Verificar que a mensagem de erro foi exibida
        String output = outContent.toString();
        assert(output.contains("Coordenadas devem estar entre 1 e 8"));
        
        // Verificar que o serviço não foi chamado
        verify(rainhaService, times(0)).calcularMovimentosMinimos(anyInt(), anyInt(), anyInt(), anyInt());
    }
} 