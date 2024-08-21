package concorrente;

import java.util.Random;

public class MultiplicacaoDeMatrizesConcorrente {
	
	// Classe que representa uma tarefa de multiplicação para uma linha da matriz
    static class MultiplicacaoLinha implements Runnable {
        private int[][] A;
        private int[][] B;
        private int[][] C;
        private int linha;

        public MultiplicacaoLinha(int[][] A, int[][] B, int[][] C, int linha) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.linha = linha;
        }

        @Override
        public void run() {
            int m = A[0].length;
            int p = B[0].length;
            for (int j = 0; j < p; j++) {
                C[linha][j] = 0;
                for (int k = 0; k < m; k++) {
                    C[linha][j] += A[linha][k] * B[k][j];
                }
            }
        }
    }

    // Método para multiplicar duas matrizes usando threads
    public static int[][] multiplicarMatrizesConcorrente(int[][] A, int[][] B) {
        int n = A.length;
        int p = B[0].length;
        
        int[][] C = new int[n][p];
        
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(new MultiplicacaoLinha(A, B, C, i));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return C;
    }

    // Método para imprimir toda a matriz
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(String.format("%4d - ", valor)); // Formatação para alinhamento
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
        final int TAMANHO = 100;
        
        int[][] A = criarMatrizAleatoria(TAMANHO, TAMANHO);
        int[][] B = criarMatrizAleatoria(TAMANHO, TAMANHO);
        
        long startTime = System.currentTimeMillis();
        int[][] C = multiplicarMatrizesConcorrente(A, B);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Matriz resultante C (100x100):");
        imprimirMatriz(C);
        
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");
    }
}
