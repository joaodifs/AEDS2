public class Pilha {
    int n;
    int array[];

    Pilha() {
        this(6);
    }

    Pilha(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    void inserir(int x) throws Exception {
        if (array.length >= n) {
            throw new Exception("Pilha cheia!");
        }
        array[n] = x;
        n++;
    }

    int remover() throws Exception {
        if (n == 0) {
            throw new Exception("Pilha vazia!");
        }
        int resp = array[n];
        n--;
        return resp;
    }

    void mostar() {
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
