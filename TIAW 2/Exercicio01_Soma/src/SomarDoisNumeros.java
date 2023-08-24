

import java.util.Scanner;

public class SomarDoisNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número inteiro: ");
        int numero1 = scanner.nextInt();

        System.out.print("Digite o segundo número inteiro: ");
        int numero2 = scanner.nextInt();

        int resultado = somarNumeros(numero1, numero2);

        System.out.println("A soma de " + numero1 + " e " + numero2 + " é igual a " + resultado);
        
        // Feche o scanner para evitar vazamentos de recursos
        scanner.close();
    }

    public static int somarNumeros(int a, int b) {
        return a + b;
    }
}
