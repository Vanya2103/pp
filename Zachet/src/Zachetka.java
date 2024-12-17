import java.util.ArrayList;
import java.util.List;

public class Zachetka {
    private String studentID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String group;
    private int course;
    private List<Session> sessions;

    public Zachetka(String studentID, String lastName, String firstName, String middleName, String group, int course) {
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.group = group;
        this.course = course;
        this.sessions = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public void addSession(int sessionNumber) {
        sessions.add(new Session(sessionNumber));
    }

    public Session getSession(int sessionNumber) {
        for (Session session : sessions) {
            if (session.getSessionNumber() == sessionNumber) {
                return session;
            }
        }
        return null;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public boolean isAllGradesNineOrTen() {
        for (Session session : sessions) {
            for (Session.Subject subject : session.getSubjects()) {
                if (subject.getGrade() != 9 && subject.getGrade() != 10) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class Session {
        private int sessionNumber;
        private List<Subject> subjects;

        public Session(int sessionNumber) {
            this.sessionNumber = sessionNumber;
            this.subjects = new ArrayList<>();
        }

        public int getSessionNumber() {
            return sessionNumber;
        }

        public void addSubject(String name, int grade, boolean isExam) {
            subjects.add(new Subject(name, grade, isExam));
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public static class Subject {
            private String name;
            private int grade;
            private boolean isExam;

            public Subject(String name, int grade, boolean isExam) {
                this.name = name;
                this.grade = grade;
                this.isExam = isExam;
            }

            public String getName() {
                return name;
            }

            public int getGrade() {
                return grade;
            }

            public boolean isExam() {
                return isExam;
            }
        }
    }
}