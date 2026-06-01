import java.io.*;
import java.util.*;

/**
 * Encontra caminhos disjuntos em arestas usando Fluxo Máximo (Edmonds-Karp / BFS-Ford-Fulkerson).
 *
 * A ideia central: modelamos o grafo direcionado em uma rede de fluxo onde cada aresta
 * tem capacidade 1. O fluxo máximo de s para t equivale ao número máximo de caminhos
 * disjuntos em arestas entre s e t.
 *
 * Complexidade: O(V * E^2) — Edmonds-Karp com BFS.
 */
public class CaminhosDijuntos {

    // -----------------------------------------------------------------------
    // Estrutura interna: grafo residual com lista de adjacência
    // -----------------------------------------------------------------------
    static int[] cap[];          // capacidade residual: cap[u][v]
    static int n;                // número de vértices

    // Inicializa a matriz de capacidades
    static void init(int vertices) {
        n = vertices;
        cap = new int[n][n];
    }

    // Adiciona aresta direcionada u->v com capacidade 1
    static void addEdge(int u, int v) {
        cap[u][v] += 1;
        // A aresta reversa v->u começa com capacidade 0 (rede residual)
    }

    // -----------------------------------------------------------------------
    // BFS: encontra caminho aumentante de s a t no grafo residual
    // Retorna array 'pai' (predecessor) ou null se não há caminho
    // -----------------------------------------------------------------------
    static int[] bfs(int s, int t) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        parent[s] = s;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty() && parent[t] == -1) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (parent[v] == -1 && cap[u][v] > 0) {
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }
        return parent[t] == -1 ? null : parent;
    }

    // -----------------------------------------------------------------------
    // Edmonds-Karp: calcula fluxo máximo de s a t
    // -----------------------------------------------------------------------
    static int maxFlow(int s, int t) {
        int flow = 0;
        int[] parent;
        while ((parent = bfs(s, t)) != null) {
            // Como cada capacidade é 1, o fluxo aumentante é sempre 1
            int v = t;
            while (v != s) {
                int u = parent[v];
                cap[u][v] -= 1;
                cap[v][u] += 1;  // aresta reversa
                v = u;
            }
            flow++;
        }
        return flow;
    }

    // -----------------------------------------------------------------------
    // Recupera os caminhos disjuntos a partir da rede residual pós-fluxo.
    // Usa DFS seguindo arestas que foram "usadas" (cap original - cap residual > 0).
    // Para isso, precisamos guardar as capacidades originais.
    // -----------------------------------------------------------------------
    static int[][] capOriginal;

    static List<List<Integer>> extractPaths(int s, int t, int numPaths) {
        // Copia do estado atual (pós-fluxo) para rastrear fluxo enviado
        int[][] usedFlow = new int[n][n];
        for (int u = 0; u < n; u++)
            for (int v = 0; v < n; v++)
                usedFlow[u][v] = capOriginal[u][v] - cap[u][v];

        List<List<Integer>> paths = new ArrayList<>();

        for (int p = 0; p < numPaths; p++) {
            List<Integer> path = new ArrayList<>();
            int cur = s;
            path.add(cur);
            boolean found = true;

            while (cur != t) {
                boolean moved = false;
                for (int v = 0; v < n; v++) {
                    if (usedFlow[cur][v] > 0) {
                        usedFlow[cur][v]--;  // consome este caminho
                        path.add(v);
                        cur = v;
                        moved = true;
                        break;
                    }
                }
                if (!moved) { found = false; break; }
            }
            if (found) paths.add(path);
        }
        return paths;
    }

    // -----------------------------------------------------------------------
    // Lê o grafo de um arquivo
    // Formato esperado:
    //   Linha 1: V E  (vértices e arestas)
    //   Linhas 2..E+1: u v  (aresta direcionada de u para v, índices 0-based)
    //   Última linha: s t  (origem e destino)
    // -----------------------------------------------------------------------
    static int[] readGraph(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        init(V);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            addEdge(u, v);
        }

        // Salva cópia das capacidades originais para reconstrução dos caminhos
        capOriginal = new int[n][n];
        for (int u = 0; u < n; u++)
            capOriginal[u] = Arrays.copyOf(cap[u], n);

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        br.close();
        return new int[]{s, t};
    }

    // -----------------------------------------------------------------------
    // Exibe resultado formatado
    // -----------------------------------------------------------------------
    static void printResult(int s, int t, int numPaths, List<List<Integer>> paths,
                            long timeNs) {
        System.out.println("=".repeat(55));
        System.out.printf("Origem: %d  →  Destino: %d%n", s, t);
        System.out.println("=".repeat(55));
        System.out.printf("Número de caminhos disjuntos em arestas: %d%n%n", numPaths);
        System.out.println("Caminhos encontrados:");
        for (int i = 0; i < paths.size(); i++) {
            System.out.printf("  Caminho %d: %s%n", i + 1,
                    paths.get(i).toString().replace("[", "").replace("]", "")
                         .replace(", ", " → "));
        }
        System.out.printf("%nTempo de execução: %.3f ms%n", timeNs / 1_000_000.0);
        System.out.println("=".repeat(55));
    }

    // -----------------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: java EdgeDisjointPaths <arquivo_grafo>");
            System.err.println("Gerando exemplos e executando testes automáticos...\n");
            runBuiltinTests();
            return;
        }

        int[] st = readGraph(args[0]);
        int s = st[0], t = st[1];

        long start = System.nanoTime();
        int numPaths = maxFlow(s, t);
        long elapsed = System.nanoTime() - start;

        // Reinicia cap para reconstrução (maxFlow alterou cap)
        for (int u = 0; u < n; u++)
            cap[u] = Arrays.copyOf(capOriginal[u], n);
        // Re-executa para reconstruir estado residual correto
        capOriginal = new int[n][n];
        for (int u = 0; u < n; u++)
            capOriginal[u] = Arrays.copyOf(cap[u], n);
        maxFlow(s, t);

        List<List<Integer>> paths = extractPaths(s, t, numPaths);
        printResult(s, t, numPaths, paths, elapsed);
    }

    // -----------------------------------------------------------------------
    // Testes embutidos com dois tipos de grafos e 4 tamanhos cada
    // -----------------------------------------------------------------------
    static void runBuiltinTests() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     TESTES: CAMINHOS DISJUNTOS EM ARESTAS           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        // --- TIPO 1: Grafos em Grade (Grid Graphs) ---
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│  TIPO 1: Grafos em Grade (Grid Graphs)              │");
        System.out.println("└──────────────────────────────────────────────────────┘");
        System.out.println("Descrição: Grafo direcionado formado por uma grade N×N,");
        System.out.println("com arestas indo para a direita e para baixo.");
        System.out.println("Origem = canto superior-esquerdo, Destino = canto inferior-direito.\n");

        int[] gridSizes = {3, 5, 8, 12};
        printTableHeader();
        for (int g : gridSizes) {
            TestResult r = testGrid(g);
            printTableRow(String.format("Grade %d×%d", g, g), g*g, 2*(g*(g-1)), r);
        }
        printTableFooter();

        System.out.println();

        // --- TIPO 2: Grafos Aleatórios Esparsos ---
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.println("│  TIPO 2: Grafos Aleatórios Esparsos (Random Sparse) │");
        System.out.println("└──────────────────────────────────────────────────────┘");
        System.out.println("Descrição: Grafos direcionados aleatórios com ~2V arestas.");
        System.out.println("Semente fixa para reprodutibilidade. Origem=0, Destino=V-1.\n");

        int[] randomSizes = {10, 25, 50, 100};
        printTableHeader();
        for (int v : randomSizes) {
            TestResult r = testRandom(v, v * 2L, 42L);
            printTableRow(String.format("Aleatório V=%d", v), v, v*2, r);
        }
        printTableFooter();
    }

    static void printTableHeader() {
        System.out.printf("%-22s │ %6s │ %6s │ %8s │ %10s%n",
                "Instância", "V", "E", "Caminhos", "Tempo(ms)");
        System.out.println("─".repeat(62));
    }

    static void printTableRow(String name, int v, int e, TestResult r) {
        System.out.printf("%-22s │ %6d │ %6d │ %8d │ %10.4f%n",
                name, v, e, r.paths, r.timeMs);
    }

    static void printTableFooter() {
        System.out.println("─".repeat(62));
    }

    // -----------------------------------------------------------------------
    // Gera e testa grafo em grade N×N
    // -----------------------------------------------------------------------
    static TestResult testGrid(int gridSize) {
        int V = gridSize * gridSize;
        init(V);

        // Arestas: direita e baixo
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                int u = r * gridSize + c;
                if (c + 1 < gridSize) addEdge(u, r * gridSize + (c + 1));
                if (r + 1 < gridSize) addEdge(u, (r + 1) * gridSize + c);
            }
        }

        capOriginal = new int[n][n];
        for (int u = 0; u < n; u++) capOriginal[u] = Arrays.copyOf(cap[u], n);

        int s = 0, t = V - 1;
        long start = System.nanoTime();
        int flow = maxFlow(s, t);
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        return new TestResult(flow, timeMs);
    }

    // -----------------------------------------------------------------------
    // Gera e testa grafo aleatório
    // -----------------------------------------------------------------------
    static TestResult testRandom(int V, long numEdges, long seed) {
        init(V);
        Random rng = new Random(seed);
        Set<Long> added = new HashSet<>();

        long attempts = 0;
        long added_count = 0;
        while (added_count < numEdges && attempts < numEdges * 10) {
            int u = rng.nextInt(V);
            int v = rng.nextInt(V);
            if (u != v) {
                long key = (long) u * V + v;
                if (!added.contains(key)) {
                    added.add(key);
                    addEdge(u, v);
                    added_count++;
                }
            }
            attempts++;
        }

        capOriginal = new int[n][n];
        for (int u = 0; u < n; u++) capOriginal[u] = Arrays.copyOf(cap[u], n);

        int s = 0, t = V - 1;
        long start = System.nanoTime();
        int flow = maxFlow(s, t);
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        return new TestResult(flow, timeMs);
    }

    static class TestResult {
        int paths;
        double timeMs;
        TestResult(int p, double t) { paths = p; timeMs = t; }
    }
}
