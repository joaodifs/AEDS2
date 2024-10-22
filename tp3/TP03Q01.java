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
        if (atributos[7].equals("VERDADEIRO")) {
            hogwartsStaff = true;
        } else {
            hogwartsStaff = false;
        }
        if (atributos[8].equals("VERDADEIRO")) {
            hogwartsStudent = "true";
        } else {
            hogwartsStudent = "false";
        }
        this.actorName = atributos[9];
        if (atributos[10].equals("VERDADEIRO")) {
            alive = true;
        } else {
            alive = false;
        }
        this.dateOfBirth = atributos[12];
        this.yearOfBirth = Integer.parseInt(atributos[13]);
        this.eyeColour = atributos[14];
        this.gender = atributos[15];
        this.hairColour = atributos[16];
        if (atributos[17].equals("VERDADEIRO")) {
            wizard = true;
        } else {
            wizard = false;
        }
    }

    public static Personagem searchById(String id, Personagem personagem[]) {

        for (int i = 0; i < 404; i++) {

            if (personagem[i].id.equals(id))
                return personagem[i];
        }
        return null;
    }

}

class Lista {
    private Personagem[] array;
    private int n;

    public Lista() {
        this(6);
    }


    public Lista(int tamanho) {
        array = new Personagem[tamanho];
        n = 0;
    }

  
    public void inserirInicio(Personagem x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }


    public void inserirFim(Personagem x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    public void inserir(Personagem x, int pos) throws Exception {

        // validar insercao
        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    public Personagem removerInicio() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Personagem resp = array[0];
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public Personagem removerFim() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }


    public Personagem remover(int pos) throws Exception {

        // validar remocao
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Personagem resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public void mostrar() {
        int j = 0;
        for (int i = 0; i < n; i++) {
                System.out.printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                        j++,array[i].id, array[i].name, TP03Q01.arrayToStringWithBraces(array[i].alternate_names), array[i].house,
                        array[i].ancestry, array[i].species, array[i].patronus, array[i].hogwartsStaff,
                        array[i].hogwartsStudent, array[i].actorName, array[i].alive, array[i].dateOfBirth, array[i].yearOfBirth,
                        array[i].eyeColour, array[i].gender, array[i].hairColour, array[i].wizard);
        }
    }

    public boolean pesquisar(Personagem x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}

class TP03Q01 {
    public static boolean isFim(String id) {
        boolean result = false;
        if (id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M') {
            result = true;
        }
        return result;
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
        RandomAccessFile file = new RandomAccessFile("/tmp/characters.csv", "r"); //  "/tmp/characters.csv"
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
        String[] nomes = new String[30];
        Lista lista = new Lista(404);
        while (!isFim(id)) {
            id = scanner.nextLine();
            Personagem personagem1 = Personagem.searchById(id, personagem);
            if (personagem1 != null) {
                lista.inserirFim(personagem1);
            }
        }

        int quant = scanner.nextInt() +1;
        int j = 0;
        String linha = new String();
        while (j < quant) {
            linha = scanner.nextLine();
            String[] parts = linha.split(" ");
            String cmd = parts[0];
            Personagem personagem1 = personagem[1];
            String nome = "";
                switch(cmd){
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
                    System.out.printf("(R) %s\n",nome);
                    break;
                    case "RF":
                    nome = lista.removerFim().name;
                    System.out.printf("(R) %s\n",nome);
                    break;
                    case "R*":
                    nome = lista.remover(Integer.parseInt(parts[1])).name;
                    System.out.printf("(R) %s\n",nome);
                    break;
                }
                j++;
            }
lista.mostrar();
scanner.close();

    }
}
