import java.util.Scanner;
public class Main {
    public static int[] proizvedenie(int [][] arr,int n){
        int[] res =new int [n];
            for(int i=0;i<n;i++){
                res[i]=1;
                for(int j=0;j<n;j++){
                    res[i]*=arr[i][j];
                }
            }
        return res;
    }
    public static int [][] revers(int [][] arr, int n){
        int [][] sps = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sps[j][i] = arr[i][j];
            }
        }
        return sps;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        System.out.println("Enter size:");
        int n = in.nextInt();
        int[][]arr1= new int[n][n];
        int[][]arr2= new int[n][n];
        System.out.println("Enter elements 1:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr1[i][j]=in.nextInt();
            }
        }
        System.out.println("Enter elements 2:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr2[i][j]=in.nextInt();
            }
        }
        int[] res;
        arr1=revers(arr1,n);
        res=proizvedenie(arr2,n);
        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++){
                arr1[i][j]+=res[i];
            }
        }
        arr1=revers(arr1,n);
        System.out.println("Result:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(arr1[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}