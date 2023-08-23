import java.util.Scanner;

public class Array3 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n,y,p=0;
		n=input.nextInt();
		int[]x=new int[n];
		for(int i=0;i<n;i++) {
			x[i]=input.nextInt();
		}
		int menor=x[0];
		for(int i=0;i<n;i++) {
			if(menor<x[i]) {
				menor=x[i];
				p=i;
			}
		}
		y=p;
		
		
		System.out.println(y);
	}

}
