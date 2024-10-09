package backendacademy.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class MainTest {
    @Test
    public void testCheckForInt() {
        assertEquals(1, Main.checkForInt("1").get());
        assertEquals(12, Main.checkForInt("12").get());
        assertTrue(Main.checkForInt("abc").isEmpty());
        assertTrue(Main.checkForInt("123abc").isEmpty());
    }

    @Test
    public void testCheckForChar() {
        assertTrue(Main.checkForChar("а"));
        assertTrue(Main.checkForChar("А"));
        assertFalse(Main.checkForChar("1"));
        assertFalse(Main.checkForChar("аа"));
        assertFalse(Main.checkForChar("!"));
    }

    @Test
    public void testChooseRandomInt() {
        assertTrue(Main.chooseRandomInt(1,3) > 0);
        assertTrue(Main.chooseRandomInt(1,3) < 4);
        assertEquals(1, Main.chooseRandomInt(1, 1));
        assertFalse(Main.chooseRandomInt(1,5) < 1);
        assertFalse(Main.chooseRandomInt(1,5) > 5);
    }

    @Test
    public void testChooseWordByOptions_1() {
        Scanner scanner = new Scanner("1\n2");
        Main.in = scanner;
        Dictionary dict = new Dictionary();
        Optional<Word> optWord = Main.chooseWordByOptions(dict);
        assertTrue(optWord.isPresent());
    }

    @Test
    public void testChooseWordByOptions_2() {
        Scanner scanner = new Scanner("1\nп\n9\nыы\n0");
        Main.in = scanner;
        Dictionary dict = new Dictionary();
        Optional<Word> optWord = Main.chooseWordByOptions(dict);
        assertTrue(optWord.isPresent());
    }

    @Test
    public void testChooseWordByOptions_3() {
        Scanner scanner = new Scanner("я\nп\n9\n0\n3");
        Main.in = scanner;
        Dictionary dict = new Dictionary();
        Optional<Word> optWord = Main.chooseWordByOptions(dict);
        assertTrue(optWord.isPresent());
    }

    @Test
    public void testStartGame() {
        Word word1 = new Word("", "hint", 1, 6);
        assertFalse(Main.startGame(word1));
        Scanner scanner = new Scanner("с\nЛ\nо\nВ");
        Main.in = scanner;
        Word word2 = new Word("слово", "hint", 1, 6);
        assertTrue(Main.startGame(word2));
    }

    @Test
    public void testPlayGame() {
        Scanner scanner = new Scanner("я\nп\n" + "у\nя\nп\nм\n" + "у\nя\nп\nм\nз\nж\n" + "с\nЛ\nо\nВ");
        Main.in = scanner;
        for(int i = 2; i <= 6; i += 2) {
            Word word = new Word("слово", "hint", 1, i);
            assertFalse(Main.playGame(word));
        }
        Word word = new Word("слово", "hint", 1, 6);
        assertTrue(Main.playGame(word));
    }

    @Test
    public void testNextGuessWrongInput() {
        Word word = new Word("слово", "hint", 1, 6);
        Set<Character> usedLetters = new HashSet<>();
        assertFalse(Main.nextGuess("абв", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());

        assertFalse(Main.nextGuess("1", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());
    }

    @Test
    public void testNextGuessCorrect() {
        Word word = new Word("слово", "hint", 1, 6);
        Set<Character> usedLetters = new HashSet<>();
        assertTrue(Main.nextGuess("в", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("___в_", word.getHiddenWord());

        assertTrue(Main.nextGuess("о", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("__ово", word.getHiddenWord());

        assertFalse(Main.nextGuess("в", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("__ово", word.getHiddenWord());

        assertTrue(Main.nextGuess("с", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("с_ово", word.getHiddenWord());

        assertTrue(Main.nextGuess("л", word, usedLetters));
        assertEquals(6, word.getRemainingAttempts());
        assertEquals("слово", word.getHiddenWord());
    }

    @Test
    public void testNextGuessIncorrect() {
        Word word = new Word("слово", "hint", 1, 3);
        Set<Character> usedLetters = new HashSet<>();
        assertTrue(Main.nextGuess("а", word, usedLetters));
        assertEquals(2, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());

        assertTrue(Main.nextGuess("д", word, usedLetters));
        assertEquals(1, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());

        assertFalse(Main.nextGuess("а", word, usedLetters));
        assertEquals(1, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());

        assertTrue(Main.nextGuess("з", word, usedLetters));
        assertEquals(0, word.getRemainingAttempts());
        assertEquals("_____", word.getHiddenWord());
    }

    @Test
    public void testCheckGuessCorrectLetter() {
        Word word = new Word("test", "hint", 1, 6);
        Main.checkGuess(word, 't');
        assertEquals("t__t", word.getHiddenWord());
    }

    @Test
    public void testCheckGuessCorrectLetterUpperCase() {
        Word word = new Word("test", "hint", 1, 6);
        Main.checkGuess(word, 'T');
        assertEquals("t__t", word.getHiddenWord());
    }

    @Test
    public void testCheckGuessIncorrectLetter() {
        Word word = new Word("test", "hint", 1, 6);
        Main.checkGuess(word, 'a');
        assertEquals("_".repeat(4), word.getHiddenWord());
        assertEquals(5, word.getRemainingAttempts());
    }

    @Test
    public void testCheckGuessIncorrectLetterUpperCase() {
        Word word = new Word("test", "hint", 1, 6);
        Main.checkGuess(word, 'A');
        assertEquals("_".repeat(4), word.getHiddenWord());
        assertEquals(5, word.getRemainingAttempts());
    }
}
