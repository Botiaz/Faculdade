import java.util.Scanner;

public class Array2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n,soma=0;

        n=input.nextInt();

        int[] numeros = new int[n];
        
        for (int i = 0; i < n; i++) {
            numeros[i] = input.nextInt();
        }

        for (int i = 0; i < n && (2 * i + 1) < n; i++) {
            soma += numeros[i] + numeros[2 * i + 1];
        }

        System.out.println(soma);

    }
}
