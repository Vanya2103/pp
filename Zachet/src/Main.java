import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Zachetka> zachetkas = readStudents("students.txt");
            readGrades(zachetkas, "Предмет1.txt");
            readGrades(zachetkas, "Предмет2.txt");
            readGrades(zachetkas, "Предмет3.txt");
            writeOutstandingStudents(zachetkas, "output.txt");
            writeOnlyOutstandingStudents(zachetkas, "only_outstanding.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод чтения данных о студентах
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

    // Метод чтения ведомостей и добавления оценок студентам
    public static void readGrades(List<Zachetka> zachetkas, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String subjectName = br.readLine(); // Первой строкой идет название предмета
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
                    session.addSubject(subjectName, grade, isExam);
                    break;
                }
            }
        }
        br.close();
    }

    // Метод записи студентов в файл
    public static void writeOutstandingStudents(List<Zachetka> zachetkas, String filename) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filename));
        for (Zachetka zachetka : zachetkas) {
            pw.println(zachetka.getLastName() + " " + zachetka.getFirstName() + " " + zachetka.getMiddleName() +
                    " (Курс: " + zachetka.getCourse() + ", Группа: " + zachetka.getGroup() + ")");
            for (Zachetka.Session session : zachetka.getSessions()) {
                pw.print("Сессия " + session.getSessionNumber() + ":");
                for (Zachetka.Session.Subject subject : session.getSubjects()) {
                    String subjectName = subject.getName();
                    String[] arr = subjectName.split(" ");
                    String examType = subject.isExam() ? "Экзамен" : "Зачет";
                    pw.println(" " + arr[0] + " - " + examType + " - Оценка: " + subject.getGrade());
                }
            }
            pw.println();
        }
        pw.close();
    }

    public static void writeOnlyOutstandingStudents(List<Zachetka> zachetkas, String filename) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filename));
        for (Zachetka zachetka : zachetkas) {
            if (zachetka.isAllGradesNineOrTen()) {
                pw.println(zachetka.getLastName() + " " + zachetka.getFirstName() + " " + zachetka.getMiddleName() +
                        " (Курс: " + zachetka.getCourse() + ", Группа: " + zachetka.getGroup() + ")");
                for (Zachetka.Session session : zachetka.getSessions()) {
                    pw.print("Сессия " + session.getSessionNumber() + ":");
                    for (Zachetka.Session.Subject subject : session.getSubjects()) {
                        String subjectName = subject.getName();
                        String[] arr = subjectName.split(" ");
                        String examType = subject.isExam() ? "Экзамен" : "Зачет";
                        pw.println(" " + arr[0] + " - " + examType + " - Оценка: " + subject.getGrade());
                    }
                }
            }
        }
        pw.close();
    }
}