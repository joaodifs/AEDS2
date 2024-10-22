import java.util.Scanner;
public class main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double pi = 3.14159;
        double r = sc.nextDouble();
        double a = r*r*pi;
        System.out.printf("A=%.4f\n",a);
    }

}