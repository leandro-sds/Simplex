package simplex;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a = { {3,5,6}, {2,5,9}, {8,9,7} };
		
		System.out.print("         x y z");
		for (int l = 0; l < a.length; l++) {
			System.out.print("\n linha " + l + " ");
			for (int col = 0; col < a[l].length; col++) {
				System.out.print(a[l][col] + " ");
			}
		}
	}

}
