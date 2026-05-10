import java.io.*;
import java.util.*;

/**
 * Implementação N.03 - Caminho Mínimo
 *
 * Encontra o caminho mínimo (menor peso total) com o menor número de arestas
 * em caso de empate, usando Dijkstra modificado.
 *
 * Formato do arquivo de entrada:
 *   Linha 1: <num_vertices> <num_arestas>
 *   Próximas linhas: <origem> <destino> <peso>
 *   Última linha: <vertice_origem> <vertice_destino>
 *
 * Uso: java CaminhoMinimo <arquivo_grafo>
 */
public class CaminhoMinimo {

    // -----------------------------------------------------------------------
    // Estruturas de dados
    // -----------------------------------------------------------------------

    /** Aresta do grafo dirigido ponderado */
    static class Aresta {
        int destino, peso;
        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso    = peso;
        }
    }

    /**
     * Estado na fila de prioridade: (distancia, numArestas, vertice).
     * Ordenado primeiro por distância, depois por número de arestas.
     */
    static class Estado implements Comparable<Estado> {
        long distancia;
        int  arestas;
        int  vertice;

        Estado(long distancia, int arestas, int vertice) {
            this.distancia = distancia;
            this.arestas   = arestas;
            this.vertice   = vertice;
        }

        @Override
        public int compareTo(Estado outro) {
            if (this.distancia != outro.distancia)
                return Long.compare(this.distancia, outro.distancia);
            return Integer.compare(this.arestas, outro.arestas);
        }
    }

    // -----------------------------------------------------------------------
    // Algoritmo principal
    // -----------------------------------------------------------------------

    /**
     * Dijkstra modificado: minimiza (peso total, número de arestas).
     *
     * @param adj    lista de adjacência
     * @param origem vértice de origem
     * @param n      número de vértices
     * @return array de Estado com a melhor solução para cada vértice
     */
    static Estado[] dijkstra(List<Aresta>[] adj, int origem, int n) {
        long[] dist     = new long[n];
        int[]  numArest = new int[n];
        int[]  anterior = new int[n];

        Arrays.fill(dist,     Long.MAX_VALUE);
        Arrays.fill(numArest, Integer.MAX_VALUE);
        Arrays.fill(anterior, -1);

        dist[origem]     = 0;
        numArest[origem] = 0;

        PriorityQueue<Estado> fila = new PriorityQueue<>();
        fila.add(new Estado(0, 0, origem));

        while (!fila.isEmpty()) {
            Estado atual = fila.poll();
            int u = atual.vertice;

            // Descarta estado desatualizado
            if (atual.distancia > dist[u]) continue;
            if (atual.distancia == dist[u] && atual.arestas > numArest[u]) continue;

            for (Aresta a : adj[u]) {
                int  v       = a.destino;
                long novaDist = dist[u] + a.peso;
                int  novaAr   = numArest[u] + 1;

                boolean melhor = (novaDist < dist[v])
                        || (novaDist == dist[v] && novaAr < numArest[v]);

                if (melhor) {
                    dist[v]     = novaDist;
                    numArest[v] = novaAr;
                    anterior[v] = u;
                    fila.add(new Estado(novaDist, novaAr, v));
                }
            }
        }

        // Empacota resultado
        Estado[] res = new Estado[n];
        for (int i = 0; i < n; i++)
            res[i] = new Estado(dist[i], numArest[i], anterior[i]);
        return res;
    }

    // -----------------------------------------------------------------------
    // Reconstrução do caminho
    // -----------------------------------------------------------------------

    static List<Integer> reconstruirCaminho(int[] anterior, int origem, int destino) {
        List<Integer> caminho = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) {
            caminho.add(v);
            if (v == origem) break;
        }
        Collections.reverse(caminho);
        return caminho;
    }

    // -----------------------------------------------------------------------
    // Leitura do arquivo
    // -----------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    static int[] lerGrafo(String arquivo, List<Aresta>[] adj) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        StringTokenizer st;

        // Linha 1: vértices e arestas
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        // Arestas
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Aresta(v, w));
        }

        // Origem e destino
        st = new StringTokenizer(br.readLine());
        int origem  = Integer.parseInt(st.nextToken());
        int destino = Integer.parseInt(st.nextToken());

        br.close();
        return new int[]{n, origem, destino};
    }

    // -----------------------------------------------------------------------
    // Saída formatada
    // -----------------------------------------------------------------------

    static void exibirResultado(String arquivo, int origem, int destino,
                                 Estado[] resultado, List<Integer> caminho) {
        System.out.println("=".repeat(55));
        System.out.println("  Caminho Mínimo — " + arquivo);
        System.out.println("=".repeat(55));
        System.out.printf("  Origem  : %d%n", origem);
        System.out.printf("  Destino : %d%n", destino);
        System.out.println("-".repeat(55));

        if (resultado[destino].distancia == Long.MAX_VALUE) {
            System.out.println("  Não existe caminho entre " + origem + " e " + destino);
        } else {
            System.out.printf("  Comprimento (peso)   : %d%n", resultado[destino].distancia);
            System.out.printf("  Número de arestas    : %d%n", resultado[destino].arestas);
            System.out.print ("  Vértices do caminho  : ");
            System.out.println(caminho.toString().replace("[","").replace("]","")
                                      .replace(", "," → "));
        }
        System.out.println("=".repeat(55));
    }

    // -----------------------------------------------------------------------
    // Medição de tempo
    // -----------------------------------------------------------------------

    public static ResultadoMedicao executarComMedicao(String arquivo) throws IOException {
        List<Aresta>[] adj = new List[100_001];
        long t0 = System.nanoTime();
        int[] info = lerGrafo(arquivo, adj);
        int n = info[0], origem = info[1], destino = info[2];

        Estado[]      res     = dijkstra(adj, origem, n);
        long tempoNs = System.nanoTime() - t0;

        // Reconstrói anterior para o caminho
        List<Aresta>[] adj2 = new List[n];
        int[] info2 = lerGrafo(arquivo, adj2);
        Estado[] res2 = dijkstra(adj2, origem, n);
        int[] ant = new int[n];
        Arrays.fill(ant, -1);
        // recalcula anterior manualmente
        long[] dist = new long[n]; Arrays.fill(dist, Long.MAX_VALUE);
        int[] na = new int[n]; Arrays.fill(na, Integer.MAX_VALUE);
        dist[origem] = 0; na[origem] = 0;
        PriorityQueue<Estado> fila = new PriorityQueue<>();
        fila.add(new Estado(0,0,origem));
        while (!fila.isEmpty()) {
            Estado cur = fila.poll();
            int u = cur.vertice;
            if (cur.distancia > dist[u]) continue;
            if (cur.distancia == dist[u] && cur.arestas > na[u]) continue;
            for (Aresta a : adj2[u]) {
                int v = a.destino; long nd = dist[u]+a.peso; int nn = na[u]+1;
                if (nd < dist[v] || (nd == dist[v] && nn < na[v])) {
                    dist[v] = nd; na[v] = nn; ant[v] = u;
                    fila.add(new Estado(nd,nn,v));
                }
            }
        }

        List<Integer> caminho = (dist[destino] == Long.MAX_VALUE)
                ? Collections.emptyList()
                : reconstruirCaminho(ant, origem, destino);

        exibirResultado(arquivo, origem, destino, res, caminho);
        return new ResultadoMedicao(dist[destino], na[destino], caminho.size(), tempoNs);
    }

    /** Retorno da medição para benchmarks */
    static class ResultadoMedicao {
        long distancia; int arestas, tamanho; long tempoNs;
        ResultadoMedicao(long d, int a, int t, long ns){ distancia=d; arestas=a; tamanho=t; tempoNs=ns; }
    }

    // -----------------------------------------------------------------------
    // main
    // -----------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: java CaminhoMinimo <arquivo_grafo>");
            System.exit(1);
        }

        String arquivo = args[0];
        List<Aresta>[] adj = new List[100_001];
        int[] info = lerGrafo(arquivo, adj);
        int n = info[0], origem = info[1], destino = info[2];

        // Dijkstra
        long[] dist = new long[n]; Arrays.fill(dist, Long.MAX_VALUE);
        int[]  na   = new int[n];  Arrays.fill(na, Integer.MAX_VALUE);
        int[]  ant  = new int[n];  Arrays.fill(ant, -1);
        dist[origem] = 0; na[origem] = 0;
        PriorityQueue<Estado> fila = new PriorityQueue<>();
        fila.add(new Estado(0, 0, origem));
        while (!fila.isEmpty()) {
            Estado cur = fila.poll();
            int u = cur.vertice;
            if (cur.distancia > dist[u]) continue;
            if (cur.distancia == dist[u] && cur.arestas > na[u]) continue;
            for (Aresta a : adj[u]) {
                int v = a.destino; long nd = dist[u]+a.peso; int nn = na[u]+1;
                if (nd < dist[v] || (nd == dist[v] && nn < na[v])) {
                    dist[v] = nd; na[v] = nn; ant[v] = u;
                    fila.add(new Estado(nd, nn, v));
                }
            }
        }

        Estado[] res = new Estado[n];
        for (int i = 0; i < n; i++) res[i] = new Estado(dist[i], na[i], ant[i]);

        List<Integer> caminho = (dist[destino] == Long.MAX_VALUE)
                ? Collections.emptyList()
                : reconstruirCaminho(ant, origem, destino);

        exibirResultado(arquivo, origem, destino, res, caminho);
    }
}
