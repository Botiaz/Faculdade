package Retangulo;

import java.util.Scanner;

public class Retangulo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        float base = entrada.nextFloat();
        float altura = entrada.nextFloat();

        float area = getArea(base, altura);
        float perimetro = getPerimetro(base, altura);
        float diagonal = getDiagonal(base, altura);

        System.out.println("Area: " + area);
        System.out.println("Perimetro: " + perimetro);
        System.out.println("Diagonal: " + diagonal);
    }

    private static float getArea(float base, float altura) {
        return base * altura;
    }

    private static float getPerimetro(float base, float altura) {
        return 2 * (base + altura);
    }

    private static float getDiagonal(float base, float altura) {
        return (float) Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
    }
}
