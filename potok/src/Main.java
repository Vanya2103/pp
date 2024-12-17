import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        String[] firstLine = lines.get(0).split(" ");
        int size = Integer.parseInt(firstLine[0]);
        String[] secondLine = lines.get(1).split(" ");
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(secondLine[i]);
        }

        System.out.println("Исходный массив: " + Arrays.toString(array));

        System.out.print("Введите порядок сортировки (asc для возрастания, desc для убывания): ");
        String order = scanner.next().trim();
        boolean ascending = "asc".equalsIgnoreCase(order);
        boolean descending = "desc".equalsIgnoreCase(order);

        if (!ascending && !descending) {
            System.out.println("Некорректный ввод! Порядок сортировки должен быть 'asc' или 'desc'.");
            return;
        }

        Thread sortThread = new Thread(() -> {
            if (ascending) {
                Arrays.sort(array);
            } else {
                Arrays.sort(array, (a, b) -> Integer.compare(b, a));
            }
        });

        sortThread.start();

        try {
            sortThread.join();
        } catch (InterruptedException e) {
            System.out.println("Сортировка прервана!");
            return;
        }

        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }
}