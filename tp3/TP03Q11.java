import java.io.RandomAccessFile;
import java.util.Scanner;

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

    public static Personagem searchById(String id, Personagem personagem[], int tam) {
        for (int i = 0; i < tam; i++) {
            if (personagem[i].id.equals(id))
                return personagem[i];
        }
        return null;
    }
}

class TP03Q11 {
    public static boolean isFim(String id) {
        return id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M';
    }

    public static void quicksort(Personagem personagem[], int esq, int dir) {
        int i = esq, j = dir;
        Personagem pivo = personagem[(esq + dir) / 2];
        while (i <= j) {
            while (compare(personagem[i], pivo) < 0) i++;
            while (compare(personagem[j], pivo) > 0) j--;
            if (i <= j) {
                Personagem temp = personagem[i];
                personagem[i] = personagem[j];
                personagem[j] = temp;
                i++;
                j--;
            }
        }
        if (esq < j) quicksort(personagem, esq, j);
        if (i < dir) quicksort(personagem, i, dir);
    }

    public static int compare(Personagem a, Personagem b) {
        int houseComparison = a.house.compareTo(b.house);
        if (houseComparison != 0) {
            return houseComparison;
        }
        return a.name.compareTo(b.name);
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
        int tam = 0; // Variável para armazenar o número de personagens lidos
        Personagem[] personagem = new Personagem[404];

        // Lendo o arquivo e populando o array de personagens
        while (file.getFilePointer() < file.length()) {
            String line = file.readLine();
            String[] atributos = line.split(";");
            personagem[tam] = new Personagem(atributos);
            tam++;
        }
        file.close();

        // Criando o array para armazenar os personagens encontrados
        Personagem personagem1[] = new Personagem[28];
        int pos = 0;
        String id = "";
        Scanner scanner = new Scanner(System.in);

        // Realizando a busca
        while (!isFim(id)) {
            id = scanner.nextLine();
            if (!isFim(id)) {
                Personagem p = Personagem.searchById(id, personagem, tam);
                if (p != null) {
                    personagem1[pos] = p;
                    pos++;
                }
            }
        }
        scanner.close();
        int tamanhoFinal = pos;

        // Ordenando os personagens
        quicksort(personagem1, 0, tamanhoFinal - 1);

        for (int i = 0; i < tamanhoFinal; i++) {
            System.out.printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                    personagem1[i].id, personagem1[i].name,
                    arrayToStringWithBraces(personagem1[i].alternate_names), personagem1[i].house,
                    personagem1[i].ancestry, personagem1[i].species, personagem1[i].patronus,
                    personagem1[i].hogwartsStaff, personagem1[i].hogwartsStudent, personagem1[i].actorName,
                    personagem1[i].alive, personagem1[i].dateOfBirth, personagem1[i].yearOfBirth,
                    personagem1[i].eyeColour, personagem1[i].gender, personagem1[i].hairColour,
                    personagem1[i].wizard);
        }
    }
}
