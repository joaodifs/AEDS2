import java.util.Random;
public class TP01Q04teste {
    public static void main(String[] args){
    Random gerador = new Random(); 
    gerador.setSeed ( 4 ) ;
    String palavra = "aaaaaaaaaabb";
    char primeira = ( ( char ) ( 'a' + (Math.abs ( gerador.nextInt( ) ) %26 ) ) ) ;
    char segunda = ( ( char ) ( 'a' + (Math.abs ( gerador.nextInt( ) ) %26 ) ) ) ;
    palavra.charAt(8)='a';
    MyIO.println("\n"+palavra);
    }
}