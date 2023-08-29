package TP01Q06;

import java.util.Scanner;

public class Vogal {
    
    static boolean isVogal(String entrada) {
        for (int i = 0; i < entrada.length(); i++) {
            char c = Character.toUpperCase(entrada.charAt(i));
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                return false;
            }
        }
        return true;
    }
    
    static boolean isConsoante(String entrada) {
        for (int i = 0; i < entrada.length(); i++) {
            char c = Character.toUpperCase(entrada.charAt(i));
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    
    static boolean isInteiro(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    static boolean isReal(String entrada) {
        try {
            Double.parseDouble(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        
        boolean vogal = isVogal(entrada);
        boolean consoante = isConsoante(entrada);
        boolean inteiro = isInteiro(entrada);
        boolean real = isReal(entrada);

        System.out.println((vogal ? "SIM" : "NAO") + " " + (consoante ? "SIM" : "NAO") + " " +
                (inteiro ? "SIM" : "NAO") + " " + (real ? "SIM" : "NAO"));
    }
}