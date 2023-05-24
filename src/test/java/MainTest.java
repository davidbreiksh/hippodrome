import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    public void testMainMethodWorksNotMoreThan22Seconds() throws Exception {
        long startTime = System.currentTimeMillis();
        Main.main(new String[]{});
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;
        assertTrue(duration <= 22);
    }
}