# Implementação N.03 – Caminho Mínimo

Encontra o caminho de **menor peso total** em um grafo dirigido ponderado
(valores positivos). Em caso de empate, escolhe o caminho com **menor número
de arestas**.

---

## Arquivos

| Arquivo             | Descrição                                          |
|---------------------|----------------------------------------------------|
| `CaminhoMinimo.java` | Algoritmo principal (Dijkstra modificado)         |
| `GeradorGrafos.java` | Gera os 8 grafos de teste                         |
| `Benchmark.java`     | Executa todos os testes e exibe tabelas            |
| `rodar.sh`           | Script que compila e roda tudo de uma vez          |

---

## Como usar

### Compilar
```bash
javac CaminhoMinimo.java GeradorGrafos.java Benchmark.java
```

### Rodar com um arquivo
```bash
java CaminhoMinimo grafos/tipo1_pequeno.txt
```

### Gerar grafos de teste + tabelas
```bash
java GeradorGrafos
java Benchmark
```

### Tudo de uma vez (Linux/Mac)
```bash
chmod +x rodar.sh && ./rodar.sh
```

---

## Formato do arquivo de entrada

```
<num_vertices> <num_arestas>
<u> <v> <peso>
<u> <v> <peso>
...
<vertice_origem> <vertice_destino>
```

**Exemplo** (`exemplo.txt`):
```
5 7
0 1 4
0 2 1
2 1 2
1 3 5
2 3 8
3 4 2
1 4 11
0 4
```

Saída esperada:
```
Comprimento (peso)   : 11
Número de arestas    : 3
Vértices do caminho  : 0 → 2 → 1 → 3 → 4  ← caminho de peso 11, 4 arestas? 
                                              Na verdade 0→1→3→4 = 4+5+2=11, 3 arestas ✓
```

---

## Tipos de grafo testados

| Tipo | Descrição | Tamanhos (vértices) |
|------|-----------|---------------------|
| 1 | Aleatório esparso (tipo rede de transporte) | 50 / 500 / 5.000 / 50.000 |
| 2 | Grade (grid rows×cols) | 100 / 900 / 10.000 / 90.000 |

---

## Algoritmo

**Dijkstra Modificado** com chave de prioridade composta:

```
(distância_total, número_de_arestas)
```

Ao relaxar uma aresta `(u → v, w)`:
- Se `dist[u] + w < dist[v]` → atualiza (caminho mais curto)
- Se `dist[u] + w == dist[v]` e `arestas[u]+1 < arestas[v]` → atualiza (mesmo peso, menos arestas)

**Complexidade:** O((V + A) log V) — idêntica ao Dijkstra clássico.
