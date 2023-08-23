import java.util.Scanner;

public class Maior45Maior20 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int x,y,z;
		
		x=input.nextInt();
		y=input.nextInt();
		
		if(x>45||y>45) {
			z=y+x;
		}else if(x>20&&y>20) {
			int maior=x,menor=x;
			if(y>x) {
				maior=y;
			}else {
				menor=y;
			}
			z=maior-menor;
		}else if(x<10||y<10&&x!=0&&y!=0) {
			z=x/y;
		}
		else {
			System.out.println("Lucas");
		}
	}

}
