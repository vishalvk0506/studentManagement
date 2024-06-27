package studentManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_PATH = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    public void deleteStudent(int studentId) {
        students.removeIf(student -> student.getId() == studentId);
        saveStudentsToFile();
    }

    public void updateStudent(int studentId, String name) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                students.set(students.indexOf(student), new Student(studentId, name));
                saveStudentsToFile();
                break;
            }
        }
    }

    public Student getStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    sms.addStudent(new Student(id, name));
                    break;
                case 2:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    sms.deleteStudent(deleteId);
                    break;
                case 3:
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new student name: ");
                    String updateName = scanner.next();
                    sms.updateStudent(updateId, updateName);
                    break;
                case 4:
                    sms.displayStudents();
                    break;
                case 5:
                    System.out.print("Enter student ID to calculate GPA: ");
                    int gpaId = scanner.nextInt();
                    Student student = sms.getStudent(gpaId);
                    if (student != null) {
                        System.out.println("GPA: " + student.calculateGPA());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}
