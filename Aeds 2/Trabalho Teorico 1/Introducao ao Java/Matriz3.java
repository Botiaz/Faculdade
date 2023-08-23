public class Matriz3 {
	public static void main(String[] args) {
		int[][]mat={{4,5,6,7},{3,7,4,2},{7,1,3,9}};
		int[][]mat2={{9,3,5,1},{4,5,1,3},{6,3,8,6}};
		int[][] matr=new int[3][4];
		for(int i=0;i<3;i++) {
			for(int j=0;j<4;j++) {
				matr[i][j]=mat[i][j]+mat2[i][j];
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(matr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
