import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

public class TPQ02018 {
public static void main(String[] args) {

    // Criação de uma lista para armazenar objetos da classe Jogador
    ArrayList<Jogador> listaJogadores = new ArrayList<>();
    
    try{
        // Leitura do arquivo players.csv
        FileReader leitorArquivo = new FileReader("/tmp/players.csv");
        BufferedReader arquivo = new BufferedReader(leitorArquivo);   
        
        // Ignora a primeira linha (cabeçalho) do arquivo CSV
        arquivo.readLine();
        
        // Lê os dados do arquivo e cria objetos Jogador, adicionando-os à listaJogadores
        while (arquivo.ready()) {
            Jogador jogador = new Jogador();
            jogador.lerDados(arquivo.readLine());
            listaJogadores.add(jogador);
        }
        arquivo.close();
    } catch(Exception e){
       // Captura e imprime mensagens de exceção em caso de erro durante a leitura do arquivo
       System.out.println("Erro ao ler o arquivo: " + e.getMessage() + "\n" + e.getLocalizedMessage());
    }
    
    // Criação de uma lista para armazenar jogadores selecionados pelo usuário
    ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    // Leitura do ID dos jogadores a serem selecionados, termina quando o usuário digita "FIM"
    String id = scanner.nextLine();
    while (!id.equals("FIM")) {
        jogadoresSelecionados.add(listaJogadores.get(Integer.parseInt(id)));
        id = scanner.nextLine();
    }

    // Ordenação parcial da lista de jogadores selecionados usando o algoritmo QuickSort
    quickSortParcial(jogadoresSelecionados, 0, jogadoresSelecionados.size() - 1);
    
    // Remoção dos jogadores excendentes (a partir do 11º) da lista
    int n = 10;
    if(n >= 0 && n < jogadoresSelecionados.size()){
        List<Jogador> subLista = jogadoresSelecionados.subList(n, jogadoresSelecionados.size());
        subLista.clear();
    }
    
    // Impressão dos dados dos jogadores selecionados
    for(int i = 0; i < jogadoresSelecionados.size(); i++){
        jogadoresSelecionados.get(i).imprimirDados();
    }

    scanner.close();
}

// Método para ordenar uma lista de jogadores parcialmente usando o algoritmo QuickSort
public static void quickSortParcial(ArrayList<Jogador> array, int esquerda, int direita){
    int i = esquerda, j = direita;
    
    // Escolha do pivo para comparação
    Jogador pivo = array.get((esquerda + direita) / 2);
    
    // Particionamento do array com base no pivo
    while(i <= j){
        while(compare(array.get(i), pivo) < 0){
            i++;
        }
        while(compare(array.get(j), pivo) > 0){
            j--;
        }
        if(i <= j){
            // Troca de elementos
            Jogador temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
            i++;
            j--;
        }
    }   

    // Chamadas recursivas para ordenar as partições à esquerda e à direita do pivo
    if(esquerda < j){
        quickSortParcial(array, esquerda, j);
    }
    if(i < direita){
        quickSortParcial(array, i, direita);
    }
}

// Método para comparar dois objetos Jogador com base no estado de nascimento e nome
public static int compare(Jogador jogador1, Jogador jogador2){
    // Comparação dos estados de nascimento dos jogadores
    int resultado = jogador1.getEstadoNascimento().compareToIgnoreCase(jogador2.getEstadoNascimento());
    
    // Se os estados de nascimento forem iguais, comparação dos nomes dos jogadores
    if(resultado == 0){
        resultado = jogador1.getNome().compareToIgnoreCase(jogador2.getNome());
    }
    return resultado;
}
}