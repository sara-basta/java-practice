import java.time.LocalDate;
import java.util.List;

public record Workout(LocalDate date, List<LogEntry> entries) {
}
