import java.util.Scanner;

public class Array12 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n=10,z=10;
		int []vet=new int[n];
		for(int i=0;i<10;i++) {
			vet[i]=input.nextInt();
		}
		for(int i=0;i<z;i++) {
			int aux;
			aux=vet[i]+vet[z-1-i];
			z--;
			System.out.println(aux);
		}
	}

}
