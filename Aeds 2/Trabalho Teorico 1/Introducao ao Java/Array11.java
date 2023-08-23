import java.util.Scanner;

public class Array11 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n,pares=0,d3=0;
		n=input.nextInt();
		int []vet=new int[n];
		for(int i=0;i<n;i++) {
			vet[i]=input.nextInt();
			if(vet[i]%2==0) {
				pares++;
			}else if(vet[i]%3==0) {
				d3++;
			}
		}
		System.out.println(pares);
		System.out.println(d3);
	}

}
