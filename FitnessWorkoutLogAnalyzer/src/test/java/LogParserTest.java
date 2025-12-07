import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LogParserTest {
    @Test
    void testGoodData() {
        List<Workout> workouts = LogParser.workouts("data.csv");
        assertFalse(workouts.isEmpty());

    }

    @Test
    void testAnalyzerVolumesNotNull(){
        LogAnalyzer analyzer = new LogAnalyzer();
        Workout top = analyzer.getTopVolumeWorkout();
        assertNotNull(top,"Couldn't find top volume workout!");
    }
}
