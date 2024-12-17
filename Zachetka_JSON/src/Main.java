import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Zachetka> zachetkas = readStudents("students.txt");
            readGrades(zachetkas, "Предмет1.txt");
            readGrades(zachetkas, "Предмет2.txt");
            writeOutstandingStudentsToJSON(zachetkas, "output.json");

            // Считываем данные из JSON и выводим в консоль для проверки
            readAndPrintFromJSON("output.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Zachetka> readStudents(String filename) throws IOException {
        List<Zachetka> zachetkas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) continue; // Пропуск комментариев
            String[] data = line.split(" ");
            String studentID = data[0];
            String lastName = data[1];
            String firstName = data[2];
            String middleName = data[3];
            String group = data[4];
            int course = Integer.parseInt(data[5]);
            zachetkas.add(new Zachetka(studentID, lastName, firstName, middleName, group, course));
        }
        br.close();
        return zachetkas;
    }

    public static void readGrades(List<Zachetka> zachetkas, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String subjectName = br.readLine();
        String[] arr = subjectName.split(" ");
        int sessionNumber = Integer.parseInt(br.readLine().split(" ")[1]); // Вторая строка - номер сессии
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) continue;
            String[] data = line.split(" ");
            String studentID = data[0];
            int grade = Integer.parseInt(data[1]);
            for (Zachetka zachetka : zachetkas) {
                if (zachetka.getStudentID().equals(studentID)) {
                    Zachetka.Session session = zachetka.getSession(sessionNumber);
                    if (session == null) {
                        zachetka.addSession(sessionNumber);
                        session = zachetka.getSession(sessionNumber);
                    }
                    boolean isExam = subjectName.toLowerCase().contains("экзамен");
                    session.addSubject(arr[0], grade, isExam);
                    break;
                }
            }
        }
        br.close();
    }

    public static void writeOutstandingStudentsToJSON(List<Zachetka> zachetkas, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter file = new FileWriter(filename)) {
            file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(zachetkas));
        }
    }

    public static void readAndPrintFromJSON(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Zachetka> zachetkas;

        // Чтение данных из JSON и десериализация в список объектов Zachetka
        String jsonContent = new String(Files.readAllBytes(Paths.get(filename)));
        zachetkas = mapper.readValue(jsonContent, new TypeReference<List<Zachetka>>() {});

        // Вывод структурированных данных в консоль
        for (Zachetka zachetka : zachetkas) {
            System.out.println(zachetka);
        }
    }
}
