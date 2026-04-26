import java.util.HashSet;
import java.util.Set;

/**
 * Bridge detection using the naive method.
 *
 * For each active edge, remove it temporarily and verify whether
 * the graph remains connected via BFS. If not, the edge is a bridge.
 *
 * Complexity: O(E * (V + E)) per findAllBridges call.
 */
public class NaiveBridgeFinder implements BridgeFinder {

    /**
     * Checks whether edgeId is a bridge in the current active subgraph.
     * Complexity: O(V + E)
     */
    @Override
    public boolean isBridge(Graph g, int edgeId) {
        if (!g.active[edgeId]) return false;

        int u = g.eu[edgeId];
        int v = g.ev[edgeId];

        // Temporarily remove the edge.
        g.deactivate(edgeId);

        // BFS from u: is v still reachable?
        boolean[] reachable = g.bfs(u);
        boolean bridge = !reachable[v];

        // Restore the edge.
        g.activate(edgeId);

        return bridge;
    }

    /**
     * Finds all bridges by testing each edge individually.
     * Complexity: O(E * (V + E))
     */
    @Override
    public Set<Integer> findAllBridges(Graph g) {
        Set<Integer> bridges = new HashSet<>();
        for (int id = 0; id < g.E; id++) {
            if (g.active[id] && isBridge(g, id)) {
                bridges.add(id);
            }
        }
        return bridges;
    }

    @Override
    public String getName() { return "Naive"; }
}