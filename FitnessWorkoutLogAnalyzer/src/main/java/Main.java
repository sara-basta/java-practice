import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Workout> workouts = LogParser.workouts("data.csv");
        System.out.println("Parsed Workouts:");
        workouts.forEach(System.out::println);

        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String, Double> volumes = analyzer.getVolumeByExercise();
        Workout topWorkout = analyzer.getTopVolumeWorkout();
        Map<String, LogEntry> exerciseWithMaxWeight =  analyzer.getPersonalRecordByExercise();
        Map<String, Integer> exerciseFrequency = analyzer.getFrequencyByExercise();
        System.out.println("Volumes : ");
        System.out.println(volumes);
        System.out.println("Workout with the highest volume : ");
        System.out.println(topWorkout);
        System.out.println("Exercises with their heaviest weight : ");
        System.out.println(exerciseWithMaxWeight);
        System.out.println("Number of days each exercise was done : ");
        System.out.println(exerciseFrequency);

    }
}