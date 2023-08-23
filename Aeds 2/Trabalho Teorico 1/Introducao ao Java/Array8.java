import java.util.Scanner;

public class Array8 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float[]alunos=new float[5];
		float soma=0,media,menor=1000000;
		for(int i=0;i<5;i++) {
			alunos[i]=input.nextFloat();
			soma+=alunos[i];
		}
		media=soma/5;
		for(int i=0;i<5;i++) {
			if(alunos[i]>media) {
				System.out.println(alunos[i]);
			}
		}

	}
	

}
