package simplex;

import java.util.Scanner;

public class Simplex {
	
	public static void main(String[] args) {
		float[][] tabelaSimplex;
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
		
		
		imprimeTabela(tabelaSimplex);
		solucao(tabelaSimplex);
		
		
	} // Fim classe main
	
	static public void solucao(float[][] tabela) {
		while (!melhorSol(tabela)) {
			int ultimaLinha = tabela.length - 1;
			
			// descobrir o maior valor na linha -Z
			int colunaMaior = 0;
			float maiorValor = 0;
			for (int j = 0; j < tabela[ultimaLinha].length; j++) {
				if (tabela[ultimaLinha][j] > maiorValor) {
					maiorValor = tabela[ultimaLinha][j];
					colunaMaior = j;
				}
			}
			
			// escolher quem sai
			int ultimaColuna = tabela[0].length - 1, linhaMenorDiv = 0;
			float div = 0, menorDiv = 0;
			for (int linha = 0; linha < tabela.length - 1; linha++ ) {
				// Divis�o para escolher qual linha ser� substitu�da
				div = tabela[linha][ultimaColuna] / tabela[linha][colunaMaior];
				if (linha == 0) {
					menorDiv = div;
				}
				if (div > 0 && div < menorDiv) {
					menorDiv = div;
					linhaMenorDiv = linha;
				}
			}
			
			// Opera��es para transformar a linhaMenorDiv em vari�vel b�sica
			if (tabela[linhaMenorDiv][colunaMaior] > 1) {
				// Se a coluna que entrou n�o for vari�vel b�sica, a linha inteira � dividida pelo valor da coluna
				for (int col = 0; col < tabela[linhaMenorDiv].length; col++) {
					tabela[linhaMenorDiv][col] = tabela[linhaMenorDiv][col] / tabela[linhaMenorDiv][colunaMaior];
				}
			}
			// Transformar todos os outros valores em 0
			for (int linha = 0; linha < tabela.length; linha++) {
				if (linha != linhaMenorDiv) {
					for (int col = 0; col < tabela[linha].length; col++) {
						tabela[linha][col] = tabela[linha][col] + ((tabela[linha][colunaMaior] * -1) * tabela[linhaMenorDiv][col]);
					}
				}
			}
		}
		 System.out.println("\n\n Melhor solu��o \n");
		 imprimeTabela(tabela);
	}
	
	static public void imprimeTabela(float[][] tabela) {
		for (int linha = 0; linha < tabela.length; linha++) {
			System.out.print("\n linha " + linha + " ");
			for (int col = 0; col < tabela[linha].length; col++) {
				System.out.print(tabela[linha][col] + " ");
			}
		}
	}
	
	//Verifica se esta � a melhor solu��o
	static private boolean melhorSol(float[][] tabela) {
		int ultimaLinha = tabela.length - 1;
		boolean sol = false;
		for (int col = 0; col < tabela[ultimaLinha].length; col++) {
			if (tabela[ultimaLinha][col] <= 0) {
				sol = true;
			} else {
				sol = false;
				return sol;
			}
		}
		return sol;
	}
	
	
} // Fim classe Simplex
