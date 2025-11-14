// File: Java/StudentManager.java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StudentManager {
    private static final String FILE = "students.csv";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1-Add 2-List 3-Find 4-Delete 5-Exit");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": addStudent(sc); break;
                case "2": listStudents(); break;
                case "3": findStudent(sc); break;
                case "4": deleteStudent(sc); break;
                case "5": System.exit(0);
                default: System.out.println("Invalid");
            }
        }
    }

    private static void addStudent(Scanner sc) throws IOException {
        System.out.print("ID: "); String id = sc.nextLine().trim();
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Email: "); String email = sc.nextLine().trim();
        String line = String.join(",", id, name.replace(",", " "), email);
        Files.write(Paths.get(FILE), (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Added.");
    }

    private static void listStudents() throws IOException {
        Path p = Paths.get(FILE);
        if (!Files.exists(p)) { System.out.println("No records."); return; }
        List<String> lines = Files.readAllLines(p);
        System.out.println("ID | Name | Email");
        for (String l : lines) System.out.println(l);
    }

    private static void findStudent(Scanner sc) throws IOException {
        System.out.print("Enter ID to find: ");
        String id = sc.nextLine().trim();
        List<String> lines = Files.exists(Paths.get(FILE)) ? Files.readAllLines(Paths.get(FILE)) : Collections.emptyList();
        for (String l : lines) {
            if (l.startsWith(id + ",")) { System.out.println("Found: " + l); return; }
        }
        System.out.println("Not found.");
    }

    private static void deleteStudent(Scanner sc) throws IOException {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine().trim();
        Path p = Paths.get(FILE);
        if (!Files.exists(p)) { System.out.println("No records."); return; }
        List<String> lines = new ArrayList<>(Files.readAllLines(p));
        boolean removed = lines.removeIf(l -> l.startsWith(id + ","));
        if (removed) {
            Files.write(p, lines);
            System.out.println("Deleted.");
        } else {
            System.out.println("ID not found.");
        }
    }
}
