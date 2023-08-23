import java.util.Scanner;

public class PlacarFut {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int casa,fora;
		
		casa=input.nextInt();
		fora=input.nextInt();
		
		if(casa>fora) {
			System.out.println("Time mandante vencedor");
		}else if(casa==fora) {
			System.out.println("A partida empatou");
		}else {
			System.out.println("Time visitante vencedor");
		}
	}

}
