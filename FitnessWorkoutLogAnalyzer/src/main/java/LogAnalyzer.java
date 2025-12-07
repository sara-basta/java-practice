import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogAnalyzer {
    List<LogEntry> entries = LogParser.getAllEntries("data.csv");
    List<Workout> workouts = LogParser.workouts("data.csv");

    public Map<String, Double> getVolumeByExercise(){
        return entries.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::exerciseName,
                        Collectors.summingDouble(
                                e-> e.sets() * e.reps() * e.weights()
                        )
                ));
    }

    public Workout getTopVolumeWorkout(){
        return entries.stream()
                // group by dates with volume
                .collect(Collectors.groupingBy(
                        LogEntry::date,
                        Collectors.summingDouble(e-> e.sets() * e.reps() * e.weights())
                ))
                // converts map to stream
                .entrySet().stream()
                //gets the max
                .max(Map.Entry.comparingByValue())
                // returns first workout that equals the date of the max value found in entries by date or null(optional)
                .map(
                        e -> workouts.stream()
                                .filter(w -> w.date().equals(e.getKey()))
                                .findFirst()
                                .orElse(null)
                )
                .orElse(null);

    }

    public Map<String, LogEntry> getPersonalRecordByExercise(){
        return entries.stream()
                .collect(Collectors.toMap(
                        LogEntry::exerciseName,
                        e -> e ,(e1,e2) -> e1.weights() > e2.weights() ? e1 : e2)
                );

    }

    public Map<String, Integer> getFrequencyByExercise(){
        return entries.stream()
                .collect(Collectors.groupingBy(
                        // get the exercise name
                        LogEntry::exerciseName,
                        Collectors.collectingAndThen(
                                // then extracting the dates and using set to get unique dates only
                                Collectors.mapping(LogEntry::date, Collectors.toSet()),
                                        // counts unique sets
                                        Set::size
                        )
                ));

    }
}