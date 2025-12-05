import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class CsvWriter {

    public static void generateData() throws IOException {
        String header = "Date,ExerciseName,sets,reps,weights";
        String[] exercises = {"Squat", "Bench Press", "Deadlift", "Overhead Press", "Pull-up", "Row", "Lunge"};

        Random rand = new Random();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv"))){
            writer.write(header);
            writer.newLine();
            for (int i=1; i<50; i++){
                LocalDate currentDate = LocalDate.now();
                String exerciseName = exercises[rand.nextInt(exercises.length)];
                int sets = 3 + rand.nextInt(3); // 3 to 5 sets
                int reps = 8 + rand.nextInt(13); // 8 to 20 reps
                double weights = rand.nextDouble(10,150); //10 to 150

                LogEntry entry = new LogEntry(currentDate, exerciseName, sets, reps, weights);

                String csvLine = String.format(Locale.US,"%s,%s,%d,%d,%.1f",entry.date(),entry.exerciseName(),
                        entry.sets(),entry.reps(),entry.weights());


                writer.write(csvLine);
                writer.newLine();
            }

        }catch(FileNotFoundException fnfe) {
            throw new IOException("File not found: "+fnfe);
        }

    }

    public static void main(String[] args) {
        try {
            generateData();
        }catch (IOException e) {
            System.err.println("Error running data generator: " + e.getMessage());
        }
    }
}
