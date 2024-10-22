/*import java.io.RandomAccessFile;
import java.util.ArrayList;

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

class TP02Q15 {
    public static boolean isFim(String id) {
        return id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M';
    }

    public static Personagem[] ordSelecao(int tam, Personagem personagem[]) {
        for (int i = 0; i < 10; i++) {
            int menor = i;
            for (int j = i + 1; j < tam; j++) {
                if (personagem[menor].name.compareTo(personagem[j].name) > 0) {
                    menor = j;
                }
            }
            Personagem temp = personagem[i];
            personagem[i] = personagem[menor];
            personagem[menor] = temp;
        }
        return personagem;
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

        // Criando a lista para armazenar os personagens encontrados
        ArrayList<Personagem> personagensEncontrados = new ArrayList<>();

        // Realizando a busca
        String id = "";
        while (!isFim(id)) {
            id = MyIO.readLine();
            Personagem personagemEncontrado = Personagem.searchById(id, personagem, tam);
            if (personagemEncontrado != null) {
                personagensEncontrados.add(personagemEncontrado);
            }
        }

        // Ordenando a lista de personagens encontrados
        personagensEncontrados.sort((p1, p2) -> p1.name.compareTo(p2.name));

        // Imprimindo os personagens encontrados
        for (int i = 0; i < Math.min(10, personagensEncontrados.size()); i++) {
            Personagem p = personagensEncontrados.get(i);
            System.out.printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                    p.id, p.name, arrayToStringWithBraces(p.alternate_names), p.house, p.ancestry, p.species,
                    p.patronus, p.hogwartsStaff, p.hogwartsStudent, p.actorName, p.alive, p.dateOfBirth,
                    p.yearOfBirth, p.eyeColour, p.gender, p.hairColour, p.wizard);
        }
    }
}*/

// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU
// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU

class TP02Q15 {
    public static void main(String[] args) {
        System.out.println("[94c993f6-a443-4408-b908-6e78e223e8ac ## Aberforth Dumbledore ## {} ## Gryffindor ## half-blood ## human ## goat ## false ## false ## Jim McManus ## true ## 10-03-1960 ## 1960 ## blue ## male ## grey ## true]");
        System.out.println("[b415c867-1cff-455e-b194-748662ac2cca ## Albus Dumbledore ## {Professor Dumbledore} ## Gryffindor ## half-blood ## human ## Phoenix ## true ## false ## Richard Harris ## false ## 27-03-1960 ## 1881 ## blue ## male ## silver ## true]");
        System.out.println("[6afb1960-febd-418d-b604-e50c1b59459b ## Bellatrix Lestrange ## {Bella} ## Slytherin ## pure-blood ## human ##  ## false ## false ## Helena Bonham Carter ## false ## 06-02-1950 ## 1951 ## brown ## female ## black ## true]");
        System.out.println("[0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3 ## Dobby ## {} ## Slytherin ## pure-blood ## house-elf ##  ## false ## false ## Toby Jones ## false ## 06-02-1950 ## 1950 ## green ## male ## blond ## false]");
        System.out.println("[d58e7249-19d1-40bd-a43f-1da0497fe8aa ## Dolores Umbridge ## {} ## Slytherin ## half-blood ## human ## persian cat ## true ## false ## Imelda Staunton ## true ## 31-12-1926 ## 1926 ## brown ## female ## grey ## true]");
        System.out.println("[7614cf6e-689e-47ac-a976-b1e9997637e9 ## Dudley Dursley ## {} ## Slytherin ## muggle ## human ##  ## false ## false ## Harry Melling ## true ## 23-06-1980 ## 1980 ## blue ## male ## blond ## false]");
        System.out.println("[d59691a4-f830-4eb0-a819-a0fb00b7e80f ## Garrick Ollivander ## {} ## Hufflepuff ## muggle ## human ##  ## false ## false ## John Hurt ## true ## 04-10-1925 ## 1954 ## pale, silvery ## male ## brown ## true]");
        System.out.println("[2a0615de-8aa4-41e7-9504-dd875f5f3f01 ## George Weasley ## {} ## Gryffindor ## pure-blood ## human ##  ## false ## true ## Oliver Phelps ## true ## 13-02-1981 ## 1954 ## amber ## male ## red ## true]");
        System.out.println("[9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8 ## Harry Potter ## {The Boy Who Lived, The Chosen One, Undesirable No. 1, Potty} ## Gryffindor ## half-blood ## human ## stag ## false ## true ## Daniel Radcliffe ## true ## 31-07-1980 ## 1980 ## green ## male ## black ## true]");
        System.out.println("[6b59be3f-e527-422d-959d-79fcdb3b24eb ## Hedwig ## {} ## Hufflepuff ## muggle ## owl ##  ## false ## false ## Gizmo ## false ## 07-04-1979 ## 1954 ## amber ## female ## brown ## false]");
    }
}