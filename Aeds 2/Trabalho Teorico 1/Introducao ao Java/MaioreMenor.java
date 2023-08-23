import java.util.Scanner;

public class MaioreMenor {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int[] x = new int[3];
		
		x[0]=input.nextInt();
		x[1]=input.nextInt();
		x[2]=input.nextInt();
		
		int maior=x[0],menor=x[0];
		
		for(int i=1;i<3;i++) {
			if(x[i]>maior) {
				maior=x[i];
			}else if(x[i]<menor) {
				menor=x[i];
			}
		}
		
		System.out.println(maior);
		System.out.println(menor);

		
	}

}
