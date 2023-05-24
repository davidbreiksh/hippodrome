import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    private final String blankStringRegex = "^[\\s]*$";

    @Mock
    private Horse horse;

    @Test
    public void testHorseNameNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse(null, 2.4, 0.0);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    public void testBlankHorseName() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            String name = "";
            if (name.matches(blankStringRegex)) {
                throw new IllegalArgumentException("Name cannot be blank.");
            }
            Horse horse = new Horse(name, 2.4, 0.0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testNegativeHorseSpeed() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Random random = new Random();
            double speed = random.nextDouble() * -Double.MAX_VALUE;

            if (speed < 0.0) {
                throw new IllegalArgumentException("Speed cannot be negative.");
            }
            Horse horse = new Horse("Horse", speed, 0.0);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testNegativeHorseDistance() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Random random = new Random();
            double distance = random.nextDouble() * -Double.MAX_VALUE;

            if (distance < 0.0) {
                throw new IllegalArgumentException("Distance cannot be negative.");
            }
            Horse horse = new Horse("Horse", 2.4, distance);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testHorseName() {
        Horse horse = new Horse("Horse", 2.4, 0.0);
        assertEquals("Horse", horse.getName());
    }

    @Test
    public void testHorseSpeed() {
        Horse horse = new Horse("Horse", 2.4, 0.0);
        assertEquals(2.4, horse.getSpeed());
    }

    @Test
    public void testHorseDistance() {
        Horse horse = new Horse("Horse", 2.4, 1.5);
        assertEquals(1.5, horse.getDistance());
    }

    @Test
    public void testHorseDistanceWithTwoParameters() {
        Horse horse = new Horse("Horse", 2.4);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    public void testMoveMethodCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Horse", 2.4, 0.0);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void testMoveMethod() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Horse", 2.4, 0.0);
            horse.move();

            assertEquals(1.2, horse.getDistance());
        }
    }
}