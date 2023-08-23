import java.util.Scanner;

public class BancoZe {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		double salario,prestacao;
		
		salario=input.nextFloat();
		prestacao=input.nextFloat();
		
		salario=salario*0.40;
		
		if(prestacao<=salario) {
			System.out.println("Emprestimo Aprovado");
		}else {
			System.out.println("Emprestimo Desaprovado");
		}
		
	}

}
