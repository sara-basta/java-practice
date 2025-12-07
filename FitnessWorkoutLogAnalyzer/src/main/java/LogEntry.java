import java.time.LocalDate;

public record LogEntry(LocalDate date , String exerciseName,int sets, int reps,double weights) {
    @Override
    public String toString() {
        return String.format("%s %d sets %d reps %.0fkg", exerciseName, sets, reps, weights);
    }
}