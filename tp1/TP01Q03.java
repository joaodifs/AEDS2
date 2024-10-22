public class TP01Q03 {

  public static String Codificar(String s) {

    int i, tamanho = s.length();
    String cifra = "";

    for (i = 0; i < tamanho; i++) {
      cifra = cifra + (char) (s.charAt(i) + 3);
    }
    return cifra;
  }

  public static boolean isFim(String s) {
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String[] args) {

    String[] palavra = new String[1000];
    int linha = 0;

    do {
      palavra[linha] = MyIO.readLine();
    } while (isFim(palavra[linha++]) == false);
    linha--; // para desconsiderar a ultima linha "FIM"

    for (int i = 0; i < linha; i++) { // percorrer ate a ultima linha, antes do FIM
      MyIO.println("" + Codificar(palavra[i]));

    }

  }
}
