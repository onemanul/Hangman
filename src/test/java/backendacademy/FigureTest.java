package backendacademy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FigureTest {
    @Test
    public void testShowGallowsValidAttempts() {
        Figure figure = new Figure();
        assertEquals("""

    +-----+
    |     |
          |
          |
          |
          |
===========""", figure.showGallows(6));
        assertEquals("""

    +-----+
    |     |
    0     |
          |
          |
          |
===========""", figure.showGallows(5));
        assertEquals("""

    +-----+
    |     |
    0     |
    |     |
          |
          |
===========""", figure.showGallows(4));
        assertEquals("""

    +-----+
    |     |
    0     |
   /|     |
          |
          |
===========""", figure.showGallows(3));
        assertEquals("""

    +-----+
    |     |
    0     |
   /|\\    |
          |
          |
===========""", figure.showGallows(2));
        assertEquals("""

    +-----+
    |     |
    0     |
   /|\\    |
   /      |
          |
===========""", figure.showGallows(1));
        assertEquals("""

    +-----+
    |     |
    0     |
   /|\\    |
   / \\    |
          |
===========""", figure.showGallows(0));
    }

    @Test
    public void testShowGallowsInvalidAttempts() {
        Figure figure = new Figure();
        assertEquals("Ошибка в количестве оставшихся попыток", figure.showGallows(-1));
        assertEquals("Ошибка в количестве оставшихся попыток", figure.showGallows(7));
        assertEquals("Ошибка в количестве оставшихся попыток", figure.showGallows(-5));
        assertEquals("Ошибка в количестве оставшихся попыток", figure.showGallows(10));
    }
}
