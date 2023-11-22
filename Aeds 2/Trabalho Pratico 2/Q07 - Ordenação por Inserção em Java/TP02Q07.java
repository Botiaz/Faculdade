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

public class TP02Q07 {
    public static void main(String[] args) {
        // Obtendo o tempo de início da execução do programa
        long inicioTempo = System.currentTimeMillis();
        
        // Criando uma lista para armazenar os jogadores do arquivo
        ArrayList<Jogador> listaJogadores = new ArrayList<>();

        try {
            // Lendo dados do arquivo players.csv e populando a lista de jogadores
            FileReader leitorArquivo = new FileReader("/tmp/players.csv");
            BufferedReader arquivo = new BufferedReader(leitorArquivo);   
            arquivo.readLine(); // Ignorando a primeira linha (cabeçalho)
            while (arquivo.ready()) {
                Jogador jogador = new Jogador();
                jogador.lerDados(arquivo.readLine());
                listaJogadores.add(jogador);
            }
            arquivo.close();
        } catch(Exception e){
            // Lidando com exceções de leitura do arquivo
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        
        // Criando uma lista para armazenar jogadores selecionados pelo usuário
        ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            // Adicionando jogadores à lista de jogadores selecionados
            jogadoresSelecionados.add(listaJogadores.get(Integer.parseInt(id)));
            id = scanner.nextLine();
        }

        // Chamando o algoritmo de ordenação e obtendo estatísticas de comparações e movimentações
        int[] contador = insertionSort(jogadoresSelecionados);

        // Exibindo os dados dos jogadores ordenados
        for (int i = 0; i < jogadoresSelecionados.size(); i++) {
            jogadoresSelecionados.get(i).imprimirDados();
        }

        // Obtendo o tempo de término da execução do programa
        long fimTempo = System.currentTimeMillis();
        try {
            // Escrevendo estatísticas no arquivo de saída
            FileWriter escritorArquivo = new FileWriter("matricula_insercao.txt");
            BufferedWriter arqWriter = new BufferedWriter(escritorArquivo);
            arqWriter.write("Matricula: 807094\tTempo: " + (fimTempo - inicioTempo) / 1000d + "\tComparacoes: " + contador[0] + "\tMovimentacoes: " + contador[1]);
            arqWriter.close();
        } catch(Exception e){
            // Lidando com exceções ao escrever no arquivo
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        scanner.close();
    }

    // Algoritmo de ordenação por inserção
    public static int[] insertionSort(ArrayList<Jogador> insercao){
        int tamanho = insercao.size();
        int comparacoes = 0;
        int movimentacoes = 0;

        for(int i = 1; i < tamanho; i++){
            Jogador temporario = insercao.get(i);
            int j = i - 1;
            comparacoes++;
            while(j >= 0 && insercao.get(j).getAnoNascimento() > temporario.getAnoNascimento()){
                // Movendo jogadores para a direita para abrir espaço para inserção
                insercao.set(j + 1, insercao.get(j));
                j--;
            }

            while(j >= 0 && insercao.get(j).getAnoNascimento() == temporario.getAnoNascimento() && insercao.get(j).getNome().compareTo(temporario.getNome()) > 0){
                // Movendo jogadores para a direita se o ano de nascimento for o mesmo, mas o nome for maior em ordem lexicográfica
                insercao.set(j + 1, insercao.get(j));
                j--;
                movimentacoes += 2;
            }
            // Inserindo o jogador na posição correta
            insercao.set(j + 1, temporario);
            movimentacoes++;
        }
        // Retornando o número de comparações e movimentações
        return new int[] {comparacoes, movimentacoes};
    }
}

