package serial;

import java.util.Random;

public class MultiplicacaoDeMatrizes {
	
	// Método para multiplicar duas matrizes
    public static int[][] multiplicarMatrizes(int[][] A, int[][] B) {
        int n = A.length;  
        int m = A[0].length;
        int p = B[0].length;
        
        int[][] C = new int[n][p];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                C[i][j] = 0;
                for (int k = 0; k < m; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return C;
    }

    // Método para imprimir toda a matriz
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(String.format("%4d - ", valor));
            }
            System.out.println();
        }
    }
    
    // Método para criar uma matriz com valores aleatórios
    public static int[][] criarMatrizAleatoria(int linhas, int colunas) {
        int[][] matriz = new int[linhas][colunas];
        Random rand = new Random();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = rand.nextInt(10);
            }
        }
        return matriz;
    }
    
    public static void main(String[] args) {
        final int TAMANHO = 100; // Tamanho das matrizes 100x100

        int[][] A = criarMatrizAleatoria(TAMANHO, TAMANHO);
        int[][] B = criarMatrizAleatoria(TAMANHO, TAMANHO);
        
        int[][] C = multiplicarMatrizes(A, B);
        
        System.out.println("Matriz resultante C (100x100):");
        imprimirMatriz(C);
    }

}
