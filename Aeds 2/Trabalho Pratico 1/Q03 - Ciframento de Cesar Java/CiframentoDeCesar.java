public class CiframentoDeCesar {
	
	static String isCiframento(String str) {
		String text="";
		for(int i=0;i<str.length();i++) {
			char letra;
			letra=(char) (str.charAt(i)+3);
			text+=letra;
		}
		return text;
	}
	public static void main(String[] args) {
		String str;
		str=MyIO.readLine();
		while(str.equals("FIM")!=true){
            str=isCiframento(str);
		    MyIO.println(str);
            str=MyIO.readLine();
        }
	}
}