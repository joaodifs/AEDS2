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
        this.hogwartsStudent = atributos[8];
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

class TP02Q18 {
    public static boolean isFim(String id) {
        return id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M';
    }

    public static void quickSort(Personagem personagem[], int esquerda, int direita) {
        if (esquerda < direita) {
            int indiceParticao = particionar(personagem, esquerda, direita);
            quickSort(personagem, esquerda, indiceParticao - 1);
            quickSort(personagem, indiceParticao + 1, direita);
        }
    }

    public static int particionar(Personagem personagem[], int esquerda, int direita) {
        // Verifica se o elemento em personagem[direita] é null
        if (personagem[direita] == null) {
            return -1; // Ou outra ação apropriada, dependendo da lógica do seu programa
        }
        
        String pivo = personagem[direita].name;
        int i = esquerda - 1;
        for (int j = esquerda; j < direita; j++) {
            // Verifica se o elemento em personagem[j] é null
            if (personagem[j] != null && personagem[j].name.compareTo(pivo) <= 0) {
                i++;
                Personagem temp = personagem[i];
                personagem[i] = personagem[j];
                personagem[j] = temp;
            }
        }
        Personagem temp = personagem[i + 1];
        personagem[i + 1] = personagem[direita];
        personagem[direita] = temp;
        return i + 1;
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
        RandomAccessFile file = new RandomAccessFile("characters.csv", "r");
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

        // Realizando a busca
        while (!isFim(id)) {
            id = MyIO.readLine();
            personagem1[pos] = Personagem.searchById(id, personagem, tam);
            pos++;
        }

        pos--;

        // Ordenando o array de personagens encontrados com Quicksort
        quickSort(personagem1, 0, pos);

        // Imprimindo os personagens encontrados
        for (int i = 0; i < personagem1.length && personagem1[i] != null; i++) {
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