# Trabalho N01 - Grafos

Projeto da disciplina de Grafos com foco em:

- Identificacao de pontes em grafos nao direcionados.
- Classificacao de grafos eulerianos, semi-eulerianos e nao eulerianos.
- Execucao do algoritmo de Fleury para caminho/circuito euleriano.
- Comparacao de desempenho entre os metodos Naive e Tarjan.

## Estrutura do projeto

- `TrabalhoPratico/Main.java`: ponto de entrada do programa.
- `TrabalhoPratico/Experiment.java`: executa relatorio funcional e experimentos de desempenho.
- `TrabalhoPratico/Graph.java`: estrutura do grafo (listas de adjacencia e arestas ativas).
- `TrabalhoPratico/GraphGenerator.java`: geracao de grafos Euleriano, Semi-Euleriano e Nao-Euleriano.
- `TrabalhoPratico/BridgeFinder.java`: interface para deteccao de pontes.
- `TrabalhoPratico/NaiveBridgeFinder.java`: deteccao de pontes por metodo ingenuo.
- `TrabalhoPratico/TarjanBridgeFinder.java`: deteccao de pontes em O(V + E) com Tarjan.
- `TrabalhoPratico/FleuryAlgorithm.java`: construcao de caminho/circuito euleriano.
- `main.tex`: relatorio em LaTeX.
- `Trabalho_N01_Grafos.pdf`: versao em PDF do relatorio.

## Observacoes

- Os experimentos usam repeticoes para calcular tempo medio.
- O metodo Naive pode ser marcado como `N/A (lento)` em grafos grandes.
- O Fleury com Tarjan tambem possui limite pratico para tamanhos muito altos, conforme configuracao em `Experiment.java`.
