import java.util.*;

/**
 * Identificação de Pontes em Grafos
 * ===================================
 * Método 1 – Naïve  O(E · (V + E))
 * Método 2 – Tarjan (1974)  O(V + E)
 *
 * Referência:
 *   Tarjan, R. E. (1974). A note on finding the bridges of a graph.
 *   Information Processing Letters, 2(6), 160–161.
 *   https://doi.org/10.1016/0020-0190(74)90003-9
 */
public class IdentificadorDePontes {

    // ──────────────────────────────────────────────────────────────
    // Método 1 – Naïve   O(E · (V + E))
    // ──────────────────────────────────────────────────────────────

    /**
     * Para cada aresta (u,v):
     *   1. Copia o grafo e remove (u,v)
     *   2. Verifica conectividade por BFS
     *   3. Se desconexo → (u,v) é ponte
     */
    public static List<int[]> pontesNaive(Grafo grafo) {
        List<int[]> pontes = new ArrayList<>();
        for (int[] a : grafo.getArestas()) {
            Grafo temp = grafo.copiar();
            temp.removerAresta(a[0], a[1]);
            if (!temp.estaConectado()) pontes.add(new int[]{a[0], a[1]});
        }
        return pontes;
    }

    // ──────────────────────────────────────────────────────────────
    // Método 2a – Tarjan recursivo   O(V + E)
    // ──────────────────────────────────────────────────────────────

    /**
     * DFS com dois vetores auxiliares:
     *   disc[v] – tempo de descoberta de v
     *   low[v]  – menor disc alcançável a partir da sub-árvore de v
     *             (sem usar a aresta pai→v)
     *
     * Condição de ponte: low[v] > disc[u]  (u = pai de v na DFS)
     */
    public static List<int[]> pontesTarjan(Grafo grafo) {
        int[]       disc   = new int[grafo.V];
        int[]       low    = new int[grafo.V];
        Arrays.fill(disc, -1);
        List<int[]> pontes = new ArrayList<>();
        int[]       timer  = {0};

        for (int s = 0; s < grafo.V; s++)
            if (disc[s] == -1)
                dfs(grafo, s, -1, disc, low, timer, pontes);

        return pontes;
    }

    private static void dfs(Grafo g, int u, int pai,
                            int[] disc, int[] low, int[] timer,
                            List<int[]> pontes) {
        disc[u] = low[u] = timer[0]++;
        for (int v : g.vizinhos(u)) {
            if (disc[v] == -1) {
                dfs(g, v, u, disc, low, timer, pontes);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) pontes.add(new int[]{u, v});
            } else if (v != pai) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // Método 2b – Tarjan iterativo   O(V + E)
    // ──────────────────────────────────────────────────────────────

    /**
     * Mesma lógica da versão recursiva, porém com pilha explícita.
     * Evita StackOverflowError em grafos com profundidade elevada.
     *
     * Pilha: int[] {vértice, índice_do_próximo_vizinho_a_processar}
     */
    public static List<int[]> pontesTarjanIterativo(Grafo grafo) {
        int[] disc  = new int[grafo.V];
        int[] low   = new int[grafo.V];
        int[] pai   = new int[grafo.V];
        Arrays.fill(disc, -1);
        Arrays.fill(pai,  -1);

        List<int[]>  pontes = new ArrayList<>();
        Deque<int[]> pilha  = new ArrayDeque<>();
        int timer = 0;

        for (int inicio = 0; inicio < grafo.V; inicio++) {
            if (disc[inicio] != -1) continue;

            disc[inicio] = low[inicio] = timer++;
            pilha.push(new int[]{inicio, 0});

            while (!pilha.isEmpty()) {
                int[] estado    = pilha.peek();
                int   u         = estado[0];
                int   idx       = estado[1];
                List<Integer> viz = grafo.vizinhos(u);

                if (idx < viz.size()) {
                    estado[1]++;
                    int v = viz.get(idx);
                    if (disc[v] == -1) {
                        pai[v]  = u;
                        disc[v] = low[v] = timer++;
                        pilha.push(new int[]{v, 0});
                    } else if (v != pai[u]) {
                        low[u] = Math.min(low[u], disc[v]);
                    }
                } else {
                    pilha.pop();
                    if (!pilha.isEmpty()) {
                        int p = pilha.peek()[0];
                        low[p] = Math.min(low[p], low[u]);
                        if (low[u] > disc[p]) pontes.add(new int[]{p, u});
                    }
                }
            }
        }
        return pontes;
    }
}
