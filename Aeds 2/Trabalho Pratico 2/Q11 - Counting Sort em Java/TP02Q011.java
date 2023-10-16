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

public class TP02Q011 {
    public static void main(String[] args) {
        // Obter o tempo inicial para medir o desempenho
        long tempoInicial = System.currentTimeMillis();
        
        // Inicializar uma lista de jogadores
        ArrayList<Jogador> listaJogadores = new ArrayList<>();
        
        try {
            // Ler dados do arquivo players.csv
            FileReader leitorArquivo = new FileReader("/tmp/playersAtualizado.csv");
            BufferedReader arquivo = new BufferedReader(leitorArquivo);
            arquivo.readLine(); // Ignorar cabeçalho do arquivo
            while (arquivo.ready()) {
                Jogador jogador = new Jogador();
                jogador.lerDados(arquivo.readLine());
                listaJogadores.add(jogador);
            }
            arquivo.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + ex.getLocalizedMessage());
        }
        
        // Inicializar uma lista para armazenar jogadores selecionados pelo usuário
        ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        
        // Ler IDs dos jogadores fornecidos pelo usuário até que "FIM" seja inserido
        while (!id.equals("FIM")) {
            int n = Integer.parseInt(id);
            if (n >= 0 && n < listaJogadores.size()) {
                jogadoresSelecionados.add(listaJogadores.get(n));
            } else {
                System.out.println("Índice inválido, insira um índice válido ou 'FIM' para encerrar.");
            }
            id = scanner.nextLine();
        }
        scanner.close();
        
        // Realizar a ordenação usando o algoritmo Counting Sort e contar comparações e movimentações
        int[] contador = countingSort(jogadoresSelecionados);
        
        // Obter o tempo final e calcular o tempo total
        long tempoFinal = System.currentTimeMillis();
        
        try {
            // Escrever os resultados no arquivo matricula_countingsort.txt
            FileWriter escritorArquivo = new FileWriter("matricula_countingsort.txt");
            BufferedWriter arqWriter = new BufferedWriter(escritorArquivo);
            arqWriter.write("Matrícula: 804103\tTempo: " + (tempoFinal - tempoInicial) / 1000d +
                    "\tComparações: " + contador[0] + "\tMovimentações: " + contador[1]);
            arqWriter.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + ex.getLocalizedMessage());
        }
    }
    
    public static int getMaiorAltura(ArrayList<Jogador> array) {
        int maiorAltura = array.get(0).getAltura();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getAltura() > maiorAltura)
                maiorAltura = array.get(i).getAltura();
        }
        return maiorAltura;
    }
    
    public static int[] countingSort(ArrayList<Jogador> array1) {
        // Inicializar contadores para comparações e movimentações
        int comparacoes = 0;
        int movimentacoes = 0;
    
        // Inicializar arrays auxiliares
        int[] indicesOrdenados = new int[array1.size()];
        int[] contadorAlturas = new int[getMaiorAltura(array1)];
        ArrayList<Jogador> ordenado = new ArrayList<>();
    
        // Contar a frequência de cada altura na lista de jogadores
        for (int i = 0; i < array1.size(); i++) {
            contadorAlturas[array1.get(i).getAltura() - 1] += 1;
        }
    
        // Calcular as posições finais de cada altura na lista ordenada
        for (int i = 1; i < contadorAlturas.length; i++) {
            contadorAlturas[i] += contadorAlturas[i - 1];
        }
    
        // Mapear jogadores para as posições corretas na lista ordenada usando contadorAlturas
        for (int i = array1.size() - 1; i >= 0; i--) {
            indicesOrdenados[contadorAlturas[array1.get(i).getAltura() - 1] - 1] = i;
            contadorAlturas[array1.get(i).getAltura() - 1] -= 1;
        }
    
        // Mover jogadores ordenados de array1 para a lista ordenada, contar movimentações
        for (int i = 0; i < indicesOrdenados.length; i++) {
            ordenado.add(array1.get(indicesOrdenados[i]));
            movimentacoes++;
        }
    
        // Comparar e ordenar jogadores com a mesma altura por nome usando o método de seleção
        for (int i = 0; i < ordenado.size(); i++) {
            for (int j = i + 1; j < ordenado.size(); j++) {
                comparacoes++;
                if (ordenado.get(i).getAltura() == ordenado.get(j).getAltura()) {
                    comparacoes++;
                    if (ordenado.get(i).getNome().compareToIgnoreCase(ordenado.get(j).getNome()) > 0) {
                        comparacoes++;
                        Jogador temp = ordenado.get(i);
                        ordenado.set(i, ordenado.get(j));
                        ordenado.set(j, temp);
                        movimentacoes += 3;
                    }
                }
            }
        }
    
        // Imprimir dados ordenados (opcional, dependendo dos requisitos)
        for (int i = 0; i < ordenado.size(); i++) {
            ordenado.get(i).imprimirDados();
        }
    
        // Retornar o número de comparações e movimentações
        return new int[]{comparacoes, movimentacoes};
    }
    
}