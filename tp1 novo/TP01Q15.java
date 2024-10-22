import java.util.Scanner;

public class TP01Q15 {
    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');

    }

    public static boolean X1(String s) {
        boolean result = true; // Inicialmente, assumimos que a string é composta apenas de vogais
        
        if (!s.isEmpty()) {
            if (!(s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u' ||
                  s.charAt(0) == 'A' || s.charAt(0) == 'E' || s.charAt(0) == 'I' || s.charAt(0) == 'O' || s.charAt(0) == 'U')) {
                result = false;
            } else {
                result = X1(s.substring(1));
            }
        }
        return result;
    }
    public static boolean X2(String s) {
        boolean result = true;
        if (!s.isEmpty()) {
            if (!(s.charAt(0) == 'b' || s.charAt(0) == 'c' || s.charAt(0) == 'd' || s.charAt(0) == 'f'
                || s.charAt(0) == 'g' || s.charAt(0) == 'h' || s.charAt(0) == 'j' || s.charAt(0) == 'k' || s.charAt(0) == 'l'
                || s.charAt(0) == 'm' || s.charAt(0) == 'n' || s.charAt(0) == 'p' || s.charAt(0) == 'q' || s.charAt(0) == 'r'
                || s.charAt(0) == 's' || s.charAt(0) == 't' || s.charAt(0) == 'v' || s.charAt(0) == 'w' || s.charAt(0) == 'x'
                || s.charAt(0) == 'y' || s.charAt(0) == 'z' || s.charAt(0) == 'B' || s.charAt(0) == 'C' || s.charAt(0) == 'D'
                || s.charAt(0) == 'F' || s.charAt(0) == 'G' || s.charAt(0) == 'H' || s.charAt(0) == 'J' || s.charAt(0) == 'K'
                || s.charAt(0) == 'L' || s.charAt(0) == 'M' || s.charAt(0) == 'N' || s.charAt(0) == 'P' || s.charAt(0) == 'Q'
                || s.charAt(0) == 'R' || s.charAt(0) == 'S' || s.charAt(0) == 'T' || s.charAt(0) == 'V' || s.charAt(0) == 'W'
                || s.charAt(0) == 'X' || s.charAt(0) == 'Y' || s.charAt(0) == 'Z')) {
                        result = false;
                    } else {
                        result = X1(s.substring(1));
            }
        }
        return (result);
    }

    public static boolean X3(String s) {
        boolean result = true;
        if (!s.isEmpty()) {
            if (!(s.charAt(0) >= '0' && s.charAt(0) <= '9')) {
                result = false;
            }else{
            result = X3(s.substring(1));
        }
    }
    return (result);
}

    public static boolean X4(String s) {
        int tamanho = s.length();
        boolean resp = true;
        int quant = 0;
        for (int i = 0; i < tamanho; i++) {
            if (s.charAt(i) == ',' || s.charAt(i) == '.') {
                quant++;
                if (quant > 1) {
                    i = tamanho;
                    resp = false;
                }
            } else {
                if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                    i = tamanho;
                    resp = false;
                }
            }
        }
        return (resp);
    }

    public static void main(String[] args) {
        String[] palavra = new String[1000];
        int linha = 0;
        Scanner RL = new Scanner(System.in);
        do {
            palavra[linha] = RL.nextLine();
        } while (!isFim(palavra[linha++]));
        RL.close();
        linha--;
        for(int i=0; i<linha;i++){
            if(!X1(palavra[i])){
                System.out.print("NAO ");
            }else{
                System.out.print("SIM ");
            }
            if(!X2(palavra[i])){
                System.out.print("NAO ");
            }else{
                System.out.print("SIM ");
            }
         if(!X3(palavra[i])){
                System.out.print("NAO ");
            }else{
                System.out.print("SIM ");
            }
        if(!X4(palavra[i])){
            System.out.println("NAO");
        }else{
            System.out.println("SIM");
        }
    }
}
}