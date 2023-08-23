import java.util.Scanner;

public class Triangulo {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int x,y,z;
		
		System.out.println("Digite um lado do tringulo:");
		x=input.nextInt();
		System.out.println("Digite o segundo lado do tringulo:");
		y=input.nextInt();
		System.out.println("Digite o terceiro lado do tringulo:");
		z=input.nextInt();
		
		if(x==y&&x==z&&y==z){
			System.out.println("Triangulo Equilatero");
		}else if(x==y||x==z||z==y) {
			System.out.println("Triangulo Isoceles");
		}else {
			System.out.println("Triangulo Escaleno");
		}
		
	}

}
