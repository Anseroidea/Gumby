import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GumbyDisplay {

    public static void main(String[] args){


        //display(10);

        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int threshold = sc.nextInt();
        int numTimes = sc.nextInt();
        int[] blocks = new int[numTimes];
        for (int i = 0; i < numTimes; i++){
            int j = (int) (Math.random() * len * (len + 1) / 2) + 1;
            System.out.println(j);
            blocks[i] = j;
        }
        for (int j = 0; j < numTimes; j++){
            int i = blocks[j];
            int k = getK(i, len);
            int ring = getRing(i, len);
            int displacement = getDisplacement(i, len);
            int x = (k % 3 == 1) ? len - 1 : 0;
            int y = (k % 3 == 2) ? len - 1: 0;
            x += ((k % 3 == 1) ? -2 : 1) * ring;
            y += ((k % 3 == 2) ? -2 : 1) * ring;
            x += ((k % 3 == 2) ? 0 : (k % 3 == 1) ? -1 : 1) * displacement;
            y += ((k % 3 == 2) ? -1 : (k % 3 == 1) ? 1 : 0) * displacement;
            if (Math.sqrt(x * x + y * y) > threshold){
                System.out.println("Unsafe");
            } else {
                System.out.println("Safe");
            }
        }

    }

    public static void display(int len){
        Double[][] matrix = new Double[len][len];
        for (int i = 1; i <= (len + 1) * len / 2; i++){
            int k = getK(i, len);
            int ring = getRing(i, len);
            int displacement = getDisplacement(i, len);
            int x = (k % 3 == 1) ? len - 1 : 0;
            int y = (k % 3 == 2) ? len - 1: 0;
            x += ((k % 3 == 1) ? -2 : 1) * ring;
            y += ((k % 3 == 2) ? -2 : 1) * ring;
            x += ((k % 3 == 2) ? 0 : (k % 3 == 1) ? -1 : 1) * displacement;
            y += ((k % 3 == 2) ? -1 : (k % 3 == 1) ? 1 : 0) * displacement;
            matrix[len - 1 - y][x] = (double) i;
        }
        for (Double[] row : matrix){
            for (Double v : row){
                if (v == null){
                    break;
                }
                System.out.print(String.format("%04.1f", v) + " ");
            }
            System.out.println();
        }
    }

    //K is the "piece" of the ring that "n" is part of
    public static int getK(int n, int len){
        return (int) Math.floor(-Math.sqrt(-2 * n + (len + 0.5) * (len + 0.5)+ 2) + len + 0.5);
    }

    //Ring is the layer in which "n" is a part of
    public static int getRing(int n, int len){
        return (int) Math.floor(getK(n, len)/3.);
    }

    //Displacement is the difference between the initiator and n
    public static int getDisplacement(int n, int len) {
        return n - getInitiator(n, len);
    }

    //Returns the greatest corner with a value less than n
    public static int getInitiator(int n, int len){
        int k = getK(n, len);
        return (int) (-0.5 * k * k + (len + 0.5) * k + 1) - (int) Math.ceil((getK(n, len) % 3)/3.);
    }

}
