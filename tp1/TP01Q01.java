public class TP01Q01 {


public static boolean isFim(String s){
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
}

public static void isPalindromo(String s){
    int tamanho = s.length();
    tamanho--; // o valor da ultima posicao eh uma a menos que o tamanho
    boolean diferentes = false;
        for(int i=0;i<=tamanho;i++){
            if(s.charAt(i)!=s.charAt(tamanho--)){
                diferentes=true;
            }
            ; // para comparar a posicao 0 com a ultima, 1 com a penultima...
        }
     if(diferentes==false){
        MyIO.println("SIM");
        }else{
            MyIO.println("NAO");
        }
}

public static void main (String[] args){
    String[] palavra = new String[1000];
    int linha = 0;


    do{
    palavra[linha] = MyIO.readLine();
    }while (!isFim(palavra[linha++]));
   linha--; // para desconsiderar a ultima linha "FIM"

    for(int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
      isPalindromo(palavra[i]);
   }

}
    
}
