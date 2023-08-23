import java.util.Scanner;

public class Exemploif {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int nota;
		
		System.out.println("Digite sua nota:");
		nota=input.nextInt();
		
		if(nota>=80) {
			System.out.println("Parabens,muito bom");
		}else if(nota>=70&&nota<80) {
			System.out.println("Parabens, aprovado");
		}else {
			System.out.println("Infelizmente, reprovado");
		}
		
	}
}
