import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Comparator;

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

    public static Comparator<Jogador> CompUniversidade = new Comparator<Jogador>() {
        @Override
        public int compare(Jogador jogador1, Jogador jogador2) {
            int resp = jogador1.getUniversidade().compareTo(jogador2.getUniversidade());
            if (resp == 0) {
                return jogador1.getNome().compareTo(jogador2.getNome());
            }
            return resp;
        }
    };

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

public class TPQ02013 {
	public static int comparacao = 0;
	public static int movimentacoes = 0;
	public static void main(String[] args) {
    // Obtém o tempo inicial para calcular a duração do processo
    long tempoInicial = System.currentTimeMillis();
    // Cria uma lista para armazenar os jogadores
    ArrayList<Jogador> jogadores = new ArrayList<>();

    try {
        // Lê dados do arquivo e cria objetos Jogador
        FileReader leitorArquivo = new FileReader("/tmp/players.csv");
        BufferedReader arquivo = new BufferedReader(leitorArquivo);   
        arquivo.readLine(); // Ignora a primeira linha (cabeçalho)
        while (arquivo.ready()) {
            Jogador jogador = new Jogador();
            jogador.lerDados(arquivo.readLine());
            jogadores.add(jogador);
        }
        arquivo.close();
    } catch(Exception excecao) {
        // Captura e imprime qualquer exceção que ocorra durante a leitura do arquivo
        System.out.println(excecao.getMessage() + "\n" + excecao.getLocalizedMessage());
    }

    // Lista para armazenar jogadores selecionados pelo usuário
    ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String id = scanner.nextLine();
    // Lê IDs dos jogadores até que o usuário insira "FIM"
    while (!id.equals("FIM")) {
        jogadoresSelecionados.add(jogadores.get(Integer.parseInt(id)));
        id = scanner.nextLine();
    }

    // Chama o algoritmo de ordenação MergeSort
    MergeSort(jogadoresSelecionados, Jogador.CompUniversidade);

    // Imprime os jogadores ordenados
    for(int i = 0; i < jogadoresSelecionados.size(); i++) {
        jogadoresSelecionados.get(i).imprimirDados();
    }

    // Obtém o tempo final e escreve as estatísticas no arquivo de saída
    long tempoFinal = System.currentTimeMillis();
    try {
        FileWriter escritorArquivo = new FileWriter("matricula_mergesort.txt");
        BufferedWriter arqEscritor = new BufferedWriter(escritorArquivo);
        // Escreve informações no arquivo: Matrícula, Tempo, Comparacoes e Movimentacoes
        arqEscritor.write("Matricula: 804103\tTempo: " + (tempoFinal - tempoInicial) / 1000d + "\tComparacoes: " + comparacao + "\tMovimentacoes: " + movimentacoes);
        arqEscritor.close();
    } catch(Exception excecao) {
        // Captura e imprime qualquer exceção que ocorra ao escrever no arquivo
        System.out.println(excecao.getMessage() + "\n" + excecao.getLocalizedMessage());
    }
    scanner.close();
}

// Implementação do algoritmo de ordenação MergeSort
public static void MergeSort(ArrayList<Jogador> array, Comparator<Jogador> comparador) {
    // Verifica se a lista possui mais de um elemento
    if(array.size() > 1) {
        comparacao++;
        // Calcula o índice do meio da lista
        int meio = array.size() / 2;
        // Divide a lista em duas partes: esquerda e direita
        ArrayList<Jogador> esquerda = new ArrayList<>(array.subList(0, meio));
        ArrayList<Jogador> direita = new ArrayList<>(array.subList(meio, array.size()));
        // Chama recursivamente o MergeSort para ambas as partes
        MergeSort(esquerda, comparador);
        movimentacoes++;
        MergeSort(direita, comparador);
        movimentacoes++;
        // Inicializa índices para percorrer as listas esquerda e direita
        int i = 0, j = 0, k = 0;
        // Combina as listas ordenadas de volta na lista original
        while(i < esquerda.size() && j < direita.size()) {
            comparacao++;
            // Compara os elementos das listas esquerda e direita
            if(comparador.compare(esquerda.get(i), direita.get(j)) < 0) {
                comparacao++;
                // Se o elemento da esquerda é menor, insere na lista original
                array.set(k++, esquerda.get(i++));
            } else {
                comparacao++;
                // Se o elemento da direita é menor, insere na lista original
                array.set(k++, direita.get(j++));
            }
        }

        // Adiciona quaisquer elementos restantes da lista esquerda
        while(i < esquerda.size()) {
            comparacao++;
            array.set(k++, esquerda.get(i++));
        }
        // Adiciona quaisquer elementos restantes da lista direita
        while(j < direita.size()) {
            comparacao++;
            array.set(k++, direita.get(j++));
        }
    }
}
}