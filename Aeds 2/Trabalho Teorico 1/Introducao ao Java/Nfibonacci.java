import java.util.Scanner;

public class Nfibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        long termo;

        n = input.nextInt();

        termo = fibonacci(n);

        System.out.println(termo);

        input.close();
    }

    public static long fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            long termoAnterior1 = 0;
            long termoAnterior2 = 1;
            long termoAtual = 0;

            for (int i = 2; i <= n; i++) {
                termoAtual = termoAnterior1 + termoAnterior2;
                termoAnterior1 = termoAnterior2;
                termoAnterior2 = termoAtual;
            }

            return termoAtual;
        }
    }
}
