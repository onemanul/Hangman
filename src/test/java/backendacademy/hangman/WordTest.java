package backendacademy.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest {

    @Test
    public void testConstructor() {
        Word word = new Word("test", "hint", 1);
        assertEquals("test", word.getWord());
        assertEquals("_".repeat(4), word.getHiddenWord());
        assertEquals("hint", word.getHint());
        assertEquals(6, word.getRemainingAttempts());
        assertEquals(1, word.getLevel());
    }

    @Test
    public void testCheckGuessCorrectLetter() {
        Word word = new Word("test", "hint", 1);
        word.checkGuess('t');
        assertEquals("t__t", word.getHiddenWord());
    }

    @Test
    public void testCheckGuessIncorrectLetter() {
        Word word = new Word("test", "hint", 1);
        word.checkGuess('a');
        assertEquals("_".repeat(4), word.getHiddenWord());
        assertEquals(5, word.getRemainingAttempts());
    }

    @Test
    public void testWordIsNotShown() {
        Word word = new Word("test", "hint", 1);
        assertTrue(word.wordIsNotShown());
        word.checkGuess('t');
        word.checkGuess('e');
        word.checkGuess('s');
        assertFalse(word.wordIsNotShown());
    }
}
