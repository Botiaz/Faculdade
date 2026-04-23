import java.util.*;

/**
 * Identificação de pontes pelo Algoritmo de Tarjan (1974).
 *
 * Referência: Tarjan, R. E. (1974). A note on finding the bridges of a graph.
 *             Information Processing Letters, 2(6), 160-161.
 *
 * Usa DFS iterativo para evitar estouro de pilha em grafos grandes.
 * Mantém dois valores para cada vértice:
 *   disc[v] – tempo de descoberta na DFS
 *   low[v]  – menor disc alcançável via subárvore de v (usando no máximo uma aresta de retorno)
 *
 * Uma aresta (u, v) de árvore é ponte se low[v] > disc[u].
 *
 * Complexidade: O(V + E) por chamada a findAllBridges.
 */
public class TarjanBridgeFinder implements BridgeFinder {

    @Override
    public boolean isBridge(Graph g, int edgeId) {
        return findAllBridges(g).contains(edgeId);
    }

    /**
     * Encontra todas as pontes com uma única DFS (iterativa).
     * Complexidade: O(V + E)
     */
    @Override
    public Set<Integer> findAllBridges(Graph g) {
        int[] disc = new int[g.V];
        int[] low  = new int[g.V];
        Arrays.fill(disc, -1);

        Set<Integer> bridges = new HashSet<>();
        int[] timer = {0};

        for (int start = 0; start < g.V; start++) {
            if (disc[start] == -1 && g.activeDegree(start) > 0) {
                dfsIterative(g, start, disc, low, timer, bridges);
            }
        }
        return bridges;
    }

    /**
     * DFS iterativa para cálculo de disc[] e low[].
     *
     * Cada frame na pilha: int[3] = { vértice u, índice na adj[u], ID da aresta pai }
     * Quando avançamos para um filho v: empilhamos novo frame para v.
     * Quando terminamos u (índice >= adj[u].size()): desempilhamos e atualizamos low do pai.
     */
    private void dfsIterative(Graph g, int root,
                               int[] disc, int[] low,
                               int[] timer, Set<Integer> bridges) {
        // frame: { vértice, índice_adj, id_aresta_pai }
        Deque<int[]> stack = new ArrayDeque<>();
        disc[root] = low[root] = timer[0]++;
        stack.push(new int[]{root, 0, -1});

        while (!stack.isEmpty()) {
            int[] frame = stack.peek();
            int u          = frame[0];
            int adjIdx     = frame[1];
            int parentEdge = frame[2];

            List<int[]> neighbors = g.adj[u];

            if (adjIdx < neighbors.size()) {
                frame[1]++; // avança índice (modifica o frame no peek)

                int[] e   = neighbors.get(adjIdx);
                int v     = e[0];
                int eid   = e[1];

                // Ignora aresta inativa ou aresta pela qual viemos
                if (!g.active[eid] || eid == parentEdge) {
                    // continua sem empilhar
                } else if (disc[v] == -1) {
                    // Aresta de árvore: visita v
                    disc[v] = low[v] = timer[0]++;
                    stack.push(new int[]{v, 0, eid});
                } else {
                    // Aresta de retorno: atualiza low[u]
                    low[u] = Math.min(low[u], disc[v]);
                }

            } else {
                // Terminamos de processar u — desempilha
                stack.pop();
                if (!stack.isEmpty()) {
                    int[] parentFrame = stack.peek();
                    int parent = parentFrame[0];
                    // Atualiza low do pai
                    low[parent] = Math.min(low[parent], low[u]);
                    // Verifica se aresta (parent, u) é ponte
                    if (low[u] > disc[parent]) {
                        bridges.add(parentEdge);
                    }
                }
            }
        }
    }

    @Override
    public String getName() { return "Tarjan"; }
}