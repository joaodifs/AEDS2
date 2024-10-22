import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
    Scanner sc = new Scanner(System.in);
    int resultado = 0;
    double valor = sc.nextDouble();
    System.out.println("NOTAS:");
    resultado = (int)valor/100;
    System.out.printf("%d nota(s) de R$ 100.00\n",resultado);
    valor = valor - resultado*100;
    resultado = (int)valor/50;
    System.out.printf("%d nota(s) de R$ 50.00\n",resultado);
    valor = valor - resultado*50;
    resultado = (int)valor/20;
    System.out.printf("%d nota(s) de R$ 20.00\n",resultado);
    valor = valor - resultado*20;
    resultado = (int)valor/10;
    System.out.printf("%d nota(s) de R$ 10.00\n",resultado);
    valor = valor - resultado*10;
    resultado = (int)valor/5;
    System.out.printf("%d nota(s) de R$ 5.00\n",resultado);
    valor = valor - resultado*5;
    resultado = (int)valor/2;
    System.out.printf("%d nota(s) de R$ 2.00\n",resultado);
    valor = valor - resultado*2;
    valor = valor*100;
    System.out.println("MOEDAS:");
    

    resultado = (int)valor/100;
    System.out.printf("%d moedas(s) de R$ 1.00\n",resultado);
    valor = valor - resultado*100;
    resultado = (int)valor/50;
    System.out.printf("%d moedas(s) de R$ 0.50\n",resultado);
    valor = valor - resultado*50;
    resultado = (int)valor/25;
    System.out.printf("%d moedas(s) de R$ 0.25\n",resultado);
    valor = valor - resultado*25;
    resultado = (int)valor/10;
    System.out.printf("%d moedas(s) de R$ 0.10\n",resultado);
    valor = valor - resultado*10;
    resultado = (int)valor/5;
    System.out.printf("%d moedas(s) de R$ 0.05\n",resultado);
    valor = valor - resultado*5;
    System.out.printf("%d moedas(s) de R$ 0.01\n",(int)valor);


    }
}
