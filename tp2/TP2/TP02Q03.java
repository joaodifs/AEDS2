
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


class TP02Q03{
    public static boolean isFim(String id){
        boolean result = false;
        if(id.length()==3 && id.charAt(0)=='F' && id.charAt(1)=='I' && id.charAt(2)=='M'){
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
        RandomAccessFile file = new RandomAccessFile("characters.csv","r");
        file.readLine();
        Personagem[] personagem = new Personagem[404];
        for(int i =0; i<404 ; i++){
            String line = file.readLine();
            String[] atributos = line.split(";");
            personagem[i] = new Personagem(atributos);
        }
        file.close();
        String id = "";
        String[] nomes = new String[30];
        int i =0;
       while(!isFim(id)){
            id = MyIO.readLine();
            Personagem personagem1 = Personagem.searchById(id, personagem);
            if (personagem1 != null) {
                nomes[i++] = personagem1.name;
                  }
        }
        String nome = MyIO.readLine();
         while(!isFim(nome)){
            boolean resp = false;
            for(int j = 0; j<i; j++){
            if(nomes[j].equals(nome)){
                System.out.printf("SIM\n");
                resp = true;
                j=i;
            }
        }
        if(resp==false){System.out.printf("NAO\n");}
        nome = MyIO.readLine();
         }

    }
}
