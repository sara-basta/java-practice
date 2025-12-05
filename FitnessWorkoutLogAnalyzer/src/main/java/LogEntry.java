import java.time.LocalDate;

public record LogEntry(LocalDate date , String exerciseName,int sets, int reps,double weights) {
}