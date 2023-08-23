import java.util.Scanner;

public class Array7 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float[]alunos=new float[5];
		float soma=0,media,menor=1000000;
		for(int i=0;i<5;i++) {
			alunos[i]=input.nextFloat();
			if(alunos[i]<menor) {
				menor=alunos[i];
			}
			soma+=alunos[i];
		}
		media=soma/5;
		System.out.println(media);
		System.out.println(soma);
		System.out.println(menor);
	}

}
