import java.util.Scanner;
import java.io.RandomAccessFile;

class Personagemp {

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

    Personagemp(String[] atributos) {
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

    public static Personagemp searchById(String id, Personagemp personagem[]) {
        for (int i = 0; i < 404; i++) {
            if (personagem[i].id.equals(id))
                return personagem[i];
        }
        return null;
    }
}

class Celula {
    public Personagemp elemento;
    public Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Personagemp elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Pilha {
    private Celula topo;

    public Pilha() {
        topo = null;
    }

    public void inserirFim(Personagemp x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public Personagemp removerFim() throws Exception {
        if (topo == null) {
            throw new Exception("Erro ao remover!");
        }

        Personagemp resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public void mostrar() {
        System.out.println("[ Top ]");
        int j = 0;
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                    j++, i.elemento.id, i.elemento.name, TP03Q06.arrayToStringWithBraces(i.elemento.alternate_names), i.elemento.house,
                    i.elemento.ancestry, i.elemento.species, i.elemento.patronus, i.elemento.hogwartsStaff,
                    i.elemento.hogwartsStudent, i.elemento.actorName, i.elemento.alive, i.elemento.dateOfBirth, i.elemento.yearOfBirth,
                    i.elemento.eyeColour, i.elemento.gender, i.elemento.hairColour, i.elemento.wizard);
        }
        System.out.println("[ Bottom ]");
    }

    public boolean pesquisar(Personagemp x) {
        boolean retorno = false;
        for (Celula i = topo; i != null && retorno == false; i = i.prox) {
            retorno = (i.elemento == x);
        }
        return retorno;
    }
}

class TP03Q06 {
    public static boolean isFim(String id) {
        return (id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M');
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
        Personagemp[] personagem = new Personagemp[404];
        for (int i = 0; i < 404; i++) {
            String line = file.readLine();
            String[] atributos = line.split(";");
            personagem[i] = new Personagemp(atributos);
        }
        file.close();
        Scanner scanner = new Scanner(System.in);
        String id = "";
        Pilha pilha = new Pilha();
        while (!isFim(id)) {
            id = scanner.nextLine();
            Personagemp personagem1 = Personagemp.searchById(id, personagem);
            if (personagem1 != null) {
                pilha.inserirFim(personagem1);
            }
        }

        int quant = scanner.nextInt() + 1;
        int j = 0;
        String linha = "";
        while (j < quant) {
            linha = scanner.nextLine();
            String[] parts = linha.split(" ");
            String cmd = parts[0];
            Personagemp personagem1 = personagem[1];
            String nome = "";
            switch (cmd) {
                case "I":
                    personagem1 = Personagemp.searchById(parts[1], personagem);
                    pilha.inserirFim(personagem1);
                    break;
                case "R":
                    nome = pilha.removerFim().name;
                    System.out.printf("(R) %s\n", nome);
                    break;
            }
            j++;
        }
        pilha.mostrar();
        scanner.close();
    }
}
