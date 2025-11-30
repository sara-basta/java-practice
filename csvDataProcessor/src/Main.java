import java.util.List;
import java.util.Map;

public class Main {
    public static void main (String[] args){
        EmployeeProcessor processor = new EmployeeProcessor();
        processor.loadEmployees();
//        for (int i =0;i<employees.size();i++){
//            System.out.println("id : "+ employees.get(i).getId());
//            System.out.println("name : "+ employees.get(i).getName());
//            System.out.println("dept : "+ employees.get(i).getDept());
//            System.out.println("salary : "+ employees.get(i).getSalary());
//            System.out.println("age : "+ employees.get(i).getAge());
//        }
        List<Employe> hrEmployees = processor.filterByDepartment("HR");

//        for (Employe emp : hrEmployees) {
//            System.out.println(emp);
//        }

        hrEmployees.forEach(System.out::println);

        double average = processor.calculateAverageSalary();
        Employe highestPaid = processor.findHighestPaid();
        double totalSalary = processor.getTotalSalaryExpense();
        Map<String, Long> groupByDept = processor.groupByDepartment();

        System.out.printf("The average salary is : %.2f \n", average);
        System.out.printf("The total salary expense is : %.2f \n", totalSalary);
        System.out.println("The highest paid employee is : "+highestPaid);
        System.out.println("Number of employees per department : "+groupByDept);
        processor.exportHighEarnersYoung();

    }
}
