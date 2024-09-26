package backendacademy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testCheckForInt() {
        assertEquals(1, Main.checkForInt("1"));
        assertEquals(12, Main.checkForInt("12"));
        assertEquals(0, Main.checkForInt("abc"));
        assertEquals(0, Main.checkForInt("123abc"));
    }
}
