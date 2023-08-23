import java.util.Scanner;

public class Sequenciapadrao47 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int x,y=1,z=1;
		x=input.nextInt();
		System.out.println(y);
		while(y<x) {
			if(z%2==0) {
				y=y+7;
			}else {
				y=y+4;
			}
			if(y<x){
				System.out.println(y);
			}
			z++;
		}
	}

}
