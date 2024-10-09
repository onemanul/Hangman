package backendacademy.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {

    @Test
    public void testConstructor() {
        Word word = new Word("test", "hint", 1, 6);
        assertEquals("test", word.getWord());
        assertEquals("_".repeat(4), word.getHiddenWord());
        assertEquals("hint", word.getHint());
        assertEquals(6, word.getRemainingAttempts());
        assertEquals(1, word.getLevel());
    }

    @Test
    public void testWordIsNotShown() {
        Word word = new Word("test", "hint", 1, 6);
        assertTrue(word.wordIsNotShown());
        Main.checkGuess(word, 'T');
        Main.checkGuess(word, 'e');
        Main.checkGuess(word, 's');
        assertFalse(word.wordIsNotShown());
    }
}
