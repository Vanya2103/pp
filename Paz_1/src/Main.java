import java.util.Scanner;
import java.text.*;
class COSSSSS{
    public static double COS1(double x, int k) {
        double y = Math.toRadians(x%360);
        double res = 1;
        double term = 1;
        for (int i = 1; i <= k; i++) {
            term *= -y * y / (2 * i * (2 * i - 1));
            res += term;
        }
        return res;
    }
    public static double COS2(double x){
        double y= Math.toRadians(x);
        double res = Math.cos(y);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        System.out.println("Enter K:");
        int k = in.nextInt();
//        System.out.println("K:"+k);
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(k);
        System.out.println("Enter X:");
        double x = in.nextDouble();
//        System.out.println("X:"+formatter.format(x));
        double res1 = COSSSSS.COS1(x,k);
        System.out.println("Res 1:"+formatter.format(res1));
        double res2 = COSSSSS.COS2(x);
        System.out.println("Res 2:"+formatter.format(res2));
    }
}