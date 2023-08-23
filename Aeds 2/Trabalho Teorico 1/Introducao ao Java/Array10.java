import java.util.Scanner;

public class Array10 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n,menor=1000000000,aux=0;
		n=input.nextInt();
		int []vet=new int[n];
		for(int i=0;i<n;i++) {
			vet[i]=input.nextInt();
			if(vet[i]<menor) {
				menor=vet[i];
				aux=i;
			}
		}
		System.out.println(aux);
	}

}
