import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        File file = new File(nomeArquivo);

        if (!file.exists()) {
            System.out.println("Arquivo não encontrado.");
            return;
        }

        Scanner fileScanner = new Scanner(file);

        System.out.println("Conteúdo do arquivo:");

        while (fileScanner.hasNextLine()) {
            String linha = fileScanner.nextLine();
            System.out.println(linha.toUpperCase());
        }

        fileScanner.close();
    }
}