package Main;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
	    Scanner input=new Scanner(System.in);
	    String s;
	    int cont=0;

	    s=input.nextLine();
	    for(int i=0;i<s.length();i++){
	      if(s.charAt(i)!=' '){
	        cont++;
	      }else{
	        cont=0;
	      }
	    }
	    System.out.println(cont);
	  }
}
