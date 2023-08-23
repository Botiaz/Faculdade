package Palindromo;

import java.util.Scanner;

public class Palindromo {
    public static boolean isPalindromo(String s) {
        return isPalindromoRecursivo(s, 0, s.length());
    }

    public static boolean isPalindromoRecursivo(String s, int i, int len) {
        boolean resp;
        if (i >= len / 2) {
            resp = true;
        } else if (s.charAt(i) != s.charAt(len - 1 - i)) {
            resp = false;
        } else {
            resp = isPalindromoRecursivo(s, i + 1, len);
        }
        return resp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma palavra: ");
        String palavra = scanner.next();
        scanner.close();

        if (isPalindromo(palavra)) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
    }
}