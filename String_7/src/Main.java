import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter string 1:");
        String s1 = in.nextLine();
        System.out.println("Enter string 2:");
        String s2 = in.nextLine();
        String s3 = s1 +" "+ s2;
        s3=s3.replaceAll("[^a-zA-Z0-9а-яёА-ЯЁ]"," ");
        s3=s3.replaceAll("\\s+"," ");
        String [] arr= s3.split(" ");
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
        String res = String.join(" ",arr);
        System.out.println(res);
    }
}