#!/bin/bash
# ============================================================
#  Implementação N.03 – Caminho Mínimo
#  Script de compilação e execução
# ============================================================

echo "=== Compilando... ==="
javac CaminhoMinimo.java GeradorGrafos.java Benchmark.java
if [ $? -ne 0 ]; then echo "Erro de compilação."; exit 1; fi

echo ""
echo "=== Gerando grafos de teste... ==="
java GeradorGrafos

echo ""
echo "=== Executando benchmarks... ==="
java Benchmark

echo ""
echo "=== Exemplo de uso direto: ==="
echo "  java CaminhoMinimo grafos/tipo1_pequeno.txt"
echo ""
echo "=== Para um grafo próprio: ==="
echo "  java CaminhoMinimo seu_grafo.txt"
echo ""
echo "Formato do arquivo:"
echo "  Linha 1:  <num_vertices> <num_arestas>"
echo "  Próximas: <origem> <destino> <peso>"
echo "  Última:   <vertice_origem> <vertice_destino>"
