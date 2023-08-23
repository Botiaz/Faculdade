import java.util.Scanner;

public class Palindromo {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String text;
		String textr= ("");
		text=input.nextLine();
		
		while(true) {
			if (text.equals("FIM")) {
                break;
            }
			for(int i=text.length()-1;i>=0;i--) {
				textr+=text.charAt(i);
			}
			if(textr.equals(text)) {
				System.out.println("SIM");
			}else {
				System.out.println("NAO");
			}
			text=input.nextLine();
			textr= ("");
		}
		
	}
}