import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogParserTest {
    @Test
    void testGoodData() {
        List<Workout> workouts = LogParser.readCSV("data.csv");
        assertFalse(workouts.isEmpty());

    }
}
