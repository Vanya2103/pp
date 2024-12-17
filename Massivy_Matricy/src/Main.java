import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static boolean ISsortOBR(int [] arr){
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]<arr[i+1])
                return false;
        }
        return true;
    }
    public static boolean ISsortPRM(int [] arr){
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]>arr[i+1])
                return false;
        }
        return true;
    }
    public static int FIRST(int[][]arr){
        int max =Integer.MIN_VALUE;
        for(int [] row:arr){
            if(ISsortOBR(row)||ISsortPRM(row)){
                for(int num:row){
                    if(num>max)
                        max=num;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность:");
        int n = in.nextInt();
        int m= in.nextInt();
        int[][] arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
            System.out.print("Элемент:");
            arr[i][j]=in.nextInt();}
        }
//        for(int i =0;i< arr.length;i++){
//            for (int j = 0;j<m;j++){
//                arr[i][j]=(int)(Math.random()*100);
//            }
//        }
        for(int i=0;i<arr.length;i++){
            for(int j =0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");}
            System.out.println(" ");
        }
        int min = FIRST(arr);
        System.out.println("MAX:"+min);
    }
}