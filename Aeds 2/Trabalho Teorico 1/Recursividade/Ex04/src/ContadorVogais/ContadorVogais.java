package ContadorVogais;

import java.util.Scanner;

public class ContadorVogais {
    public static int contarVogaisRecursivo(String palavra, int indice) {
        if (indice >= palavra.length()) {
            return 0; // Caso base: chegamos ao final da string
        } else {
            char caractereAtual = Character.toLowerCase(palavra.charAt(indice)); // Convertendo para minúsculas para ser case-insensitive
            if (caractereAtual == 'a' || caractereAtual == 'e' || caractereAtual == 'i' || caractereAtual == 'o' || caractereAtual == 'u') {
                return 1 + contarVogaisRecursivo(palavra, indice + 1); // Contando a vogal atual e continuando a verificação recursiva
            } else {
                return contarVogaisRecursivo(palavra, indice + 1); // Não é vogal, continue a verificação recursiva
            }
        }
    }

    public static int contarVogais(String palavra) {
        return contarVogaisRecursivo(palavra, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma palavra: ");
        String palavra = scanner.next();
        scanner.close();

        int vogais = contarVogais(palavra);
        System.out.println("A palavra tem " + vogais + " vogais.");
    }
}
