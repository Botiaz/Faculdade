import java.util.Scanner;

public class Array13 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n=10,soma=0;
		int []vet=new int[n];
		for(int i=0;i<10;i++) {
			vet[i]=input.nextInt();
			if(vet[i]%3==0) {
				soma+=vet[i];
			}
		}
		System.out.println(soma);
		
	}

}
