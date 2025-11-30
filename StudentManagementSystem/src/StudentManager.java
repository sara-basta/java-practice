import java.util.*;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();
    HashSet<Integer> studentIds = new HashSet<>();
    HashMap<String, List<Student>> studentsPerMajor = new HashMap<>();


    public void addStudent(Student s) {
        if(studentIds.contains(s.getId())) {
            System.out.println("This id already exists");
            return;
        }
        students.add(s);
        studentIds.add(s.getId());
        if (!studentsPerMajor.containsKey(s.getMajor())) {
            studentsPerMajor.put(s.getMajor(), new ArrayList<>());
        }

        studentsPerMajor.get(s.getMajor()).add(s);
    }

    public boolean removeStudent(int id){

        if(!studentIds.contains(id)){
            System.out.println("This id doesn't exist");
            return false;
        }


        Student toRemove = null;
        for (Student s : students){
            if(s.getId()==id) {
                toRemove = s;
                break;
            }
        }

        studentIds.remove(id);
        students.remove(toRemove);
        studentsPerMajor.get(toRemove.getMajor()).remove(toRemove);

        return true;
    }


//    public Student findByName(String name){
//        for (Student s : students) {
//            if(s.getName().equals(name)) {
//                return s;
//            }
//        }
//        return null;
//    }

    public Optional<Student> findByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }


    public List<Student> getStudentsByMajor(String major) {
        return studentsPerMajor.getOrDefault(major, new ArrayList<>());
    }


    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("Total: " + students.size() + " students");
    }

    public void sortByGrade() {
        Collections.sort(students);
    }


    public void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
    }



}
