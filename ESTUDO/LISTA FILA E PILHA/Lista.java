
class Lista {
    int array[];
    int n;

    Lista() {
        this(6);
    }

    Lista(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    void inserirInicio(int x) {
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = x;
        n++;
    }

    void inserirFim(int x) {
        array[n] = x;
        n++;
    }

    void inserir(int x, int pos) {
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }
        array[pos] = x;
        n++;
    }

    int removerFim() {
        return array[--n];
    }

    int removerInicio() {
        int resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    int remover(int pos) {
        int resp = array[pos];
        n--;
        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}