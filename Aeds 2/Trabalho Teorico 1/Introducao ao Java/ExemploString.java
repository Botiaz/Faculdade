import java.util.Scanner;

public class ExemploString {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String text;
		char letra;
		int n=0;
		text=input.nextLine();
		letra=input.next().charAt(0);
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)==letra) {
				n++;
			}
		}
		System.out.println(n);
	}
}
