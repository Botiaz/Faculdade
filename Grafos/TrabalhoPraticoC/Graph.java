import java.util.*;

/**
 * Grafo não-direcionado representado por listas de adjacência.
 * Arestas podem ser ativadas/desativadas individualmente (usado no algoritmo de Fleury).
 */
public class Graph {

    public final int V;          // Número de vértices
    public int E;                 // Número de arestas adicionadas
    public final List<int[]>[] adj;  // adj[u] = lista de {vizinho, idAresta}
    public boolean[] active;     // active[id] = aresta está ativa?
    public int[] eu, ev;         // Extremidades: aresta id conecta eu[id] -- ev[id]

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        int cap = Math.max(V * 2, 16);
        this.active = new boolean[cap];
        this.eu     = new int[cap];
        this.ev     = new int[cap];
    }

    /** Adiciona aresta não-direcionada e retorna seu ID. */
    public int addEdge(int u, int v) {
        ensureCapacity(E + 1);
        int id = E++;
        adj[u].add(new int[]{v, id});
        adj[v].add(new int[]{u, id});
        active[id] = true;
        eu[id] = u;
        ev[id] = v;
        return id;
    }

    private void ensureCapacity(int needed) {
        if (needed <= active.length) return;
        int cap = Math.max(active.length * 2, needed);
        active = Arrays.copyOf(active, cap);
        eu     = Arrays.copyOf(eu, cap);
        ev     = Arrays.copyOf(ev, cap);
    }

    public void deactivate(int id) { active[id] = false; }
    public void activate  (int id) { active[id] = true;  }

    /** Grau de u considerando apenas arestas ativas. */
    public int activeDegree(int u) {
        int d = 0;
        for (int[] e : adj[u]) if (active[e[1]]) d++;
        return d;
    }

    /** Vetor de graus ativos para todos os vértices. */
    public int[] activeDegrees() {
        int[] deg = new int[V];
        for (int id = 0; id < E; id++) {
            if (active[id]) { deg[eu[id]]++; deg[ev[id]]++; }
        }
        return deg;
    }

    /** Número de arestas ativas. */
    public int activeEdgeCount() {
        int c = 0;
        for (int id = 0; id < E; id++) if (active[id]) c++;
        return c;
    }

    /**
     * BFS a partir de src usando somente arestas ativas.
     * @return vetor de visitados
     */
    public boolean[] bfs(int src) {
        boolean[] vis = new boolean[V];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(src);
        vis[src] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] e : adj[u]) {
                int v = e[0], eid = e[1];
                if (active[eid] && !vis[v]) { vis[v] = true; q.add(v); }
            }
        }
        return vis;
    }

    /** Verifica se o subgrafo ativo é conexo (ignora vértices isolados). */
    public boolean isConnected() {
        int start = -1;
        for (int u = 0; u < V; u++) {
            if (activeDegree(u) > 0) { start = u; break; }
        }
        if (start == -1) return true;
        boolean[] vis = bfs(start);
        for (int u = 0; u < V; u++) {
            if (activeDegree(u) > 0 && !vis[u]) return false;
        }
        return true;
    }

    /** Cópia profunda do grafo. */
    @SuppressWarnings("unchecked")
    public Graph copy() {
        Graph g = new Graph(V);
        g.E      = E;
        g.active = Arrays.copyOf(active, active.length);
        g.eu     = Arrays.copyOf(eu, eu.length);
        g.ev     = Arrays.copyOf(ev, ev.length);
        for (int id = 0; id < E; id++) {
            g.adj[eu[id]].add(new int[]{ev[id], id});
            g.adj[ev[id]].add(new int[]{eu[id], id});
        }
        return g;
    }

    @Override
    public String toString() {
        return String.format("Graph(V=%d, E=%d, arestasAtivas=%d)", V, E, activeEdgeCount());
    }
}