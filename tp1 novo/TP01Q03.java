import java.util.Scanner;
public class TP01Q03 {


public static boolean isFim(String s){
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
}

public static void codificar(String s, int chave){
    int tamanho = s.length();
    String stringcod = "";
    tamanho--; // o valor da ultima posicao eh uma a menos que o tamanho
        for(int i=0;i<=tamanho;i++){
            
                stringcod = stringcod + (char)((int)s.charAt(i) + chave);

            }
            System.out.println(""+stringcod);
            ; // para comparar a posicao 0 com a ultima, 1 com a penultima...
        }

public static void main (String[] args){
    MyIO.setCharset("UTF-8");
    String[] palavra = new String[1000];
    int linha = 0;
    int chave = 3;
    Scanner RL = new Scanner(System.in);
    do{
    // palavra[linha] = MyIO.readLine();
    palavra[linha] = RL.nextLine();
    }while (!isFim(palavra[linha++]));
    RL.close();
   linha--; // para desconsiderar a ultima linha "FIM"

    for(int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
      codificar(palavra[i], chave);
   }

}
}