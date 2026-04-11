import java.io.*;
import java.util.*;

/**
 * Ponto de entrada do programa.
 *
 * Uso:
 *   java Main <caminho_do_arquivo>
 *
 * Formato do arquivo (vértices numerados de 1 a n):
 *   n m
 *   u1 v1
 *   u2 v2
 *   ...
 *   um vm
 */
public class Main {

    // ── Leitura do arquivo ─────────────────────────────────────────

    /**
     * Lê o grafo do arquivo texto.
     * Converte os rótulos 1-based do arquivo para 0-based internamente.
     */
    static Grafo lerGrafo(String caminho) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            // Primeira linha: n m
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Grafo g = new Grafo(n);

            // m linhas seguintes: origem destino  (1-based)
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1; // → 0-based
                int v = Integer.parseInt(st.nextToken()) - 1;
                g.adicionarAresta(u, v);
            }
            return g;
        }
    }

    // ── Formatação da saída ────────────────────────────────────────

    /** Converte lista de pontes (0-based) para strings ordenadas (1-based). */
    static List<String> formatarPontes(List<int[]> pontes) {
        List<String> lista = new ArrayList<>();
        for (int[] p : pontes) {
            int a = Math.min(p[0], p[1]) + 1; // volta para 1-based
            int b = Math.max(p[0], p[1]) + 1;
            lista.add("(" + a + ", " + b + ")");
        }
        lista.sort(Comparator.naturalOrder());
        return lista;
    }

    /** Normaliza para conjunto canônico — usado na verificação de consistência. */
    static Set<String> normalizar(List<int[]> pontes) {
        Set<String> s = new HashSet<>();
        for (int[] p : pontes) {
            int a = Math.min(p[0], p[1]), b = Math.max(p[0], p[1]);
            s.add(a + "-" + b);
        }
        return s;
    }

    // ── Execução e exibição ────────────────────────────────────────

    static void executar(Grafo g) {
        System.out.println("Vértices : " + g.V);
        System.out.println("Arestas  : " + g.getArestas().size());
        System.out.println();

        long t0, dt;

        // ── Naïve ────────────────────────────────────────────────
        t0 = System.nanoTime();
        List<int[]> pNaive = IdentificadorDePontes.pontesNaive(g);
        dt = System.nanoTime() - t0;
        System.out.printf("[Naïve]         %3d ponte(s) encontrada(s)  |  %.3f ms%n",
                pNaive.size(), dt / 1_000_000.0);
        System.out.println("  Pontes: " + formatarPontes(pNaive));

        // ── Tarjan recursivo ─────────────────────────────────────
        t0 = System.nanoTime();
        List<int[]> pRec = IdentificadorDePontes.pontesTarjan(g);
        dt = System.nanoTime() - t0;
        System.out.printf("%n[Tarjan rec.]   %3d ponte(s) encontrada(s)  |  %.3f ms%n",
                pRec.size(), dt / 1_000_000.0);
        System.out.println("  Pontes: " + formatarPontes(pRec));

        // ── Tarjan iterativo ─────────────────────────────────────
        t0 = System.nanoTime();
        List<int[]> pIter = IdentificadorDePontes.pontesTarjanIterativo(g);
        dt = System.nanoTime() - t0;
        System.out.printf("%n[Tarjan iter.]  %3d ponte(s) encontrada(s)  |  %.3f ms%n",
                pIter.size(), dt / 1_000_000.0);
        System.out.println("  Pontes: " + formatarPontes(pIter));

        // ── Verificação de consistência ──────────────────────────
        Set<String> sN = normalizar(pNaive);
        System.out.println();
        System.out.println("Consistência (naïve == tarjan rec.) : "
                + (sN.equals(normalizar(pRec))  ? "OK" : "DIVERGÊNCIA"));
        System.out.println("Consistência (naïve == tarjan iter.): "
                + (sN.equals(normalizar(pIter)) ? "OK" : "DIVERGÊNCIA"));
    }

    // ── Main ───────────────────────────────────────────────────────

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java Main <caminho_do_arquivo>");
            System.exit(1);
        }

        try {
            Grafo g = lerGrafo(args[0]);
            System.out.println("=".repeat(52));
            System.out.println("  IDENTIFICACAO DE PONTES  –  " + args[0]);
            System.out.println("=".repeat(52));
            System.out.println();
            executar(g);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo nao encontrado: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException | NoSuchElementException e) {
            System.err.println("Formato de arquivo invalido: " + e.getMessage());
            System.exit(1);
        }
    }
}
