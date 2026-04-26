import java.util.Set;

/** Contrato para algoritmos de identificação de pontes em grafos. */
public interface BridgeFinder {

    /**
     * Verifica se uma aresta ativa específica é ponte no estado atual do grafo.
     */
    boolean isBridge(Graph g, int edgeId);

    /**
     * Retorna o conjunto de IDs de todas as arestas-ponte ativas no grafo.
     */
    Set<Integer> findAllBridges(Graph g);

    /** Nome curto do método (ex.: Naive, Tarjan). */
    String getName();
}