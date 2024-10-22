import java.util.Scanner;

public class TP01Q12 {

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

public static void codificar(String s, int chave, int i){
        if (i < s.length()){ 
            MyIO.print((char) ((int) s.charAt(i) + chave));
            codificar(s, chave, i + 1);
        }else{
            MyIO.println("");
        }
    }
    public static void main(String[] args){
        MyIO.setCharset("ISO-8859-1");
        String[] palavra = new String[1000];
        int linha = 0;
        int chave = 3;
        do {
            palavra[linha] = MyIO.readLine();
        } while (!isFim(palavra[linha++]));
        linha--; // para desconsiderar a ultima linha "FIM"

        for (int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
            codificar(palavra[i], chave, 0);
        }
    }
}