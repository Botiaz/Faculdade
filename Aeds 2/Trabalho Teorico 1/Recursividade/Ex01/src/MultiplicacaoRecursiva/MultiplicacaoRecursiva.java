package MultiplicacaoRecursiva;

import java.util.Scanner;

public class MultiplicacaoRecursiva {
    public static int multiplicacao(int a, int b) {
        int resp = 0;

        if (b > 0) {
            resp = a + multiplicacao(a, b - 1);
        }
        System.out.println(resp);
        return resp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b;
        System.out.print("Digite dois numeros inteiros separados por espaco: ");
        a = scanner.nextInt();
        b = scanner.nextInt();
        scanner.close();
        multiplicacao(a, b);
    }
}