import java.util.Scanner;
import java.io.RandomAccessFile;

class Personagem {
    String id;
    String name;
    String[] alternate_names;
    String house;
    String ancestry;
    String species;
    String patronus;
    boolean hogwartsStaff;
    String hogwartsStudent;
    String actorName;
    boolean alive;
    String dateOfBirth;
    int yearOfBirth;
    String eyeColour;
    String gender;
    String hairColour;
    boolean wizard;

    Personagem(String[] atributos) {
        this.id = atributos[0];
        this.name = atributos[1];
        this.alternate_names = atributos[2].replaceAll("'", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        this.house = atributos[3];
        this.ancestry = atributos[4];
        this.species = atributos[5];
        this.patronus = atributos[6];
        this.hogwartsStaff = atributos[7].equals("VERDADEIRO");
        this.hogwartsStudent = atributos[8].equals("VERDADEIRO") ? "true" : "false";
        this.actorName = atributos[9];
        this.alive = atributos[10].equals("VERDADEIRO");
        this.dateOfBirth = atributos[12];
        this.yearOfBirth = Integer.parseInt(atributos[13]);
        this.eyeColour = atributos[14];
        this.gender = atributos[15];
        this.hairColour = atributos[16];
        this.wizard = atributos[17].equals("VERDADEIRO");
    }

    public static Personagem searchById(String id, Personagem personagem[]) {
        for (Personagem p : personagem) {
            if (p.id.equals(id))
                return p;
        }
        return null;
    }
}

class Celula {
    public Personagem elemento;
    public Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Personagem elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(Personagem x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
    }

    public void inserirFim(Personagem x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public Personagem removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento;
        tmp.prox = null;
        return resp;
    }

    public Personagem removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox) ;

        Personagem resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    public void inserir(Personagem x, int pos) throws Exception {
        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox) ;

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
        }
    }

    public Personagem remover(int pos) throws Exception {
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            return removerInicio();
        } else if (pos == tamanho - 1) {
            return removerFim();
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox) ;

            Celula tmp = i.prox;
            Personagem resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            return resp;
        }
    }

    public void mostrar() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                    j++, i.elemento.id, i.elemento.name, TP03Q05.arrayToStringWithBraces(i.elemento.alternate_names), i.elemento.house,
                    i.elemento.ancestry, i.elemento.species, i.elemento.patronus, i.elemento.hogwartsStaff,
                    i.elemento.hogwartsStudent, i.elemento.actorName, i.elemento.alive, i.elemento.dateOfBirth, i.elemento.yearOfBirth,
                    i.elemento.eyeColour, i.elemento.gender, i.elemento.hairColour, i.elemento.wizard);
        }
    }

    public boolean pesquisar(Personagem x) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = true;
                break;
            }
        }
        return resp;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++) ;
        return tamanho;
    }
}

class TP03Q05 {
    public static boolean isFim(String id) {
        return id.length() == 3 && id.equals("FIM");
    }

    public static String arrayToStringWithBraces(Object[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("/tmp/characters.csv", "r");
        file.readLine();
        Personagem[] personagem = new Personagem[404];
        for (int i = 0; i < 404; i++) {
            String line = file.readLine();
            String[] atributos = line.split(";");
            personagem[i] = new Personagem(atributos);
        }
        file.close();
        Scanner scanner = new Scanner(System.in);
        String id = "";
        Lista lista = new Lista();
        while (!isFim(id)) {
            id = scanner.nextLine();
            Personagem personagem1 = Personagem.searchById(id, personagem);
            if (personagem1 != null) {
                lista.inserirFim(personagem1);
            }
        }

        int quant = scanner.nextInt();
        int j = 0;
        scanner.nextLine(); // Consume newline left-over
        while (j < quant) {
            String linha = scanner.nextLine();
            String[] parts = linha.split(" ");
            String cmd = parts[0];
            Personagem personagem1;
            String nome = "";
            switch (cmd) {
                case "II":
                    personagem1 = Personagem.searchById(parts[1], personagem);
                    lista.inserirInicio(personagem1);
                    break;
                case "IF":
                    personagem1 = Personagem.searchById(parts[1], personagem);
                    lista.inserirFim(personagem1);
                    break;
                case "I*":
                    personagem1 = Personagem.searchById(parts[2], personagem);
                    lista.inserir(personagem1, Integer.parseInt(parts[1]));
                    break;
                case "RI":
                    nome = lista.removerInicio().name;
                    System.out.printf("(R) %s\n", nome);
                    break;
                case "RF":
                    nome = lista.removerFim().name;
                    System.out.printf("(R) %s\n", nome);
                    break;
                case "R*":
                    nome = lista.remover(Integer.parseInt(parts[1])).name;
                    System.out.printf("(R) %s\n", nome);
                    break;
            }
            j++;
        }
        lista.mostrar();
        scanner.close();
    }
}