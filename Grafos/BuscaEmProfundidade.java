import java.io.*;
import java.util.*;

public class BuscaEmProfundidade {

    static int numeroVertices, numeroArestas;
    static List<Integer>[] adjacencias;
    static int[] cor;          // 0=BRANCO, 1=CINZA, 2=PRETO
    static int[] descoberta;   // tempo de descoberta
    static int[] finalizacao;  // tempo de finalizacao
    static int temporizador = 0;

    static List<String> arestasArvore = new ArrayList<>();
    static List<String> classificacoesArestas = new ArrayList<>();
    static int verticeConsulta;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = sc.nextLine().trim();

        System.out.print("Digite o número do vértice: ");
        verticeConsulta = Integer.parseInt(sc.nextLine().trim());

        // --- Leitura do grafo ---
        BufferedReader leitorArquivo = new BufferedReader(new FileReader(nomeArquivo));
        StringTokenizer tokenizador = new StringTokenizer(leitorArquivo.readLine());
        numeroVertices = Integer.parseInt(tokenizador.nextToken());
        numeroArestas = Integer.parseInt(tokenizador.nextToken());

        adjacencias = new ArrayList[numeroVertices + 1];
        for (int i = 1; i <= numeroVertices; i++) adjacencias[i] = new ArrayList<>();

        for (int i = 0; i < numeroArestas; i++) {
            tokenizador = new StringTokenizer(leitorArquivo.readLine());
            int origem = Integer.parseInt(tokenizador.nextToken());
            int destino = Integer.parseInt(tokenizador.nextToken());
            adjacencias[origem].add(destino);
        }
        leitorArquivo.close();

        // Ordena listas de adjacência para ordem lexicográfica
        for (int i = 1; i <= numeroVertices; i++) Collections.sort(adjacencias[i]);

        // --- DFS ---
        cor = new int[numeroVertices + 1];
        descoberta  = new int[numeroVertices + 1];
        finalizacao = new int[numeroVertices + 1];

        // DFS completa (começa pelo menor vértice não visitado)
        for (int i = 1; i <= numeroVertices; i++) {
            if (cor[i] == 0) dfs(i);
        }

        // --- Saída ---
        System.out.println("\n=== Arestas de Árvore (DFS completa) ===");
        for (String aresta : arestasArvore) System.out.println(aresta);

        System.out.println("\n=== Classificação das arestas divergentes do vértice " + verticeConsulta + " ===");
        for (String aresta : classificacoesArestas) System.out.println(aresta);
    }

    static void dfs(int verticeAtual) {
        cor[verticeAtual] = 1; // CINZA
        descoberta[verticeAtual] = ++temporizador;

        for (int verticeVizinho : adjacencias[verticeAtual]) {
            String rotuloAresta = "(" + verticeAtual + " -> " + verticeVizinho + ")";

            if (cor[verticeVizinho] == 0) {
                // Aresta de árvore
                arestasArvore.add(rotuloAresta);
                if (verticeAtual == verticeConsulta) classificacoesArestas.add(rotuloAresta + " : Aresta de Árvore");
                dfs(verticeVizinho);

            } else if (verticeAtual == verticeConsulta) {
                // Classifica apenas arestas do vértice consultado
                if (cor[verticeVizinho] == 1) {
                    // vizinho ainda em aberto -> aresta de retorno
                    classificacoesArestas.add(rotuloAresta + " : Aresta de Retorno");
                } else {
                    // vizinho ja fechado
                    if (descoberta[verticeAtual] < descoberta[verticeVizinho]) {
                        // atual foi descoberto antes do vizinho -> aresta de avanco
                        classificacoesArestas.add(rotuloAresta + " : Aresta de Avanço");
                    } else {
                        // atual foi descoberto depois do vizinho -> aresta cruzada
                        classificacoesArestas.add(rotuloAresta + " : Aresta Cruzada");
                    }
                }
            }
        }

        cor[verticeAtual] = 2; // PRETO
        finalizacao[verticeAtual] = ++temporizador;
    }
}