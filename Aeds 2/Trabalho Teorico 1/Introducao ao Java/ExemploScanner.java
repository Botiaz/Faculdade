import java.util.Scanner;

public class ExemploScanner {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        char c;
        String s;
        int x;
        float y;

        System.out.println("Digite um texto");
        s = input.nextLine();
        System.out.println("Digite um valor");
        x = input.nextInt();
        System.out.println("Digite um valor real");
        y = input.nextFloat();
        System.out.println("Digite uma letra");
        c = input.next().charAt(0);
        System.out.println(s);
        System.out.println(y);
        System.out.println(x);
        System.out.println(c);

        input.close();
    }
}