import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExemploBufferedReader {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        char a;
        float y;
        int x;

        System.out.println("Digite um texto");
        s = br.readLine();

        System.out.println("Digite um caractere");
        a = br.readLine().charAt(0);

        System.out.println("Digite um número float");
        y = Float.parseFloat(br.readLine());

        System.out.println("Digite um número inteiro");
        x = Integer.parseInt(br.readLine());

        System.out.println(s);
        System.out.println(a);
        System.out.println(y);
        System.out.println(x);

        br.close();
    }
}

