import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        File file = new File("input.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String[]> lines = new ArrayList<>();
        String line;
        while(bufferedReader.ready()){
            line = bufferedReader.readLine();
            String[] parts = line.trim().split(" ");
            lines.add(parts);
        }
        String [][] res = new String[lines.size()][3];
        res = lines.toArray(res);
        Map<String, List<String>> map = new HashMap<>();
        for(String [] rows:res){
            if(rows.length==3){
                String ost = rows[1]+" "+rows[0];
                if(!map.containsKey(rows[2])){
                    List<String> pos = new ArrayList<>();
                    pos.add(ost);
                    map.put(rows[2],pos);
                }else{
                    map.get(rows[2]).add(ost);
                }
            }
        }
        System.out.println("Enter a key:");
        String key = in.nextLine();
        List<String> result = map.get(key);
        if (result != null) {
            System.out.println(key + "':");
            for (String value : result) {
                System.out.println(value);
            }
        } else {
            System.out.println("Ключ '" + key + "' не найден.");
        }
    }
}