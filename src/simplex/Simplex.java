package simplex;

import java.util.Scanner;

public class Simplex {
	static float[][] tabelaSimplex;
	public static void main(String[] args) {
		
		int var, rest, linhas, colunas;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite o n�mero de vari�veis");
		var = input.nextInt();
		System.out.println("Digite o n�mero de restri��es");
		rest = input.nextInt();
		
		linhas = rest + 1; // +1 indica a linha -Z
		colunas = var + rest +1; // +1 indica a coluna b
		
		tabelaSimplex = new float[linhas][colunas];
		
		/*
		 * for para o preenchimento da tabela
		 * auxL: indicar as linhas (linha 1, linha 2, linha 3...)
		 * auxF: indicar os n�meros de f (f1, f2, f3...)
		 * auxCol: indicar os n�meros de x (x1, x2, x3...)
		 */
		for (int l = 0; l < tabelaSimplex.length; l++) {
			int auxL = l+1; 
			int auxF = 1; 
			for (int col = 0; col < tabelaSimplex[l].length; col++) {
				int auxCol = col+1;
				// Se auxCol coluna for menor ou igual ao n�mero de vari�veis ent�o esta coluna indica os valores x
				if (auxCol <= var) {
					System.out.println("Digite o valor de x" + auxCol + " da linha " + auxL);
					tabelaSimplex[l][col] = input.nextFloat();
				}
				/* Se auxCol for maior que o n�mero de vari�veis e menor que o n�mero de colunas 
				 * ent�o estas colunas indicam os valores de f
				 */
				if (auxCol > var & auxCol < colunas) {
					System.out.println("Digite o valor de f" + auxF + " da linha " + auxL);
					tabelaSimplex[l][col] = input.nextFloat();
					auxF++;
				}
				// Se a auxCol for exatamente igual ao n�mero de colunas ent�o esta coluna indica o valor de b
				if (auxCol == colunas) {
					System.out.println("Digite o valor de b da linha " + auxL);
					tabelaSimplex[l][col] = input.nextFloat();
				}
			}
		}
		input.close();
		
		imprimeTabela();
		
	} // Fim classe main
	
	static public void imprimeTabela() {
		for (int l = 0; l < tabelaSimplex.length; l++) {
			System.out.print("\n linha " + l + " ");
			for (int col = 0; col < tabelaSimplex[l].length; col++) {
				System.out.print(tabelaSimplex[l][col] + " ");
			}
		}
	}
	
	
} // Fim classe Simplex
