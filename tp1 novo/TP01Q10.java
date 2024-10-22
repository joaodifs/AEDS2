public class TP01Q10 {


    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    
    public static boolean isPalindromo(String s) {
        int tamanho = s.length();
        boolean resultado = true; // Assumimos que é um palíndromo até encontrar uma contra prova
        
        if (tamanho >= 2 && s.charAt(0) != s.charAt(tamanho - 1)) {
            resultado = false;
        } else if (tamanho >= 2) {
            resultado = isPalindromo(s.substring(1, tamanho - 1));
        }
        
        return resultado;
    }
    
    public static void main (String[] args){
        MyIO.setCharset("US-ASCII");
        String[] palavra = new String[1000];
        int linha = 0;
    
    
        do{
        palavra[linha] = MyIO.readLine();
        }while (!isFim(palavra[linha++]));
       linha--; // para desconsiderar a ultima linha "FIM"
        boolean resultado;
        for(int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
          resultado = isPalindromo(palavra[i]);
          if(resultado==true){
            MyIO.println("SIM");
            }else{
                MyIO.println("NAO");
            }
    }
       }
    
    }
