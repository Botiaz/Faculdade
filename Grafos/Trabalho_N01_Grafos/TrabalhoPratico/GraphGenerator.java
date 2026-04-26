import java.util.*;

/**
 * Gerador de grafos aleatórios para os experimentos.
 *
 * Tipos gerados:
 *   - Euleriano:      grafo conexo, todos vértices grau par
 *   - Semi-Euleriano: grafo conexo, exatamente 2 vértices com grau ímpar
 *   - Não-Euleriano:  grafo conexo com mais de 2 vértices de grau ímpar
 */
public class GraphGenerator {

    private final Random rng;

    public GraphGenerator(long seed) { this.rng = new Random(seed); }
    public GraphGenerator()          { this.rng = new Random(); }

    // -------------------------------------------------------------------------
    // Euleriano: ciclo Hamiltoniano + ciclos aleatórios adicionais
    // Todo ciclo mantém os graus pares → invariante preservado.
    // -------------------------------------------------------------------------
    public Graph generateEulerian(int V) {
        Graph g = new Graph(V);

        // 1) Ciclo Hamiltoniano: garante conexão e graus = 2 (pares)
        int[] perm = shuffle(V);
        for (int i = 0; i < V; i++) {
            g.addEdge(perm[i], perm[(i + 1) % V]);
        }

        // 2) Ciclos aleatórios extras (poucos, para manter grafo esparso nos experimentos)
        int numExtra = Math.min(3, Math.max(1, V / 50));
        for (int c = 0; c < numExtra; c++) {
            int len = 3 + rng.nextInt(Math.min(8, Math.max(3, V / 100)));
            len = Math.min(len, V);
            int[] cyc = shufflePrefix(V, len);
            for (int i = 0; i < len; i++) {
                g.addEdge(cyc[i], cyc[(i + 1) % len]);
            }
        }

        return g;
    }

    // -------------------------------------------------------------------------
    // Semi-Euleriano: começa com um grafo Euleriano e adiciona uma aresta.
    // Essa aresta extra torna exatamente 2 vértices ímpares.
    // -------------------------------------------------------------------------
    public Graph generateSemiEulerian(int V) {
        Graph g = generateEulerian(V);
        int u = rng.nextInt(V);
        int v;
        do { v = rng.nextInt(V); } while (v == u);
        g.addEdge(u, v);
        return g;
    }

    // -------------------------------------------------------------------------
    // Não-Euleriano: árvore geradora aleatória + arestas extras.
    // A maioria dos vértices terá grau ímpar.
    // -------------------------------------------------------------------------
    public Graph generateNonEulerian(int V) {
        Graph g = new Graph(V);

        // Árvore geradora aleatória (garante conexão)
        int[] perm = shuffle(V);
        for (int i = 1; i < V; i++) {
            int parent = rng.nextInt(i);
            g.addEdge(perm[parent], perm[i]);
        }

        // Arestas extras aleatórias (criam vértices de grau ímpar)
        int extras = Math.max(V / 4, 5);
        for (int i = 0; i < extras; i++) {
            int u = rng.nextInt(V);
            int v = rng.nextInt(V);
            if (u != v) g.addEdge(u, v);
        }

        // Garante que temos > 2 vértices ímpares
        int[] deg = g.activeDegrees();
        int oddCount = 0;
        for (int d : deg) if (d % 2 == 1) oddCount++;

        // Ajuste se necessário: adiciona arestas avulsas até ter > 2 ímpares
        int attempts = 0;
        while (oddCount <= 2 && attempts++ < V) {
            int u = rng.nextInt(V);
            int v = rng.nextInt(V);
            if (u != v) {
                g.addEdge(u, v);
                // Recalcula (simplificado)
                deg[u]++; deg[v]++;
                oddCount = 0;
                for (int d : deg) if (d % 2 == 1) oddCount++;
            }
        }

        return g;
    }

    // -------------------------------------------------------------------------
    // Utilitários
    // -------------------------------------------------------------------------

    /** Permutação aleatória de [0, n). */
    private int[] shuffle(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        for (int i = n - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return arr;
    }

    /** Retorna os primeiros k elementos de uma permutação aleatória. */
    private int[] shufflePrefix(int n, int k) {
        int[] arr = shuffle(n);
        return Arrays.copyOf(arr, k);
    }
} 
