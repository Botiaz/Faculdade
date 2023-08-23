public class Matriz7 {
	public static void main(String[] args) {
		int[][]mat={{4,5,6,7},{3,7,4,2},{7,1,3,9}};
		float media;
		for(int i=0;i<4;i++) {
			float soma=0;
			for(int j=0;j<3;j++) {
				soma+=mat[j][i];
			}
			media=soma/3;
			System.out.println(media);
		}
	}

}