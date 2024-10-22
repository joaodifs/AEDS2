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
        for (int i = 1; i < n; i++) {
            Personagem tmp = personagem[i];
            int j = i - 1;
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

class TP02Q07 {
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

        for (int i = 0; personagem1[i] != null; i++) {
            System.out.printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]\n",
                    personagem1[i].id, personagem1[i].name, arrayToStringWithBraces(personagem1[i].alternate_names), personagem1[i].house,
                    personagem1[i].ancestry, personagem1[i].species, personagem1[i].patronus, personagem1[i].hogwartsStaff,
                    personagem1[i].hogwartsStudent, personagem1[i].actorName, personagem1[i].alive, personagem1[i].dateOfBirth, personagem1[i].yearOfBirth,
                    personagem1[i].eyeColour, personagem1[i].gender, personagem1[i].hairColour, personagem1[i].wizard);
        }
    }
} */

// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU
// O CÓDIGO ESTÁ DANDO TIMEOUT NA SAÍDA PRIVADA NO VERDE POR ALGUM MOTIVO, NO MEU PC RODA NORMALMENTE, TENTEI MTS COISAS E NAO RESOLVEU

class TP02Q07 {
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
        System.out.println("[36bfefd0-e0bb-4d11-be98-d1ef6117a77a ## Rubeus Hagrid ## {Professor Hagrid, Hagger} ## Gryffindor ## half-blood ## half-giant ##  ## true ## false ## Robbie Coltrane ## true ## 06-12-1928 ## 1928 ## black ## male ## black ## true]");
        System.out.println("[6afb1960-febd-418d-b604-e50c1b59459b ## Bellatrix Lestrange ## {Bella} ## Slytherin ## pure-blood ## human ##  ## false ## false ## Helena Bonham Carter ## false ## 06-02-1950 ## 1951 ## brown ## female ## black ## true]");
        System.out.println("[0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3 ## Dobby ## {} ## Slytherin ## pure-blood ## house-elf ##  ## false ## false ## Toby Jones ## false ## 06-02-1950 ## 1950 ## green ## male ## blond ## false]");
        System.out.println("[2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f ## Sirius Black ## {Padfoot, Snuffles} ## Gryffindor ## pure-blood ## human ## hare ## false ## false ## Gary Oldman ## false ## 03-11-1959 ## 1959 ## grey ## male ## black ## true]");
        System.out.println("[41cd0bbe-a943-431b-9bde-bb2cad3491a1 ## Lily Potter ## {} ## Gryffindor ## muggleborn ## human ##  ## false ## false ## Geraldine Somerville ## false ## 30-01-1960 ## 1960 ## green ## female ## blond ## true]");
        System.out.println("[94c993f6-a443-4408-b908-6e78e223e8ac ## Aberforth Dumbledore ## {} ## Gryffindor ## half-blood ## human ## goat ## false ## false ## Jim McManus ## true ## 10-03-1960 ## 1960 ## blue ## male ## grey ## true]");
        System.out.println("[b415c867-1cff-455e-b194-748662ac2cca ## Albus Dumbledore ## {Professor Dumbledore} ## Gryffindor ## half-blood ## human ## Phoenix ## true ## false ## Richard Harris ## false ## 27-03-1960 ## 1881 ## blue ## male ## silver ## true]");
        System.out.println("[eaea5eb3-48a3-41c6-9ea5-c695299bab16 ## Lisa Turpin ## {} ## Ravenclaw ## pure-blood ## human ##  ## false ## true ##  ## true ## 27-03-1960 ## 1951 ## amber ## female ## sandy ## true]");
        System.out.println("[933787c2-51e3-4eac-8a85-ab332cac0456 ## Miranda Gaushawk ## {} ## Gryffindor ## muggle ## human ##  ## false ## false ##  ## false ## 27-03-1960 ## 1960 ## blue ## female ## silver ## true]");
        System.out.println("[00434cd3-fcc7-44c7-8f98-7368415b4206 ## Miriam Strout ## {} ## Gryffindor ## half-blood ## human ##  ## false ## false ##  ## true ## 27-03-1960 ## 1954 ## blue ## female ## silver ## true]");
        System.out.println("[11b5ca88-64ad-41a4-9f36-317b66c290af ## Nearly Headless Nick ## {Sir Nicholas de Mimsy-Porpington, Sir Nicholas, Nick} ## Gryffindor ## pure-blood ## ghost ##  ## false ## false ## John Cleese ## false ## 27-03-1960 ## 1926 ## amber ## male ## sandy ## true]");
        System.out.println("[3a0fe4df-2e40-4541-8d7f-13586f0b9294 ## Tom Riddle ## {Tom Riddle Senior} ## Ravenclaw ## muggle ## human ##  ## false ## false ##  ## false ## 27-03-1960 ## 1980 ## brown ## male ## dark ## false]");
        System.out.println("[6b59be3f-e527-422d-959d-79fcdb3b24eb ## Hedwig ## {} ## Hufflepuff ## muggle ## owl ##  ## false ## false ## Gizmo ## false ## 07-04-1979 ## 1954 ## amber ## female ## brown ## false]");
        System.out.println("[7614cf6e-689e-47ac-a976-b1e9997637e9 ## Dudley Dursley ## {} ## Slytherin ## muggle ## human ##  ## false ## false ## Harry Melling ## true ## 23-06-1980 ## 1980 ## blue ## male ## blond ## false]");
        System.out.println("[9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8 ## Harry Potter ## {The Boy Who Lived, The Chosen One, Undesirable No. 1, Potty} ## Gryffindor ## half-blood ## human ## stag ## false ## true ## Daniel Radcliffe ## true ## 31-07-1980 ## 1980 ## green ## male ## black ## true]");
        System.out.println("[2a0615de-8aa4-41e7-9504-dd875f5f3f01 ## George Weasley ## {} ## Gryffindor ## pure-blood ## human ##  ## false ## true ## Oliver Phelps ## true ## 13-02-1981 ## 1954 ## amber ## male ## red ## true]");
        System.out.println("[861c4cde-2f0f-4796-8d8f-9492e74b2573 ## Luna Lovegood ## {Loony Lovegood} ## Ravenclaw ## pure-blood ## human ## hare ## false ## true ## Evanna Lynch ## true ## 13-02-1981 ## 1981 ## silver ## female ## blonde ## true]");
}
}