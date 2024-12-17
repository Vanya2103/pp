import java.util.Vector;
import java.util.Scanner;
public class Main {
    public static boolean CHEAK(Vector<Integer> rows){
        for(int num:rows){
            if(num<-10||num>-1)
                  return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность:");
        int n = in.nextInt();
        int m = in.nextInt();
        Vector<Vector<Integer>> arr = new Vector<>();
        System.out.println("Введите элементы:");
        for(int i=0;i<n;i++){
            Vector<Integer> rows = new Vector<>();
            for(int j=0;j<m;j++){
                rows.add(in.nextInt());//(int)(Math.random()*100);
            }
            arr.add(rows);
        }
        System.out.println("Матрица:");
        for(Vector<Integer>rows:arr){
            System.out.println(rows);
        }
        int res=0;
        for(Vector<Integer> rows:arr){
            if(CHEAK(rows))
                res++;
        }
        System.out.println("Количество строк перестановок:"+res);
    }
}