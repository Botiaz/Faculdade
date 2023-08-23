public class Matriz4 {
	public static void main(String[] args) {
		int[][]mat={{4,5,6,7},{3,7,4,2},{7,1,3,9},{7,4,9,2}};
		int x = mat.length;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(i==j) {
					System.out.print(mat[i][j]+" ");
				}else if(i+j==x-1) {
					System.out.print(mat[i][j] + " ");
				}else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		
	}

}
