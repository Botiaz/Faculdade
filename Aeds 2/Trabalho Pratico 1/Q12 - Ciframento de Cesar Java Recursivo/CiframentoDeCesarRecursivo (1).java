public class CiframentoDeCesarRecursivo {

	static String isCiframento(String str, String text, int i) {
		if(i == str.length()) {
			return text;
		}else{
      char letra;
      letra = (char)(str.charAt(i)+3);
      text += letra;
      return isCiframento(str,text,i+1);
    }
	}
	public static void main(String[] args) {
		String str;
		str=MyIO.readLine();
		while(str.equals("FIM")!=true){
      int i = 0;
      String text = "";
      str=isCiframento(str, text, i);
		  MyIO.println(str);
      str=MyIO.readLine();
        }
	}
}