import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo de origem: ");
        String nomeArquivoOrigem = scanner.nextLine();

        File fileOrigem = new File(nomeArquivoOrigem);

        if (!fileOrigem.exists()) {
            System.out.println("Arquivo de origem não encontrado.");
            return;
        }

        System.out.print("Digite o nome do arquivo de destino: ");
        String nomeArquivoDestino = scanner.nextLine();

        File fileDestino = new File(nomeArquivoDestino);

        Scanner fileScanner = new Scanner(fileOrigem);
        FileWriter writer = new FileWriter(fileDestino);

        System.out.println("Conteúdo do arquivo de origem invertido e salvo no destino:");

        while (fileScanner.hasNextLine()) {
            String linha = fileScanner.nextLine();
            String linhaInvertida = reverseString(linha);
            System.out.println(linhaInvertida);
            writer.write(linhaInvertida + "\n"); 
        }

        fileScanner.close();
        writer.close();

        System.out.println("Conteúdo invertido e salvo com sucesso no arquivo de destino.");
    }

    public static String reverseString(String input) {
        StringBuilder reversed = new StringBuilder(input);
        return reversed.reverse().toString();
    }
}