import java.util.Scanner;

public class Log {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double x,y;

        x = input.nextDouble();
        y = input.nextDouble();

        double maior,menor;

        menor = Math.min(x, y);
        maior = Math.max(x, y);
        
        double raiz,log;

        raiz = Math.cbrt(menor);
        log = Math.log(menor) / Math.log(maior);

        System.out.println("Raiz cúbica do menor número: " + raiz);
        System.out.println("Logaritmo do menor na base do maior: " + log);

    }
}

