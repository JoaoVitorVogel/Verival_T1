package com.xadrez.rainha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xadrez.rainha.model.PosicaoRainha;
import com.xadrez.rainha.service.RainhaService;

@RestController
@RequestMapping("/api/rainha")
public class RainhaController {

    @Autowired
    private RainhaService rainhaService;

    @PostMapping("/movimentos")
    public ResponseEntity<Integer> calcularMovimentos(@RequestBody PosicaoRainha posicao) {
        int movimentos = rainhaService.calcularMovimentosMinimos(
            posicao.getX1(), posicao.getY1(), posicao.getX2(), posicao.getY2());
        return ResponseEntity.ok(movimentos);
    }
} 