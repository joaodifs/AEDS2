public class Fila {
    private Object[] elementos;
    private int tamanho;
    private int capacidade;

    public Fila(int capacidade) {
        this.capacidade = capacidade;
        elementos = new Object[capacidade];
        tamanho = 0;
    }

    public void inserir(Object elemento) {
        if (tamanho < capacidade) {
            elementos[tamanho] = elemento;
            tamanho++;
        } else {
            throw new IllegalStateException("A fila está cheia.");
        }
    }

    public Object remover() {
        if (tamanho > 0) {
            Object elementoRemovido = elementos[0];
            for (int i = 0; i < tamanho - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[tamanho - 1] = null;
            tamanho--;
            return elementoRemovido;
        } else {
            throw new IllegalStateException("A fila está vazia.");
        }
    }

    public void mostrar() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Fila fila = new Fila(5);

        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        System.out.println("Elementos na fila:");
        fila.mostrar();

        System.out.println("Removendo um elemento: " + fila.remover());

        System.out.println("Elementos restantes na fila:");
        fila.mostrar();
    }
}
