package MaiorElemento;

import java.util.Scanner;

public class MaiorElemento {
    public static int maior(int[] vet) {
        return Maior(vet, vet.length, 0);
    }

    public static int Maior(int[] vet, int n, int i) {
        int resp;
        if (i == n - 1) {
            resp = vet[n - 1];
        } else {
            resp = Maior(vet, n, i + 1);
            if (resp < vet[i]) {
                resp = vet[i];
            }
        }
        System.out.print(resp + " ");
        return resp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("Digite o tamanho do vetor: ");
        n = scanner.nextInt();
        int[] vet = new int[n];

        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < n; i++) {
            vet[i] = scanner.nextInt();
        }

        scanner.close();

        int resultado = maior(vet);
        System.out.println("\nMaior elemento no vetor: " + resultado);
    }
}