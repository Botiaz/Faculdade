public class Array5 {
	public static void main(String[] args) {
		int []x= {5,6,7,3,2,9,0,8,1,4};
		int []y= {1,2,3,4,5,6,7,8,9,0};
		
		for(int i=0;i<10;i++) {
			int uni=0,inter=0;
			uni=x[i]+y[i];
			System.out.println("Uniao"+i+": "+uni);
			if((x[i]-y[i])>=0) {
				inter=x[i]-y[i];
			}else {
				inter=y[i]-x[i];
			}
			System.out.println("Intersecao"+i+": "+inter);
		}
	}

}
