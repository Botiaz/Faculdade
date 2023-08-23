import java.util.Scanner;

public class String1 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String text;
		int n=0,n2=0;
		text=input.nextLine();
		for(int i=0;i<text.length();i++) {
			if(Character.isUpperCase(text.charAt(i))) {
				n++;
			}
			n2++;
		}
		System.out.println(n);
		System.out.println(n2);
	}

}
