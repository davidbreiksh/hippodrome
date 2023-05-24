import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Mock
    private Hippodrome hippodrome;

    @Mock
    private Horse horse;

    public List<Horse> generateHorses(int frequency) {

        List<Horse> horses = new ArrayList<>();

        String horseName = "Horse";
        double horseSpeed = (Math.random() * 2.0) + 1.0;

        for (int i = 0; i < frequency; i++) {
            horses.add(new Horse(horseName + i, horseSpeed));
        }

        return horses;
    }

    @Test
    public void testHippodromeHorsesNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    public void testHippodromeHorsesEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Hippodrome hippodrome = new Hippodrome(List.of());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> horses = generateHorses(30);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMoveCallsOnAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for(int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        List<Horse> horses = generateHorses(50);

        hippodrome = new Hippodrome(horses);

        Horse winner = horses.stream()
                .max((h1, h2) -> Double.compare(h1.getDistance(), h2.getDistance()))
                .get();

        assertEquals(winner, hippodrome.getWinner());
    }
}