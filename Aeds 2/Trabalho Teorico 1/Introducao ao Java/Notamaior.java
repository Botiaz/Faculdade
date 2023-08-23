import java.util.Scanner;

public class Notamaior {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		float notamax,media,soma=0;
		double n60,am60=0,n90,am90=0;
		float[] alunos=new float[20];
	
		
		notamax=input.nextFloat();
		n60=notamax*0.60;
		n90=notamax*0.90;
		
		for(int i=0;i<20;i++) {
			alunos[i]=input.nextFloat();
			soma+=alunos[i];
			if(alunos[i]<n60) {
				am60++;
			}
			if(alunos[i]>=n90) {
				am90++;
			}
		}
		media=soma/20;
		System.out.println(media);
		System.out.println(am60);
		System.out.println(am90);
	}

}
