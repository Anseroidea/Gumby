import java.util.Scanner;

public class Gumby {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int threshold = sc.nextInt();
        int numTimes = sc.nextInt();
        for (int j = 0; j < numTimes; j++){
            int i = sc.nextInt();
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
