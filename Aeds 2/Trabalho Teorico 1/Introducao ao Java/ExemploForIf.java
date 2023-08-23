import java.util.Scanner;

public class ExemploForIf {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float[]alunos=new float[5];
		float media,soma=0,contagem=0;
		
		for(int i=0;i<5;i++) {
			alunos[i]=input.nextFloat();
			if(alunos[i]>=80) {
					soma+=alunos[i];
					contagem++;
			}
		}
		media=soma/contagem;
		System.out.println(media);
	}

}