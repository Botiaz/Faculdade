import java.util.Scanner;

public class String2 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String text;
		int pos=0;
		text=input.nextLine();
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)=='a') {
				pos=i+1;
			}
		}
		System.out.println(pos);
	}

}
