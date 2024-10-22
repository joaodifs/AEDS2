class Fila {
    int array[];
    int primeiro, ultimo;

    Fila() {
        this(6);
    }

    Fila(int tamanho) {
        array = new int[tamanho + 1];
        ultimo = primeiro = 0;
    }

    void inserir(int x) throws Exception {
        if (((ultimo + 1) % array.length) == primeiro) {
            throw new Exception("Erro ao inserir");
        }
        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length;
    }

    int remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        return resp;
    }

    void mostrar() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Fila vazia!");
        }
        int i = primeiro;
        while (i != ultimo) {
            System.out.printf(array[i] + " ");
            i = (i + 1) % array.length;
        }
    }
}
