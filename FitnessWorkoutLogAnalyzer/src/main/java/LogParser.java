import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogParser {

    public static List<Workout> readCSV(String filename){
        List<Workout> workouts = List.of();
        try{

            // loading the data
            List<LogEntry> listEntries = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(LogParser::getLogEntry)
                    .toList();

            // grouping entries by date
            Map<LocalDate, List<LogEntry>> groupedPerDate = listEntries.stream()
                    .collect(Collectors.groupingBy(LogEntry::date));

            // retrieving it to a set to be able to Iterate in it/ stream it
            Set<Map.Entry<LocalDate, List<LogEntry>>> entries = groupedPerDate.entrySet();

            // adds them to the workout list after comparing keys (dates)
            workouts = entries.stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(e -> new Workout(e.getKey(), e.getValue()))
                    .toList();
        }
        catch (Exception e) {
            System.err.println("CSV parse error: " + e.getMessage());
            return List.of();
        }
        return workouts;
    }

    static LogEntry getLogEntry(String line) {
        String[] fields = line.split(",");
        if(fields.length!=5){
            throw new RuntimeException("Invalid CSV line: " + line);
        }
        LocalDate date = LocalDate.parse(fields[0]);
        String ExerciseName = fields[1];
        int sets = Integer.parseInt(fields[2]);
        int reps = Integer.parseInt(fields[3]);
        double weights = Double.parseDouble(fields[4]);
        return new LogEntry(date, ExerciseName, sets, reps, weights);
    }

    public static void main(String[] args) {
        List<Workout> workouts = readCSV("data.csv");
        System.out.println("Parsed Workouts:");
        workouts.forEach(System.out::println);
    }


}
