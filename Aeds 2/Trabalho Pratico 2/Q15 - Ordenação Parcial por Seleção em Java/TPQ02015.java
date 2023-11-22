import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0; 
        altura = 0;
        peso = 0;
        anoNascimento = 0;
        nome = "";
        universidade = "";
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

   public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }
    
    public String getUniversidade() {
        return universidade;
    }

     public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void imprimirDados() {
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " 
        + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }

    public Jogador clone() {
        Jogador clone = new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());
        return clone;
    }

    public void lerDados(String dadosJogador) {
        String[] posicao = new String[8];
        int tmp = 0;
        int j = 0;
        for (int i = 0; i < dadosJogador.length(); i++) {
            if (dadosJogador.charAt(i) == ',') {
                posicao[j] = dadosJogador.substring(tmp, i);
                tmp = i + 1;
                if (posicao[j].intern() == "") {
                    posicao[j] = "nao informado";
                }
                j++;
            }
        }
        posicao[7] = dadosJogador.substring(tmp, dadosJogador.length());

        if (posicao[7] == "") {
            posicao[7] = "nao informado";
        }
        setId(Integer.parseInt(posicao[0]));
        setNome(posicao[1]);
        setAltura(Integer.parseInt(posicao[2]));
        setPeso(Integer.parseInt(posicao[3]));
        setUniversidade(posicao[4]);
        setAnoNascimento(Integer.parseInt(posicao[5]));
        setCidadeNascimento(posicao[6]);
        setEstadoNascimento(posicao[7]);
    }
}

public class TPQ02015 {
    public static int totalComparisons = 0; 
    public static int totalSwaps = 0; 

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Jogador> listaJogadores = new ArrayList<>();
        
        try {
            FileReader leitorArquivo = new FileReader("/tmp/players.csv");
            BufferedReader bufferedReader = new BufferedReader(leitorArquivo);
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                Jogador jogador = new Jogador();
                jogador.lerDados(bufferedReader.readLine());
                listaJogadores.add(jogador);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            jogadoresSelecionados.add(listaJogadores.get(Integer.parseInt(id)));
            id = scanner.nextLine();
        }

        selectionSortPartial(jogadoresSelecionados);

        for (int i = 0; i < 10; i++) {
            jogadoresSelecionados.get(i).imprimirDados();
        }

        long endTime = System.currentTimeMillis();
        try {
            FileWriter escritorArquivo = new FileWriter("matricula_selectionParcial.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);
            bufferedWriter.write("Matricula: 804103\tTempo: " + (endTime - startTime) / 1000d +
                                 "\tComparacoes: " + totalComparisons + "\tMovimentacoes: " + totalSwaps);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        scanner.close();
    }

    // Método para ordenar parcialmente a lista de jogadores usando o Selection Sort
    public static void selectionSortPartial(ArrayList<Jogador> array) {
        int tamanho = array.size();
        int elementosOrdenados = 10; // Número de elementos a serem ordenados
        for (int i = 0; i < elementosOrdenados; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < tamanho; j++) {
                totalComparisons++;
                // Compara os nomes dos jogadores ignorando maiúsculas e minúsculas
                if (array.get(j).getNome().compareToIgnoreCase(array.get(indiceMenor).getNome()) < 0) {
                    indiceMenor = j;
                }
            }
            swap(array, indiceMenor, i);
        }
    }

    // Método para trocar dois elementos na lista
    public static void swap(ArrayList<Jogador> array, int i, int j) {
        Jogador temporario = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temporario);
        totalSwaps++;
    }
}
