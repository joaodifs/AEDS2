import java.util.Scanner;

class Matriz {
    private int linhas;
    private int colunas;
    private int[][] dados;

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.dados = new int[linhas][colunas];
    }

    public void lerDados(Scanner scanner) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = scanner.nextInt();
            }
        }
    }

    public Matriz soma(Matriz outra) {
        if (this.linhas != outra.linhas || this.colunas != outra.colunas) {
            throw new IllegalArgumentException("Matrizes de tamanhos diferentes não podem ser somadas.");
        }
        Matriz resultado = new Matriz(this.linhas, this.colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.dados[i][j] = this.dados[i][j] + outra.dados[i][j];
            }
        }
        return resultado;
    }

    public Matriz multiplicacao(Matriz outra) {
        if (this.colunas != outra.linhas) {
            throw new IllegalArgumentException("Número de colunas da primeira matriz deve ser igual ao número de linhas da segunda matriz.");
        }
        Matriz resultado = new Matriz(this.linhas, outra.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < outra.colunas; j++) {
                for (int k = 0; k < this.colunas; k++) {
                    resultado.dados[i][j] += this.dados[i][k] * outra.dados[k][j];
                }
            }
        }
        return resultado;
    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(dados[i][i]);
            if (i < Math.min(linhas, colunas) - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(dados[i][colunas - i - 1]);
            if (i < Math.min(linhas, colunas) - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public void imprimir() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(dados[i][j]);
                if (j < colunas - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

public class TP03Q09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCasos = scanner.nextInt();

        for (int caso = 0; caso < numCasos; caso++) {
            // Ler primeira matriz
            int l1 = scanner.nextInt();
            int c1 = scanner.nextInt();
            Matriz matriz1 = new Matriz(l1, c1);
            matriz1.lerDados(scanner);

            // Ler segunda matriz
            int l2 = scanner.nextInt();
            int c2 = scanner.nextInt();
            Matriz matriz2 = new Matriz(l2, c2);
            matriz2.lerDados(scanner);

            // Mostrar diagonais da primeira matriz
            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            // Calcular e imprimir soma
            Matriz soma = matriz1.soma(matriz2);
            soma.imprimir();

            // Calcular e imprimir multiplicação
            Matriz multiplicacao = matriz1.multiplicacao(matriz2);
            multiplicacao.imprimir();
        }

        scanner.close();
    }
}
