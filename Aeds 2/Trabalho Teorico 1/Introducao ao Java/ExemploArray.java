import java.util.Scanner;

public class ExemploArray {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float[]alunos=new float[5];
		String[]nome=new String[5];
		float media,soma=0;
		
		for(int i=0;i<5;i++) {
			alunos[i]=input.nextFloat();
			nome[i]=input.nextLine();
			soma+=alunos[i];
		}
		media=soma/5;
		for(int i=0;i<5;i++) {
			if(alunos[i]>media) {
				System.out.println(nome[i]);
			}
		}
		
	}

}