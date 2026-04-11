import java.util.*;

/**
 * Grafo não-dirigido representado por lista de adjacência.
 * Vértices internamente indexados de 0 a V-1
 * (a leitura do arquivo converte de 1-based para 0-based).
 */
public class Grafo {

    public final int V;
    private final List<List<Integer>> adj;
    private final List<int[]> arestas; // {u, v} em 0-based

    public Grafo(int v) {
        this.V = v;
        this.adj = new ArrayList<>(v);
        this.arestas = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
    }

    /** Adiciona aresta não-dirigida (0-based). */
    public void adicionarAresta(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
        arestas.add(new int[]{u, v});
    }

    /** Remove UMA ocorrência de (u,v) das listas de adjacência. */
    public void removerAresta(int u, int v) {
        adj.get(u).remove(Integer.valueOf(v));
        adj.get(v).remove(Integer.valueOf(u));
    }

    public List<Integer> vizinhos(int v) { return adj.get(v); }
    public List<int[]>   getArestas()    { return Collections.unmodifiableList(arestas); }

    /** Cópia independente do grafo. */
    public Grafo copiar() {
        Grafo g = new Grafo(V);
        for (int[] a : arestas) g.adicionarAresta(a[0], a[1]);
        return g;
    }

    /**
     * Verifica conectividade via BFS considerando apenas
     * vértices que participam de pelo menos uma aresta.
     */
    public boolean estaConectado() {
        if (arestas.isEmpty()) return true;
        Set<Integer> comAresta = new HashSet<>();
        for (int[] a : arestas) { comAresta.add(a[0]); comAresta.add(a[1]); }

        int inicio = comAresta.iterator().next();
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new ArrayDeque<>();
        visitados.add(inicio);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            for (int viz : adj.get(fila.poll())) {
                if (visitados.add(viz)) fila.add(viz);
            }
        }
        return visitados.containsAll(comAresta);
    }
}
