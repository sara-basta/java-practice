import java.time.LocalDate;
import java.util.List;

public record Workout(LocalDate date, List<LogEntry> entries) {
    @Override
    public String toString() {
        return String.format("Workout[ %s | %d exercises | Total Volume: %.1fkg]",
                date, entries.size(),
                entries.stream().mapToDouble(e -> e.sets() * e.reps() * e.weights()).sum());
    }
}
