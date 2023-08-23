import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SalvarFraseEmArquivo {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("frase que deseja salvar no arquivo: ");
        String frase = scanner.nextLine();

        FileWriter fileWriter = new FileWriter(nomeArquivo);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(frase);
        bufferedWriter.close();
        fileWriter.close();

        System.out.println("Frase salva");
    }
}