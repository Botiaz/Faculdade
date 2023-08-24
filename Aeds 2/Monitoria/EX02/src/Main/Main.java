package Main;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
	    Scanner input=new Scanner(System.in);
	    int abre=0,fecha=1;
	    String s;
	    s=input.nextLine();
	    for(int i=0;i<s.length();i++){
	      if(s.charAt(i)=='('){
	        abre=i;
	      }
	      if(s.charAt(i)==')'){
	        fecha=i;
	      }
	    }
	    for(int i=0;i<=abre;i++){
	      System.out.print(s.charAt(i));
	    }
	    for(int i=fecha-1;i>abre;i--){
	      System.out.print(s.charAt(i));
	    }
	    System.out.print(")");
	    for(int i=fecha+1;i<s.length();i++){
	      System.out.print(s.charAt(i));
	    }
	  }
}
