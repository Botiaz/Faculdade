import java.util.Random;

public class AlteracaoAleatoria {
	public static void main(String[] args) {
		Random gerador = new Random();
		gerador.setSeed(4);
		String str;
		str=MyIO.readLine();
		while(str.equals("FIM")!=true) {
			char x,y;
			char[] text=new char[str.length()];
			x = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
			y = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
			for(int i=0;i<str.length();i++) {
			    if(str.charAt(i)==x){
			    	text[i]=y;
			    }else {
			    	text[i]=str.charAt(i);
			    }
			}
			MyIO.println(new String(text));
			str=MyIO.readLine();
		}
		
	}

}
