import java.util.Scanner;

public class Maior10n {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int[] x= new int[10];
		
		for(int i=0;i<10;i++) {
			x[i]=input.nextInt();
		}
		int maior=x[0];
		
		for(int i=1;i<10;i++) {
			if(x[i]>maior) {
				maior=x[i];
			}
		}
		System.out.println(maior);
	}

}
