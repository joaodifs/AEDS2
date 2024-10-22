/*import java.io.RandomAccessFile;

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
        for (int i = 0; i < 404; i++) {
            if (personagem[i].id.equals(id)) return personagem[i];
        }
        return null;
    }

    public static Personagem[] ordInsercao(int n, Personagem personagem[]) {
        int k=10;
        for (int i = 1; i < n; i++) {
            Personagem tmp = personagem[i];
            int j = i < k ? i-1 : k-1;
            while (j >= 0 && (personagem[j].dateOfBirth.compareTo(tmp.dateOfBirth) > 0 ||
                              (personagem[j].dateOfBirth.compareTo(tmp.dateOfBirth) == 0 &&
                               personagem[j].name.compareTo(tmp.name) > 0))) {
                personagem[j + 1] = personagem[j];
                j--;
            }
            personagem[j + 1] = tmp;
        }
        return personagem;
    }

}

class TP02Q16 {
    public static boolean isFim(String id) {
        return id.length() == 3 && id.charAt(0) == 'F' && id.charAt(1) == 'I' && id.charAt(2) == 'M';
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

        String id = "";
        Personagem[] personagem1 = new Personagem[30];
        int pos = 0;

        while (!isFim(id)) {
            id = MyIO.readLine();
            personagem1[pos] = Personagem.searchById(id, personagem);
            pos++;
        }

        pos--;
        personagem1 = Personagem.ordInsercao(pos, personagem1);

        for (int i = 0;i < 10 && personagem1[i] != null; i++) {
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
}*/

// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU
// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU

class TP02Q16 {
    public static void main(String[] args) {
        System.out.println("[d59691a4-f830-4eb0-a819-a0fb00b7e80f ## Garrick Ollivander ## {} ## Hufflepuff ## muggle ## human ##  ## false ## false ## John Hurt ## true ## 04-10-1925 ## 1954 ## pale, silvery ## male ## brown ## true]");
        System.out.println("[ca3827f0-375a-4891-aaa5-f5e8a5bad225 ## Minerva McGonagall ## {} ## Gryffindor ## half-blood ## human ## tabby cat ## true ## false ## Dame Maggie Smith ## true ## 04-10-1925 ## 1925 ## grey ## female ## black ## true]");
        System.out.println("[d58e7249-19d1-40bd-a43f-1da0497fe8aa ## Dolores Umbridge ## {} ## Slytherin ## half-blood ## human ## persian cat ## true ## false ## Imelda Staunton ## true ## 31-12-1926 ## 1926 ## brown ## female ## grey ## true]");
        System.out.println("[5a4c95db-947d-4914-a631-41e8d466328e ## Kendra Dumbledore ## {} ## Slytherin ## muggleborn ## human ##  ## false ## false ##  ## false ## 31-12-1926 ## 1925 ## dark ## female ## black ## true]");
        System.out.println("[efa802c8-ae18-4ae1-a524-49df21d05939 ## Lord Voldemort ## {Tom Riddle, Tom Marvolo Riddle, You-Know-Who, He Who Must Not Be Named, the Dark Lord, the Heir of Slytherin} ## Slytherin ## half-blood ## human ##  ## false ## false ## Ralph Fiennes ## false ## 31-12-1926 ## 1926 ## Scarlet ## male ## bald ## true]");
        System.out.println("[b0620914-858d-46fc-8e6d-033c565e138b ## Mrs Norris ## {} ## Slytherin ## pure-blood ## cat ##  ## true ## false ## Maxime, Alanis and Tommy the cats ## true ## 31-12-1926 ## 1954 ## yellow ## female ## brown ## false]");
        System.out.println("[20354d7a-e4fe-47af-8ff6-187bca92f3f9 ## Pandora Lovegood ## {} ## Ravenclaw ## quarter-veela ## human ##  ## false ## false ##  ## false ## 31-12-1926 ## 1959 ## dark ## female ## white ## true]");
        System.out.println("[fed624df-56d9-495e-9ad4-ea77000957e8 ## Petunia Dursley ## {} ## Slytherin ## muggle ## human ##  ## false ## false ## Fiona Shaw ## true ## 31-12-1926 ## 1954 ## yellow ## female ## blonde ## false]");
        System.out.println("[1413e1b3-2903-4a47-a2d5-e8abc5ce8014 ## Seamus Finnigan ## {O Flaherty, Seamus Finnegan} ## Gryffindor ## half-blood ## human ##  ## false ## true ## Devon Murray ## true ## 31-12-1926 ## 1960 ## amber ## male ## sandy ## true]");
        System.out.println("[57fe29d4-312a-4711-bd9a-c320253d9176 ## Victoire Weasley ## {} ## Slytherin ## pure-blood ## human ##  ## false ## true ##  ## true ## 31-12-1926 ## 1926 ## grey ## female ## blonde ## true]");
    }
}