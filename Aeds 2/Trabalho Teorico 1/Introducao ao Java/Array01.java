import java.util.Scanner;

public class Array01 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n,soma=0;
		n=input.nextInt();
		int[]x=new int[n];
		float media;
		for(int i=0;i<n;i++) {
			x[i]=input.nextInt();
			soma+=x[i];
		}
		media=soma/n;
		for(int i=0;i<n;i++) {
			if(x[i]>media) {
				System.out.println(x[i]);
			}
		}
		
	}

}
