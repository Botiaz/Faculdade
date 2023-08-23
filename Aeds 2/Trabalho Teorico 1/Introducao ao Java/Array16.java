public class Array16 {
	public static void main(String[] args) {
		int[] vet= {10,4,3,7,2,5,6,8,9,1};
		int x=0;
		
		for(int i=0;i<10;i++) {
			for(int j=x;j<10;j++) {
				int aux;
				if(vet[i]>vet[j]) {
					aux=vet[i];
					vet[i]=vet[j];
					vet[j]=aux;
				}
			}
			x++;
			System.out.println(vet[i]);
		}
	}

}
