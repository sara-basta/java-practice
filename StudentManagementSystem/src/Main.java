
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static StudentManager manager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    sortStudents();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    displayByMajor();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Sort Students");
        System.out.println("5. Display All Students");
        System.out.println("6. Display by Major");
        System.out.println("0. Quit");
        System.out.println("==========================");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");

        int id = getIntInput("ID: ");
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        int age = getIntInput("Age: ");
        double grade = getDoubleInput("Grade: ");
        scanner.nextLine();

        System.out.print("Major: ");
        String major = scanner.nextLine();

        Student student = new Student(id, name, age, grade, major);
        manager.addStudent(student);

        System.out.println("Student added successfully");
    }


    private static void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        int id = getIntInput("Enter student ID to remove: ");

        if (manager.removeStudent(id)) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }


    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Optional<Student> result = manager.findByName(name);

        if (result.isPresent()) {
            System.out.println("\nStudent found:");
            System.out.println(result.get());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void sortStudents() {
        System.out.println("\n--- Sort Students ---");
        System.out.println("1. Sort by Grade");
        System.out.println("2. Sort by Name");

        int choice = getIntInput("Choose sorting method: ");

        switch (choice) {
            case 1:
                manager.sortByGrade();
                System.out.println("Students sorted by grade (descending)");
                break;
            case 2:
                manager.sortByName();
                System.out.println("Students sorted by name (alphabetical)");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        manager.displayAll();
    }

    private static void displayByMajor() {
        System.out.println("\n--- Display by Major ---");
        scanner.nextLine();
        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        List<Student> students = manager.getStudentsByMajor(major);

        if (students.isEmpty()) {
            System.out.println("No students found in major: " + major);
        } else {
            System.out.println("\nStudents in " + major + ":");
            for (Student s : students) {
                System.out.println(s);
            }
            System.out.println("Total: " + students.size() + " students");
        }
    }
    // Helper: Get int input with error handling

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Helper: Get double input with error handling
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();             }
        }
    }
}
