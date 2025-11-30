import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeProcessor {
    static List<Employe> employees = new ArrayList<>();

    public void loadEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sarab\\Desktop\\list_of_employees.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dept = parts[2];
                double salary = Double.parseDouble(parts[3]);
                int age = Integer.parseInt(parts[4]);
                Employe employee = new Employe(id, name, dept, salary, age);
                employees.add(employee);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found! " + fnfe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Employe> filterByDepartment(String dept) {
        return employees.stream()
                .filter(e -> e.getDept().equals(dept))
                .collect(Collectors.toList());
    }

    public double calculateAverageSalary(){
        return employees.stream()
                .mapToDouble(Employe::getSalary).average()
                .orElse(0.0);
    }

    public Employe findHighestPaid(){
        return employees.stream()
                .max(Comparator.comparing(Employe::getSalary))
                .orElse(null);
    }

//    public double getTotalSalaryExpense() {
//        return employees.stream()
//                .mapToDouble(Employe::getSalary).sum();
//    }

    public double getTotalSalaryExpense() {
        return employees.stream()
                .mapToDouble(Employe::getSalary)
                .reduce(0, Double::sum);
    }

    public Map<String, Long> groupByDepartment() {
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employe::getDept,
                                Collectors.counting()
                        )
                );
    }

    public List<Employe> getHighEarnersYoung() {
        return employees.stream()
                .filter(e -> e.getSalary() > 50_000 && e.getAge() < 40)
                .toList();
    }

    public void writeToCSV(List<Employe> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("id,name,dept,salary,age");
            writer.newLine();

            for (Employe e : list) {
                writer.write(String.format(Locale.US,
                        "%d,%s,%s,%.2f,%d",
                        e.getId(),
                        e.getName(),
                        e.getDept(),
                        e.getSalary(),
                        e.getAge()));
                writer.newLine();
            }

            System.out.println("Export terminé → " + list.size() + " employés écrits dans " + filename);

        } catch (IOException ex) {
            System.out.println("Erreur lors de l'écriture du fichier : " + ex.getMessage());
        }
    }

    public void exportHighEarnersYoung() {
        writeToCSV(getHighEarnersYoung(), "C:\\Users\\sarab\\Desktop\\output.csv");
    }
}

