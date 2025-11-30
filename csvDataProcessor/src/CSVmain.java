import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class CSVmain {
    public static void main (String[] args){
        String[] names = {"Sara","Amina","Vanessa","Alex","Hiba","Mariam","Aicha","Mohammed","Yassine","Omar"};
        String[] depts = {"Engineering", "HR", "Sales", "Marketing", "Finance"};



        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\sarab\\Desktop\\list_of_employees.csv"))){
            writer.write("id,name,dept,salary,age");
            writer.newLine();
            Random rand = new Random();
            for (int i=1;i<=1000;i++){
                String name = names[rand.nextInt(names.length)];
                String dept = depts[rand.nextInt(depts.length)];
                Double salary = rand.nextDouble(8000,80000);
                int age = rand.nextInt(20,60);
                writer.write(i+","+name+","+dept+","+String.format(Locale.US, "%.2f", salary)+","+age);
                writer.newLine();

            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found! "+ fnfe);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
