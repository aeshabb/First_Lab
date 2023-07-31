import java.util.Random;

public class Second_task {
    public static void main(String[] args) {
        float[] x = new float[17];
        float min = -15;
        float max = 2;

        for(int i = 0; i < 17; i++){
            Random rand = new Random();
            float num = min + rand.nextFloat()*(max - min);
            x[i] = num;
        }
        for(int i = 0; i < 17; i++){
            System.out.print(x[i] + "\n");
        }
}
}