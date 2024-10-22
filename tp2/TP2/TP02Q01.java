
import java.io.RandomAccessFile;
import java.util.Arrays;
class Personagem{

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

     Personagem(String[] atributos){
        this.id = atributos[0];
        this.name = atributos[1];
        this.alternate_names = atributos[2].replaceAll("'", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        this.house = atributos[3];
        this.ancestry = atributos[4];
        this.species = atributos[5];
        this.patronus = atributos[6];
        if(atributos[7].equals("VERDADEIRO")){
            hogwartsStaff = true;
        }else{
            hogwartsStaff = false;
        }
        if(atributos[8].equals("VERDADEIRO")){
            hogwartsStudent = "true";
        }else{
            hogwartsStudent = "false";
        }
        this.hogwartsStudent = atributos[8];
        this.actorName = atributos[9];
        if(atributos[10].equals("VERDADEIRO")){
            alive = true;
        }else{
            alive = false;
        }
        this.dateOfBirth = atributos[12];
        this.yearOfBirth = Integer.parseInt(atributos[13]);
        this.eyeColour = atributos[14];
        this.gender = atributos[15];
        this.hairColour = atributos[16];
        if(atributos[17].equals("VERDADEIRO")){
            wizard = true;
        }else{
            wizard = false;
        }
     }

     public static Personagem searchById(String id, Personagem personagem[]) {

        for(int i = 0; i < 404; i++) {
    
            if(personagem[i].id.equals(id)) return personagem[i];
        }
        return null;
    }
    
}


class TP02Q01{
    public static boolean isFim(String id){
        boolean result = false;
        if(id.length()==3 && id.charAt(0)=='F' && id.charAt(1)=='I' && id.charAt(2)=='M'){
            result = true;
        }
        return result;
    }


    // para imprimir chaves em vez de colchetes no alternate_names
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
        RandomAccessFile file = new RandomAccessFile("characters.csv","r"); // pra mandar no verde é "/tmp/characters.csv"
        file.readLine(); // pular a primeira linha que nao é util
        Personagem[] personagem = new Personagem[404]; // criando um array de personagens para guardar todos.
        for(int i =0; i<404 ; i++){
            String line = file.readLine();
            String[] atributos = line.split(";"); // separando as linhas por ponto e virgula, cada linha vira um atributo
            personagem[i] = new Personagem(atributos);
        }
        file.close();
        String id = "";
        while(!isFim(id)){
            id = MyIO.readLine();
            Personagem personagem1 = Personagem.searchById(id, personagem);
            if (personagem1 != null) {
                System.out.printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %b ## %s ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]",
                personagem1.id, personagem1.name, arrayToStringWithBraces(personagem1.alternate_names), personagem1.house,
                personagem1.ancestry, personagem1.species, personagem1.patronus, personagem1.hogwartsStaff,
                personagem1.hogwartsStudent, personagem1.actorName, personagem1.alive, personagem1.dateOfBirth, personagem1.yearOfBirth,
                personagem1.eyeColour, personagem1.gender, personagem1.hairColour, personagem1.wizard);
            }
    }
}
}