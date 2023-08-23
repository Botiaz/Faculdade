import java.util.Scanner;

public class ExemploFor {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float[]alunos=new float[5];
		float media,soma=0;
		
		for(int i=0;i<5;i++) {
			alunos[i]=input.nextFloat();
			soma+=alunos[i];
		}
		media=soma/5;
		System.out.println(media);
	}

}
