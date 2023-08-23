package VogalOuConsoante;

import java.util.Scanner;

public class VogalOuConsoante {
    public static boolean ehVogal(char c) {
        c = Character.toLowerCase(c);
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    public static boolean ehConsoante(char c) {
        c = Character.toLowerCase(c);
        return (c >= 'a' && c <= 'z' && !ehVogal(c));
    }

    public static int contarNaoVogalNaoConsoanteRecursivo(String str, int index) {
        if (index == str.length()) {
            return 0; // Caso base: chegamos ao final da string
        } else {
            char caractereAtual = str.charAt(index);
            if (!ehVogal(caractereAtual) && !ehConsoante(caractereAtual)) {
                return 1 + contarNaoVogalNaoConsoanteRecursivo(str, index + 1); // Contando o caractere atual e continuando a verificação recursiva
            } else {
                return contarNaoVogalNaoConsoanteRecursivo(str, index + 1); // Não é o tipo de caractere desejado, continue a verificação recursiva
            }
        }
    }

    public static int contarNaoVogalNaoConsoante(String str) {
        return contarNaoVogalNaoConsoanteRecursivo(str, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma string: ");
        String entrada = scanner.next();
        scanner.close();

        int quantidade = contarNaoVogalNaoConsoante(entrada);
        System.out.println("A string tem " + quantidade + " caracteres que não são vogais nem consoantes.");
    }
}