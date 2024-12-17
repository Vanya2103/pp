import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность:");
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        System.out.println("Введите элементы:");
        for(int i=0;i<n;i++){
            ArrayList<Integer> rows = new ArrayList<>();
            for(int j=0;j<n;j++){
                rows.add(in.nextInt());//(int)(Math.random()*100);
                }
            arr.add(rows);
        }
        System.out.println("Матрица:");
        for(ArrayList<Integer>rows:arr){
            System.out.println(rows);
        }
        int max = arr.get(0).get(0);
        int k=0;
        int l=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(Math.abs(arr.get(i).get(j))>Math.abs(max)){
                    max = arr.get(i).get(j);
                    k=i+1;
                    l=j+1;
                }
            }
        }
        arr.remove(k-1);
        for(ArrayList<Integer>rows:arr){
            rows.remove(l-1);
        }
        System.out.println("Максимальный по модулю:"+max);
        System.out.println("Строка:"+k);
        System.out.println("Столбец:"+l);
        System.out.println("Новая матрица:");
        for(ArrayList<Integer>rows:arr){
            System.out.println(rows);
        }
    }
}
