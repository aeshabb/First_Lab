import java.text.DecimalFormat;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
    int[] c1 = new int[10];

    for(int i = 0; i < 10; i++) {
        c1[i] = i*2 + 6;
    }

    float[] x = new float[17];
    float min = -15;
    float max = 2;

    for(int i = 0; i < 17; i++){
        Random rand = new Random();
        float num = min + rand.nextFloat()*(max - min);
        x[i] = num;
    }
    double[][] c = new double[10][17];
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 17; j++) {
            if(c1[i] == 8) {
                double k = Math.pow((Math.asin(Math.pow((Math.E), -(Math.abs(x[j]))))), ((Math.tan(Math.pow(Math.E, x[j])) + 1) / 4));
                c[i][j] = k;
            }
            else if(c1[i] == 6 || c1[i] == 12 || c1[i] == 14 || c1[i] == 16 || c1[i] == 22) {
                c[i][j] = Math.sin(Math.pow((0.5 / (Math.pow((0.5 / x[j]), x[j]) - 1)), Math.sin(x[j])));
            }
            else {
                c[i][j] = Math.pow((0.5 * (Math.pow(Math.asin((x[j] - 6.5) / 17), 2) / (0.25 - Math.tan(Math.tan(Math.pow((x[j] / 2), 2)))))), 2);
            }
        }
    }
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 17; j++) {
            String formatDouble = new DecimalFormat("#0.00").format(c[i][j]);
            System.out.print(formatDouble + " ");
        }
        System.out.println("\n");
    }
}
}