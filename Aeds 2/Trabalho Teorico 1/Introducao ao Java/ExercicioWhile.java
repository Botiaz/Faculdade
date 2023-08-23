import java.util.Scanner;

public class ExercicioWhile {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int x=0,aluno;
		float media,soma=0;
		
		while(x<5) {
			aluno=input.nextInt();
			soma+=aluno;
			x++;
		}
		media=soma/5;
		System.out.println(media);
	}

}
