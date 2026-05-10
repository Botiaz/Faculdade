import java.io.*;
import java.util.*;

/**
 * Benchmark – executa todos os grafos de teste e exibe tabela de resultados.
 * Uso: java Benchmark
 */
public class Benchmark {

    // Roda o Dijkstra embutido (sem chamar processo externo)
    static long[] rodarDijkstra(String arquivo) throws IOException {
        List<CaminhoMinimo.Aresta>[] adj = new List[200_001];
        int[] info = CaminhoMinimo.lerGrafo(arquivo, adj);
        int n = info[0], origem = info[1], destino = info[2];

        long[] dist = new long[n]; Arrays.fill(dist, Long.MAX_VALUE);
        int[]  na   = new int[n];  Arrays.fill(na, Integer.MAX_VALUE);
        dist[origem] = 0; na[origem] = 0;

        PriorityQueue<CaminhoMinimo.Estado> fila = new PriorityQueue<>();
        fila.add(new CaminhoMinimo.Estado(0, 0, origem));

        long t0 = System.nanoTime();

        while (!fila.isEmpty()) {
            CaminhoMinimo.Estado cur = fila.poll();
            int u = cur.vertice;
            if (cur.distancia > dist[u]) continue;
            if (cur.distancia == dist[u] && cur.arestas > na[u]) continue;
            for (CaminhoMinimo.Aresta a : adj[u]) {
                int v = a.destino; long nd = dist[u] + a.peso; int nn = na[u] + 1;
                if (nd < dist[v] || (nd == dist[v] && nn < na[v])) {
                    dist[v] = nd; na[v] = nn;
                    fila.add(new CaminhoMinimo.Estado(nd, nn, v));
                }
            }
        }

        long tempoMs = (System.nanoTime() - t0) / 1_000_000;
        long distRes = dist[destino] == Long.MAX_VALUE ? -1 : dist[destino];
        int  arestRes = na[destino] == Integer.MAX_VALUE ? -1 : na[destino];
        return new long[]{n, distRes, arestRes, tempoMs};
    }

    static String fmt(long v) { return v < 0 ? "N/A" : String.valueOf(v); }

    public static void main(String[] args) throws IOException {
        String[][] grafos = {
            {"tipo1_pequeno",  "grafos/tipo1_pequeno.txt"},
            {"tipo1_medio",    "grafos/tipo1_medio.txt"},
            {"tipo1_grande",   "grafos/tipo1_grande.txt"},
            {"tipo1_enorme",   "grafos/tipo1_enorme.txt"},
            {"tipo2_pequeno",  "grafos/tipo2_pequeno.txt"},
            {"tipo2_medio",    "grafos/tipo2_medio.txt"},
            {"tipo2_grande",   "grafos/tipo2_grande.txt"},
            {"tipo2_enorme",   "grafos/tipo2_enorme.txt"},
        };

        // ── Tabela Tipo 1 ────────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║       TABELA 1 — Grafo Aleatório Esparso                         ║");
        System.out.println("╠═══════════════╦═══════════╦════════════════╦═══════════╦══════════╣");
        System.out.println("║  Instância    ║ Vértices  ║  Dist. mínima  ║  Arestas  ║ Tempo(ms)║");
        System.out.println("╠═══════════════╬═══════════╬════════════════╬═══════════╬══════════╣");

        for (int i = 0; i < 4; i++) {
            String nome = grafos[i][0];
            String arq  = grafos[i][1];
            try {
                long[] r = rodarDijkstra(arq);
                System.out.printf("║  %-13s║  %7d  ║   %12s ║  %7s  ║  %6d  ║%n",
                        nome, r[0], fmt(r[1]), fmt(r[2]), r[3]);
            } catch (Exception e) {
                System.out.printf("║  %-13s║   ERRO: %-40s ║%n", nome, e.getMessage());
            }
        }
        System.out.println("╚═══════════════╩═══════════╩════════════════╩═══════════╩══════════╝");

        // ── Tabela Tipo 2 ────────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║       TABELA 2 — Grafo em Grade (Grid)                           ║");
        System.out.println("╠═══════════════╦═══════════╦════════════════╦═══════════╦══════════╣");
        System.out.println("║  Instância    ║ Vértices  ║  Dist. mínima  ║  Arestas  ║ Tempo(ms)║");
        System.out.println("╠═══════════════╬═══════════╬════════════════╬═══════════╬══════════╣");

        for (int i = 4; i < 8; i++) {
            String nome = grafos[i][0];
            String arq  = grafos[i][1];
            try {
                long[] r = rodarDijkstra(arq);
                System.out.printf("║  %-13s║  %7d  ║   %12s ║  %7s  ║  %6d  ║%n",
                        nome, r[0], fmt(r[1]), fmt(r[2]), r[3]);
            } catch (Exception e) {
                System.out.printf("║  %-13s║   ERRO: %-40s ║%n", nome, e.getMessage());
            }
        }
        System.out.println("╚═══════════════╩═══════════╩════════════════╩═══════════╩══════════╝\n");
    }
}
