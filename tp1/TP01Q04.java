import java.util.Random;
public class TP01Q04 {
    public static String Aleatorio(String s, char a, char b) {

        int i, tamanho = s.length();
        String resultado = "";
    
        for (i = 0; i < tamanho; i++) {
            if(s.charAt(i)==a){
                resultado = resultado + b; // 
            }else{
                resultado = resultado + s.charAt(i);
            }
            }
            return resultado;
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

        Random gerador = new Random( ) ;
        gerador.setSeed ( 4 ) ;
        char primeira;
        char segunda;
        for (int i = 0; i < linha; i++) { // percorrer ate a ultima linha, antes do FIM
primeira = ( ( char ) ( 'a' + (Math. abs ( gerador.nextInt( ) ) %26 ) ) ) ; // primeira letra aleatoria
segunda = ( ( char ) ( 'a' + (Math. abs ( gerador.nextInt( ) ) %26 ) ) ) ; // segunda letra aleatoria
          MyIO.println("" + Aleatorio(palavra[i],primeira,segunda));
    
        }
    
      }
}

/*
Random gerador = new Random( ) ;
gerador . setSeed ( 4 ) ;
System . out . pr int ln ( ( char ) ( ’ a ’ + (Math. abs ( gerador . nextInt ( ) ) % 2 6 ) ) ) ;
 */