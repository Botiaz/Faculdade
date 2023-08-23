public class Array14 {
	public static void main(String[] args) {
		int x=0,y=0;
		int[] vet1= {5,4,3,8,9,5,3,8,9,1};
		int[] vet2= {6,7,3,2,5,4,9,6,0,3};
		
		for(int i=0;i<20;i++) {
			if(i%2==0) {
				System.out.println(vet1[x]);
				x++;
			}else {
				System.out.println(vet2[y]);
				y++;
			}
		}
	}

}
