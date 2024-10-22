import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class TP01Q08 {
    public static void escreverArquivo(double[] numeros, File file) throws IOException {
        BufferedWriter escrever = new BufferedWriter(new FileWriter(file));
        for (double numero : numeros) {
            escrever.write(Double.toString(numero));
            escrever.newLine();
        }
        escrever.close();
    }

    public static void lerArquivo(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        long length = raf.length();
        while (length > 0) {
            length--;
            raf.seek(length);
            char c = (char) raf.read();
            if (c == '\n' && length != 0) {
                String line = raf.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            }
        }
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("valores.txt");
        Scanner ler = new Scanner(System.in);
        int quant = ler.nextInt(); // Lê a quantidade de números
        double[] numeros = new double[quant];
        for (int i = 0; i < quant; i++) {
            numeros[i] = ler.nextDouble();
        }
        escreverArquivo(numeros, file);
        lerArquivo(file);
        ler.close(); // Fechar o Scanner
    }
}