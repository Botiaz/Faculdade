public class Matriz6 {
	public static void main(String[] args) {
		int[][]mat={{4,5,6,7},{3,7,4,2},{7,1,3,9}};
		float media;
		for(int i=0;i<3;i++) {
			float soma=0;
			for(int j=0;j<4;j++) {
				soma+=mat[i][j];
			}
			media=soma/4;
			System.out.println(media);
		}
	}

}
