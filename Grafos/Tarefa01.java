import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tarefa01 {

    static class Grafo {
        int n; // número de vértices
        int m; // número de arestas

        List<Integer>[] adjOut; // lista de sucessores (arestas de saída)
        List<Integer>[] adjIn;  // lista de predecessores (arestas de entrada)

        @SuppressWarnings("unchecked")
        public Grafo(int n) {
            this.n = n;
            adjOut = new ArrayList[n + 1]; // índice de 1 a n
            adjIn  = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                adjOut[i] = new ArrayList<>();
                adjIn[i]  = new ArrayList<>();
            }
        }

        public void adicionarAresta(int origem, int destino) {
            adjOut[origem].add(destino); // saída
            adjIn[destino].add(origem);  // entrada
            m++;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Entrada do usuário
        System.out.print("Digite o nome do arquivo do grafo: ");
        String nomeArquivo = sc.nextLine();

        System.out.print("Digite o número do vértice: ");
        int verticeConsulta = sc.nextInt();

        try {
            Grafo grafo = lerGrafo(nomeArquivo);

            if (verticeConsulta < 1 || verticeConsulta > grafo.n) {
                System.out.println("Vértice inválido!");
                return;
            }

            // Grau de saída
            int grauSaida = grafo.adjOut[verticeConsulta].size();

            // Grau de entrada
            int grauEntrada = grafo.adjIn[verticeConsulta].size();

            // Sucessores
            List<Integer> sucessores = grafo.adjOut[verticeConsulta];

            // Predecessores
            List<Integer> predecessores = grafo.adjIn[verticeConsulta];

            // Saída
            System.out.println("\n=== Informações do vértice " + verticeConsulta + " ===");
            System.out.println("Grau de saída: " + grauSaida);
            System.out.println("Grau de entrada: " + grauEntrada);

            System.out.println("Sucessores: " + sucessores);
            System.out.println("Predecessores: " + predecessores);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
            e.printStackTrace();
        }

        sc.close();
    }

    // Leitura do arquivo
    public static Grafo lerGrafo(String nomeArquivo) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));

        // Primeira linha: n m
        String linha = br.readLine();
        String[] partes = linha.trim().split("\\s+");

        int n = Integer.parseInt(partes[0]);
        int m = Integer.parseInt(partes[1]);

        Grafo grafo = new Grafo(n);

        // Leitura das arestas
        for (int i = 0; i < m; i++) {
            linha = br.readLine();
            if (linha == null) break;

            partes = linha.trim().split("\\s+");
            int origem = Integer.parseInt(partes[0]);
            int destino = Integer.parseInt(partes[1]);

            grafo.adicionarAresta(origem, destino);
        }

        br.close();
        return grafo;
    }
}