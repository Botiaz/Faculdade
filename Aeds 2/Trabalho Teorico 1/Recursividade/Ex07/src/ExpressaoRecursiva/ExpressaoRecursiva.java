package ExpressaoRecursiva;

import java.util.Scanner;

public class ExpressaoRecursiva {
    public static int T(int n) {
        if (n == 0) {
            return 1;  // Caso base: T(0) = 1
        } else if (n == 1) {
            return 2;  // Caso base: T(1) = 2
        } else {
            // Caso geral: T(n) = T(n-1) * T(n-2) - T(n-1)
            return T(n - 1) * T(n - 2) - T(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor de n: ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Valor inválido. n deve ser maior ou igual a 0.");
        } else {
            int resultado = T(n);
            System.out.println("T(" + n + ") = " + resultado);
        }

        scanner.close();
    }
}