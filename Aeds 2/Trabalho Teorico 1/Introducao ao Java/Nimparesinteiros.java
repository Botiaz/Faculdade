import java.util.Scanner;

public class Nimparesinteiros {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int x;
		
		x=input.nextInt();
		if(x%2==0) {
			x=x-1;
		}else {
			x=x-2;
		}
		int i=x;
		while(i>0) {
			System.out.println(i);
			i=i-2;
		}
	}

}