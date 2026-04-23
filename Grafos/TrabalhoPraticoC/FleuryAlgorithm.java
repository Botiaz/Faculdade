import java.util.*;

/**
 * Algoritmo de Fleury para encontrar caminhos/circuitos Eulerianos.
 *
 * Condições para existência:
 *   - Circuito Euleriano: grafo conexo, todos os vértices com grau par.
 *   - Caminho Euleriano: grafo conexo, exatamente 2 vértices com grau ímpar.
 *
 * Estratégia de Fleury: a cada passo, a partir do vértice atual, escolhe uma
 * aresta que NÃO seja ponte — a menos que seja a única opção disponível.
 * Isso garante que o algoritmo nunca "se corte" do restante do grafo.
 *
 * A detecção de pontes é delegada ao BridgeFinder injetado (Naive ou Tarjan).
 */
public class FleuryAlgorithm {

    public enum EulerianType { CIRCUIT, PATH, NONE }

    private final BridgeFinder bridgeFinder;

    public FleuryAlgorithm(BridgeFinder bridgeFinder) {
        this.bridgeFinder = bridgeFinder;
    }

    /**
     * Classifica o tipo Euleriano do grafo (usando os graus ativos atuais).
     */
    public static EulerianType classify(Graph g) {
        if (!g.isConnected()) return EulerianType.NONE;
        int[] deg = g.activeDegrees();
        int odd = 0;
        for (int d : deg) if (d % 2 == 1) odd++;
        if (odd == 0) return EulerianType.CIRCUIT;
        if (odd == 2) return EulerianType.PATH;
        return EulerianType.NONE;
    }

    /**
     * Encontra um caminho/circuito Euleriano usando o algoritmo de Fleury.
     *
     * @param original grafo original (não é modificado — usa cópia interna)
     * @return lista de vértices do caminho/circuito, ou null se não Euleriano
     */
    public List<Integer> findEulerianPath(Graph original) {
        EulerianType type = classify(original);
        if (type == EulerianType.NONE) return null;

        // Trabalha em cópia para não modificar o grafo original
        Graph g = original.copy();

        // Vértice de início
        int start = chooseStart(g, type);

        List<Integer> path = new ArrayList<>();
        path.add(start);
        int current = start;

        while (g.activeDegree(current) > 0) {
            // Encontra todas as pontes no estado atual do grafo
            Set<Integer> bridges = bridgeFinder.findAllBridges(g);

            // Procura uma aresta não-ponte a partir de current
            int chosenEdge   = -1;
            int chosenVertex = -1;
            int fallbackEdge   = -1;
            int fallbackVertex = -1;

            for (int[] e : g.adj[current]) {
                int v = e[0], eid = e[1];
                if (!g.active[eid]) continue;

                if (fallbackEdge == -1) {
                    fallbackEdge   = eid;
                    fallbackVertex = v;
                }

                if (!bridges.contains(eid)) {
                    chosenEdge   = eid;
                    chosenVertex = v;
                    break;
                }
            }

            // Se não há aresta não-ponte, usa a ponte como último recurso
            if (chosenEdge == -1) {
                chosenEdge   = fallbackEdge;
                chosenVertex = fallbackVertex;
            }

            // Atravessa a aresta escolhida
            g.deactivate(chosenEdge);
            path.add(chosenVertex);
            current = chosenVertex;
        }

        return path;
    }

    /** Escolhe o vértice inicial de acordo com o tipo Euleriano. */
    private int chooseStart(Graph g, EulerianType type) {
        int[] deg = g.activeDegrees();
        if (type == EulerianType.PATH) {
            // Começa em um dos dois vértices de grau ímpar
            for (int u = 0; u < g.V; u++) {
                if (deg[u] % 2 == 1) return u;
            }
        }
        // Circuito: qualquer vértice com aresta
        for (int u = 0; u < g.V; u++) {
            if (deg[u] > 0) return u;
        }
        return 0;
    }
} 
