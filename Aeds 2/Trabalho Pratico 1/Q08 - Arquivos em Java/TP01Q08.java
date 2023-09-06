
import java.io.RandomAccessFile;

public class TP01Q08 {
	public static void main(String[] args) throws Exception {
		RandomAccessFile escrita = new RandomAccessFile("exemplo.txt","rw");
			
		int n = MyIO.readInt();	
		double num;
		
		for(int i = 0; i < n; i++) {
			num = MyIO.readDouble();
			escrita.writeDouble(num);
		}
			
		escrita.close();
			
		RandomAccessFile leitura = new RandomAccessFile("exemplo.txt", "r");
			
		long tamanho = leitura.length();
		
		while(tamanho > 0) {
			tamanho -= 8;
			leitura.seek(tamanho);
			num = leitura.readDouble();
            if(num % 1 == 0)
			    MyIO.println((int)num);
            else
                MyIO.println(num);
		}
			
		leitura.close();
	}
}
