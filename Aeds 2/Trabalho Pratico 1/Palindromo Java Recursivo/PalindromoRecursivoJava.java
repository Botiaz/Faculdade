class Main {
  public static boolean isPalindromo(String s, int i){
    Boolean resp = true;
    if (s.length() / 2 > i){
      if(s.charAt(i) != s.charAt(s.length()- i - 1)){
      resp = false;
    }else{
      isPalindromo(s,i+1);
    }
   }
    return resp;
  }
  
  public static void main(String[] args) {
    String s;
    int i = 0;
    while(true){
      s = MyIO.readLine();
      if(s.equals("FIM")){
        break;
      }else{
        if(isPalindromo(s, i) == true){
          MyIO.println("SIM");
        }else{
          MyIO.println("NAO");
        }     
      }
    }
  }
}