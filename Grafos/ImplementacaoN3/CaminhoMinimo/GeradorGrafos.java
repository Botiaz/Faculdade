import java.io.*;
import java.util.*;

/**
 * Gerador de grafos de teste para a Implementação N.03
 *
 * Tipo 1 – Grafo Aleatório Esparso (tipo de rede de transporte)
 * Tipo 2 – Grafo em Grade (grid), com arestas horizontais/verticais
 *
 * Uso: java GeradorGrafos
 *   Gera automaticamente os 8 arquivos de teste (4 tamanhos × 2 tipos)
 */
public class GeradorGrafos {

    static Random rng = new Random(42); // semente fixa para reprodutibilidade

    // -----------------------------------------------------------------------
    // Tipo 1 – Grafo Aleatório Esparso
    // -----------------------------------------------------------------------

    /**
     * Gera um grafo dirigido aleatório com garantia de conectividade:
     * primeiro cria um caminho de 0→1→…→(n-1), depois adiciona arestas extras.
     *
     * @param n        número de vértices
     * @param fator    número médio de vizinhos extras por vértice
     * @param pesoMax  peso máximo das arestas
     */
    static void gerarAleatorio(String arquivo, int n, int fator, int pesoMax,
                               int origem, int destino) throws IOException {
        Set<String> arestas = new LinkedHashSet<>();
        List<int[]>  lista  = new ArrayList<>();

        // Caminho garantindo conectividade
        for (int i = 0; i < n - 1; i++) {
            int w = rng.nextInt(pesoMax) + 1;
            String chave = i + "," + (i + 1);
            if (arestas.add(chave)) lista.add(new int[]{i, i + 1, w});
        }

        // Arestas extras aleatórias
        int extras = n * fator;
        for (int k = 0; k < extras; k++) {
            int u = rng.nextInt(n);
            int v = rng.nextInt(n);
            if (u == v) continue;
            int w = rng.nextInt(pesoMax) + 1;
            String chave = u + "," + v;
            if (arestas.add(chave)) lista.add(new int[]{u, v, w});
        }

        escreverArquivo(arquivo, n, lista, origem, destino);
        System.out.printf("  [Tipo1] %s  |  V=%d  A=%d%n", arquivo, n, lista.size());
    }

    // -----------------------------------------------------------------------
    // Tipo 2 – Grafo em Grade (Grid)
    // -----------------------------------------------------------------------

    /**
     * Gera um grafo dirigido em grade rows×cols.
     * Arestas: direita e baixo (com pesos aleatórios).
     */
    static void gerarGrade(String arquivo, int rows, int cols,
                           int pesoMax) throws IOException {
        int n = rows * cols;
        List<int[]> lista = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int u = r * cols + c;
                // Aresta para a direita
                if (c + 1 < cols) {
                    int v = r * cols + (c + 1);
                    lista.add(new int[]{u, v, rng.nextInt(pesoMax) + 1});
                }
                // Aresta para baixo
                if (r + 1 < rows) {
                    int v = (r + 1) * cols + c;
                    lista.add(new int[]{u, v, rng.nextInt(pesoMax) + 1});
                }
            }
        }

        int origem  = 0;
        int destino = n - 1;
        escreverArquivo(arquivo, n, lista, origem, destino);
        System.out.printf("  [Tipo2] %s  |  V=%d (%dx%d)  A=%d%n",
                arquivo, n, rows, cols, lista.size());
    }

    // -----------------------------------------------------------------------
    // Utilitário de escrita
    // -----------------------------------------------------------------------

    static void escreverArquivo(String arquivo, int n, List<int[]> lista,
                                int origem, int destino) throws IOException {
        File dir = new File(arquivo).getParentFile();
        if (dir != null) dir.mkdirs();

        PrintWriter pw = new PrintWriter(new FileWriter(arquivo));
        pw.println(n + " " + lista.size());
        for (int[] a : lista)
            pw.println(a[0] + " " + a[1] + " " + a[2]);
        pw.println(origem + " " + destino);
        pw.close();
    }

    // -----------------------------------------------------------------------
    // main
    // -----------------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        System.out.println("Gerando grafos de teste...\n");

        // ── Tipo 1: Grafos Aleatórios Esparsos ─────────────────────────────
        System.out.println("Tipo 1 – Grafo Aleatório Esparso:");
        // Tamanho 1: pequeno
        gerarAleatorio("grafos/tipo1_pequeno.txt",    50,    3,  50,   0,  49);
        // Tamanho 2: médio
        gerarAleatorio("grafos/tipo1_medio.txt",     500,    4, 100,   0, 499);
        // Tamanho 3: grande
        gerarAleatorio("grafos/tipo1_grande.txt",   5000,    5, 200,   0, 4999);
        // Tamanho 4: muito grande
        gerarAleatorio("grafos/tipo1_enorme.txt",  50000,    5, 500,   0, 49999);

        System.out.println();

        // ── Tipo 2: Grafos em Grade ─────────────────────────────────────────
        System.out.println("Tipo 2 – Grafo em Grade (Grid):");
        // Tamanho 1: 10×10 = 100 vértices
        gerarGrade("grafos/tipo2_pequeno.txt",   10,   10,  30);
        // Tamanho 2: 30×30 = 900 vértices
        gerarGrade("grafos/tipo2_medio.txt",     30,   30,  60);
        // Tamanho 3: 100×100 = 10000 vértices
        gerarGrade("grafos/tipo2_grande.txt",   100,  100, 100);
        // Tamanho 4: 300×300 = 90000 vértices
        gerarGrade("grafos/tipo2_enorme.txt",   300,  300, 200);

        System.out.println("\nOK – Arquivos gerados em ./grafos/");
    }
}
