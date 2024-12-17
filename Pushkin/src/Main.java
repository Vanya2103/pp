import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;
public class Main{
    public static void main(String[] args)throws Exception{
        Set<String> treeSet = new TreeSet<>();
        File file = new File("input.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int res =0;
        while(bufferedReader.ready()){
            String line = bufferedReader.readLine();
            line = line.toLowerCase();
            line = line.replaceAll("[^a-zA-Z0-9а-яёА-ЯЁ]"," ");
            line = line.replaceAll("\\s+"," ");
            String [] arr = line.split(" ");
            for(int i =0;i< arr.length;i++){
                treeSet.add(arr[i]);
                res++;
            }
        }
        System.out.println(treeSet);
        System.out.println("Size:"+res);
    }
}