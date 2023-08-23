import java.util.Scanner;

public class Array9 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n;
		n=input.nextInt();
		int []vet=new int[n];
		for(int i=0;i<n;i++) {
			vet[i]=input.nextInt();
		}
		if(n%2==0) {
			for(int i=0;i<n;i++) {
				int aux=0;
				aux=vet[i]+vet[i+1];
				System.out.println(aux);
				i++;
				
			}
		}
	}

}
