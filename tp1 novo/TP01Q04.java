import java.util.Random;
import java.util.Scanner;

public class TP01Q04 {

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void codificar(String s, int chave, char original, char substituida) {
        String replaced = "";
        // System.out.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

        replaced = s.replace(original, substituida);

        // for(int i=0;i<=tamanho;i++){

        // stringcod = stringcod + (char)((int)s.charAt(i) + chave);

        // }
        // for(int i=0;i<=tamanho;i++){

        // replaced2 = replaced2 + (char)((int)replaced.charAt(i));

        // }

        // replaced = (String)(int)replaced;
        System.out.println(""+replaced);
        ; // para comparar a posicao 0 com a ultima, 1 com a penultima...
    }

    public static void main(String[] args) {
        String[] palavra = new String[1000];
        int linha = 0;
        int chave = 3;
        char substituida = ' ';
        char original = ' ';
        Scanner RL = new Scanner(System.in);
        do {
            // palavra[linha] = MyIO.readLine();
            palavra[linha] = RL.nextLine();
        } while (!isFim(palavra[linha++]));
        RL.close();
        linha--; // para desconsiderar a ultima linha "FIM"
        Random gerador = new Random();
        gerador.setSeed(4);
        for (int i = 0; i < linha; i++) { // percorrer ate a ultima linha, antes do FIM
            // System.out.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
            original = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            // Random gerador1 = new Random();
            // gerador1.setSeed(4);
            substituida = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            codificar(palavra[i], chave, original, substituida);
        }

    }
}