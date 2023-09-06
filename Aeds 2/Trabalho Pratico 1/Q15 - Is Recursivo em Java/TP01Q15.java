
public class TP01Q15 {
  public static void main(String[] args) {
	  String texto = MyIO.readLine();
	  
	 while (stop(texto)) {
          if (isVogal(texto,0) == true) {
              MyIO.print("SIM ");
          } else {
              MyIO.print("NAO ");
          }
          if (isConsoante(texto,0) == true) {
              MyIO.print("SIM ");
          } else {
              MyIO.print("NAO ");
          }
          if (isInt(texto,0) == true) {
              MyIO.print("SIM ");
          } else {
              MyIO.print("NAO ");
          }
          if (isReal(texto,0,0) == true) {
              MyIO.println("SIM ");
          } else {
              MyIO.println("NAO ");
          }
          texto = MyIO.readLine();
      } 
	  
  }
  
  public static boolean stop(String texto){
      boolean resp = true;
      if (texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M'){
           resp = false;
      }
      return resp;
}
  
  public static boolean vogal(char x) {
	  boolean resp = false;
	  if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u'|| x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U' ) {
		  resp = true;
	  }
	  return resp;
  }
  
  public static boolean isVogal(String texto,int i) {
	  boolean resp = true;
	  
	  if(i < texto.length()) {
		  if(vogal(texto.charAt(i)) == false){
			  resp = false;
		  }
		  else {
			  resp = isVogal(texto,i+1);
		  }
	  }
	  
	  return resp;
  }
  
  public static boolean isConsoante(String texto,int i) {
	  boolean resp = true;
	  
	  if(i < texto.length()) {
		  if((vogal(texto.charAt(i)) == true) || (texto.charAt(i) >= '0' && texto.charAt(i) <= '9')){
			  resp = false;
		  }
		  else {
			  resp = isConsoante(texto,i+1);
		  }
	  }
	    
	  return resp;
  }
  
  public static boolean isInt(String texto,int i) {
	  boolean resp = true;
	  
	  if(i < texto.length()) {
		  if(!(texto.charAt(i) >= '0' && texto.charAt(i) <= '9')){
			  resp = false;
		  }
		  else {
			  resp = isInt(texto,i+1);
		  }
	  }
	  
	  return resp;
  }
  
  
  public static boolean isReal(String texto,int i,int n) {
	  boolean resp = true;
	  
	  if(i < texto.length()) {
		  if(!(texto.charAt(i) >= '0' && texto.charAt(i) <= '9')){
			  if(texto.charAt(i) == '.' || texto.charAt(i) == ',')
				  resp = isReal(texto,i+1,n+1);
			  else
			      resp = false;
		  }
		  else {
			resp = isReal(texto,i+1,n);
		  }
	  }
	  
	  if(n > 1) {
		  resp = false;
	  }
	  
	  return resp;
  }
}
