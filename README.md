# Problema da Rainha no Xadrez

Este projeto implementa uma solução para o problema da rainha no xadrez, onde o objetivo é calcular o número mínimo de movimentos necessários para uma rainha ir de uma posição inicial até uma posição de destino em um tabuleiro 8x8.

## Descrição do Problema

A dama (rainha) no xadrez pode se mover qualquer quantidade de casas na mesma linha, na mesma coluna, ou em uma das duas diagonais. O problema consiste em determinar o número mínimo de movimentos necessários para ir de uma posição (X1, Y1) até uma posição (X2, Y2).

## Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven

### Executando via Console

1. Clone o repositório
2. Na pasta raiz do projeto, execute:

```bash
mvn spring-boot:run
```

3. Digite as entradas no formato `X1 Y1 X2 Y2`, onde cada valor é um número entre 1 e 8 (inclusive).
4. Para encerrar o programa, digite `0 0 0 0`.

### Utilizando a API REST

A aplicação também disponibiliza um endpoint REST:

```
POST /api/rainha/movimentos
```

Corpo da requisição:

```json
{
  "x1": 4,
  "y1": 4,
  "x2": 6,
  "y2": 2
}
```

Resposta:

```
1
```

## Lógica da Solução

O algoritmo para determinar o número mínimo de movimentos é:

1. Se a rainha já está na posição de destino, retorna 0 (nenhum movimento necessário).
2. Se a rainha pode chegar à posição de destino em um único movimento (mesma linha, coluna ou diagonal), retorna 1.
3. Em qualquer outro caso, são necessários exatamente 2 movimentos, pois a rainha pode primeiro mover-se para uma posição intermediária que esteja na mesma linha/coluna/diagonal com a posição de destino.
