import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.lang.System;


     class Jogador{
        private int id;
        private String nome;

      
        public Jogador() {
        }

        
        public Jogador(int id, String nome) {
            setId(id);
            setNome(nome);
        }

       
        public int getId() {
            return id;
        }

        
        public void setId(int id) {
            this.id = id;
        }

      
        public String getNome() {
            return nome;
        }

     
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public static Jogador ler(String line){
           
            String[] atributos = line.split(",");
            int id = Integer.parseInt(atributos[0]);
            String nome = atributos[1];
            
         
            return new Jogador(id, nome);
        }
    }

    public class TP02Q03 {
    public static void main(String args[]) {
        
        long inicio = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String arquivo = "/tmp/players.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine();
            linha = br.readLine();
            while (linha != null) {
                Jogador j = Jogador.ler(linha);
                jogadores.add(j);
                linha = br.readLine();
                }
        }

        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
      
        ArrayList<Jogador> jogadorID = new ArrayList<Jogador>();
        String str = scanner.nextLine();
        int comp = 0;
        while(str.equals("FIM") == false){
            int entrada = Integer.parseInt(str);
            for (Jogador jogador : jogadores) {
                comp++;
                if (jogador.getId() == entrada) {
                    jogadorID.add(jogador);
                    break;
                }
            }
            str = scanner.nextLine();
            }
       
        
        String entrada2 = scanner.nextLine();
       
        while(entrada2.equals("FIM") == false){
            boolean existe = false;
          
            for (Jogador jogador : jogadorID) {
                if (jogador.getNome().equals(entrada2)) {
                    System.out.println("SIM");
                    existe = true;
                    break;
                }
            }
            if(!existe){
                System.out.println("NAO");
            }
            entrada2 = scanner.nextLine();
        }
        scanner.close();
        long fim = System.currentTimeMillis();
        long time = fim - inicio;

        
        String nomeDoArquivo = "matrícula_sequencial.txt";

        try (FileOutputStream fos = new FileOutputStream(nomeDoArquivo);
             BufferedWriter escreva = new BufferedWriter(new OutputStreamWriter(fos))) {

            
            escreva.write("804103\t" +time +"\t" + comp);

            escreva.close();
        } catch (IOException e) {
            System.err.println( e.getMessage());
        }
    }
}