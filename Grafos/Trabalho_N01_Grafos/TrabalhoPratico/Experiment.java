import java.util.*;

/**
 * Experimentos de desempenho: compara os dois métodos de identificação de pontes
 * (Naïve e Tarjan) aplicados ao algoritmo de Fleury em grafos de diferentes tamanhos.
 *
 * Para grafos não-eulerianos, mede apenas o tempo de identificação de pontes
 * (pois não há caminho Euleriano a percorrer).
 *
 * Cada configuração é repetida NUM_RUNS vezes; reporta-se o tempo médio.
 */
public class Experiment {

    // Número de repetições por configuração
    private static final int NUM_RUNS = 10;

    // Tamanho maximo de vertices para o metodo Naive em qualquer experimento
    private static final int NAIVE_MAX = 1_000;

    // Tamanho maximo de vertices para Fleury com Tarjan
    private static final int TARJAN_FLEURY_MAX = 10_000;

    // Tamanho do grafo usado para exibir resultados funcionais
    private static final int FUNCTIONAL_REPORT_SIZE = 100;

    // Limite de tempo por execução (ms)
    private static final double TIMEOUT_MS = 5_000.0;

    public static void runExperiments() {
        int[] sizes = {100, 1_000, 10_000, 100_000};
        GraphGenerator gen = new GraphGenerator(2024L);

        BridgeFinder[] finders = {
            new NaiveBridgeFinder(),
            new TarjanBridgeFinder()
        };

        runFunctionalReport(gen, finders);

        System.out.println("\n========================================================");
        System.out.println(" EXPERIMENTO 1: Identificação de Pontes (findAllBridges)");
        System.out.println("========================================================");
        printHeader("Tipo", "Vértices", "Arestas", "Método", "Tempo Médio (ms)");

        for (int V : sizes) {
            for (int typeId = 0; typeId < 3; typeId++) {
                Graph g = buildGraph(gen, V, typeId);
                int E = g.E;

                for (BridgeFinder finder : finders) {
                    if (finder instanceof NaiveBridgeFinder && V > NAIVE_MAX) {
                        printRow(typeName(typeId), V, E, finder.getName(), "N/A (lento)");
                        continue;
                    }

                    double avgMs = measureBridgeFinding(g, finder);

                    printRow(
                        typeName(typeId),
                        V,
                        E,
                        finder.getName(),
                        avgMs >= 0 ? String.format("%.3f ms", avgMs) : "N/A (lento)"
                    );
                }
            }
        }

        System.out.println("\n========================================================");
        System.out.println(" EXPERIMENTO 2: Algoritmo de Fleury (caminho completo)");
        System.out.println("========================================================");
        printHeader("Tipo", "Vértices", "Arestas", "Método", "Tempo Médio (ms)");

        for (int V : sizes) {
            for (int typeId = 0; typeId < 2; typeId++) {
                Graph g = buildGraph(gen, V, typeId);
                int E = g.E;

                for (BridgeFinder finder : finders) {
                    if (finder instanceof NaiveBridgeFinder && V > NAIVE_MAX) {
                        printRow(typeName(typeId), V, E, finder.getName(), "N/A (lento)");
                        continue;
                    }

                    if (V > TARJAN_FLEURY_MAX) {
                        printRow(typeName(typeId), V, E, finder.getName(), "N/A (impraticável)");
                        continue;
                    }

                    double avgMs = measureFleury(g, finder);

                    printRow(
                        typeName(typeId),
                        V,
                        E,
                        finder.getName(),
                        avgMs >= 0 ? String.format("%.3f ms", avgMs) : "N/A (lento)"
                    );
                }
            }

            Graph gNe = buildGraph(gen, V, 2);
            for (BridgeFinder finder : finders) {
                printRow(typeName(2), V, gNe.E, finder.getName(), "N/A (não Euleriano)");
            }
        }
    }

