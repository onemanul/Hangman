package backendacademy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FigureTest {
    @Test
    public void testShowGallowsValidAttempts() {
        Figure figure = new Figure();
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "          |\n" +
                "          |\n" +
                "          |\n" +
                "          |\n" +
                "===========", figure.showGallows(6));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "          |\n" +
                "          |\n" +
                "          |\n" +
                "===========", figure.showGallows(5));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "    |     |\n" +
                "          |\n" +
                "          |\n" +
                "===========", figure.showGallows(4));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "   /|     |\n" +
                "          |\n" +
                "          |\n" +
                "===========", figure.showGallows(3));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "   /|\\    |\n" +
                "          |\n" +
                "          |\n" +
                "===========", figure.showGallows(2));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "   /|\\    |\n" +
                "   /      |\n" +
                "          |\n" +
                "===========", figure.showGallows(1));
        assertEquals("\n" +
                "    +-----+\n" +
                "    |     |\n" +
                "    0     |\n" +
                "   /|\\    |\n" +
                "   / \\    |\n" +
                "          |\n" +
                "===========", figure.showGallows(0));
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
