public class OrdenacaodeArray {
	public static void main(String[] args) {
		int []x= {5,6,7,3,2,9,0,8,1,4};
		int z=0;
		
		for(int i=0;i<10;i++) {
			int aux;

			for(int j=z;j<10;j++) {
				if(x[j]<x[i]) {
					aux=x[i];
					x[i]=x[j];
					x[j]=aux;
					
				}
				
			}
			System.out.println(x[i]);
			z++;
		}
	}

}