    private static void runFunctionalReport(GraphGenerator gen, BridgeFinder[] finders) {
        System.out.println("\n========================================================");
        System.out.println(" RESULTADOS FUNCIONAIS (pontes e caminho euleriano)");
        System.out.println("========================================================");

        for (int typeId = 0; typeId < 3; typeId++) {
            Graph g = buildGraph(gen, FUNCTIONAL_REPORT_SIZE, typeId);

            System.out.printf("\nTipo: %s | V=%d | E=%d%n", typeName(typeId), g.V, g.E);
            System.out.println("Classificação Euleriana: " + FleuryAlgorithm.classify(g));

            Set<Integer> baseBridges = null;

            for (BridgeFinder finder : finders) {
                Set<Integer> bridges = finder.findAllBridges(g);
                System.out.printf("Pontes (%s): %s%n", finder.getName(), bridgesToString(g, bridges));

                if (baseBridges == null) {
                    baseBridges = bridges;
                } else {
                    System.out.println("Concorda com o método anterior? " + baseBridges.equals(bridges));
                }

                FleuryAlgorithm fleury = new FleuryAlgorithm(finder);
                List<Integer> path = fleury.findEulerianPath(g);

                System.out.printf("Caminho Euleriano (%s): %s%n", finder.getName(), pathToString(path));

                if (path != null) {
                    System.out.printf(
                        "Arestas percorridas (%s): %d / %d%n",
                        finder.getName(),
                        path.size() - 1,
                        g.E
                    );
                }
            }
        }
    }

    private static double measureBridgeFinding(Graph original, BridgeFinder finder) {
        double total = 0;

        for (int r = 0; r < NUM_RUNS; r++) {
            Graph g = original.copy();

            long t0 = System.nanoTime();
            finder.findAllBridges(g);
            long t1 = System.nanoTime();

            double elapsedMs = (t1 - t0) / 1_000_000.0;

            if (elapsedMs > TIMEOUT_MS) {
                return -1;
            }

            total += elapsedMs;
        }

        return total / NUM_RUNS;
    }

    private static double measureFleury(Graph original, BridgeFinder finder) {
        FleuryAlgorithm fleury = new FleuryAlgorithm(finder);
        double total = 0;

        for (int r = 0; r < NUM_RUNS; r++) {
            long t0 = System.nanoTime();
            fleury.findEulerianPath(original);
            long t1 = System.nanoTime();

            double elapsedMs = (t1 - t0) / 1_000_000.0;

            if (elapsedMs > TIMEOUT_MS) {
                return -1;
            }

            total += elapsedMs;
        }

        return total / NUM_RUNS;
    }

    private static Graph buildGraph(GraphGenerator gen, int V, int typeId) {
        switch (typeId) {
            case 0:
                return gen.generateEulerian(V);
            case 1:
                return gen.generateSemiEulerian(V);
            default:
                return gen.generateNonEulerian(V);
        }
    }

    private static String typeName(int id) {
        switch (id) {
            case 0:
                return "Euleriano";
            case 1:
                return "Semi-Euler.";
            default:
                return "Não-Euler.";
        }
    }

    private static String bridgesToString(Graph g, Set<Integer> bridgeIds) {
        if (bridgeIds.isEmpty()) return "[]";

        List<Integer> sorted = new ArrayList<>(bridgeIds);
        Collections.sort(sorted);

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < sorted.size(); i++) {
            int id = sorted.get(i);

            if (i > 0) sb.append(", ");

            sb.append(id)
              .append(":")
              .append(g.eu[id])
              .append("--")
              .append(g.ev[id]);
        }

        sb.append("]");
        return sb.toString();
    }

    private static String pathToString(List<Integer> path) {
        if (path == null) {
            return "não existe (grafo não euleriano)";
        }

        if (path.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(path.get(i));
        }

        return sb.toString();
    }

    private static void printHeader(String... cols) {
        System.out.printf("%-14s %-10s %-10s %-10s %-20s%n", (Object[]) cols);
        System.out.println("-".repeat(66));
    }

    private static void printRow(String tipo, int V, int E, String method, String time) {
        System.out.printf(
            "%-14s %-10d %-10d %-10s %-20s%n",
            tipo,
            V,
            E,
            method,
            time
        );
    }
}