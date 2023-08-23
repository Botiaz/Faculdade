import java.util.Scanner;

public class String3 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String text;
		int n3=0,n=0,n2=0,vogal=0,consoante=0;
		text=input.nextLine();
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)==' ') {
				n++;
			}else {
				n2++;
			}
			n3++;
			if(isVogal(text.charAt(i))) {
				vogal++;
			}else if(text.charAt(i)!=' ') {
				consoante++;
			}
		}
		System.out.println(n);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(vogal);
		System.out.println(consoante);
	}
	public static boolean isVogal(char c) {
        c = Character.toLowerCase(c);
        
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
    }

}
