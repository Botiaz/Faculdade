package TorresDeHanoi;

import java.util.Scanner;

public class TorresDeHanoi {
    public static void hanoi(int n, int origem, int destino, int intermediario) {
        if (n == 1) {
            System.out.println("Mova o disco 1 de " + origem + " para " + destino);
            return;
        }
        hanoi(n - 1, origem, intermediario, destino);
        System.out.println("Mova o disco " + n + " de " + origem + " para " + destino);
        hanoi(n - 1, intermediario, destino, origem);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de discos: ");
        int numDiscos = scanner.nextInt();

        // O número mínimo de pinos é 3 para o problema das Torres de Hanoi.
        int numPinos = 3;

        System.out.println("Movimentos necessários:");
        hanoi(numDiscos, 1, numPinos, 2);

        scanner.close();
    }
}
