package backendacademy.hangman;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DictionaryTest {

    @Test
    public void testGetWordValidCategoryAndLevel() {
        Dictionary dictionary = new Dictionary();
        Optional<Word> optWord  = dictionary.getWord(1, 1);
        assertTrue(optWord.isPresent());
        assertEquals(1, optWord.get().getLevel());
    }

    @Test
    public void testGetWordInvalidCategory() {
        Dictionary dictionary = new Dictionary();
        Optional<Word> optWord = dictionary.getWord(-2, 1);
        assertTrue(optWord.isEmpty());
    }

    @Test
    public void testGetWordInvalidLevel() {
        Dictionary dictionary = new Dictionary();
        Optional<Word> optWord = dictionary.getWord(1, 4);
        assertTrue(optWord.isEmpty());
    }

    @Test
    public void testGetWordInvalidCategoryAndLevel() {
        Dictionary dictionary = new Dictionary();
        Optional<Word> optWord = dictionary.getWord(-3, -3);
        assertTrue(optWord.isEmpty());
    }

    @Test
    public void testNumberOfCategories() {
        Dictionary dictionary = new Dictionary();
        assertEquals(3, dictionary.numberOfCategories());
    }

    @Test
    public void testNumberOfCategoryNotExists() {
        Dictionary dictionary = new Dictionary();
        assertTrue(dictionary.numberOfCategoryNotExists(0));
        assertTrue(dictionary.numberOfCategoryNotExists(-5));
        assertTrue(dictionary.numberOfCategoryNotExists(5));
        assertFalse(dictionary.numberOfCategoryNotExists(1));
        assertFalse(dictionary.numberOfCategoryNotExists(2));
        assertFalse(dictionary.numberOfCategoryNotExists(3));
    }

    @Test
    public void testLevelNotExists() {
        Dictionary dictionary = new Dictionary();
        assertTrue(dictionary.levelNotExists(0));
        assertTrue(dictionary.levelNotExists(-5));
        assertTrue(dictionary.levelNotExists(5));
        assertFalse(dictionary.levelNotExists(1));
        assertFalse(dictionary.levelNotExists(2));
        assertFalse(dictionary.levelNotExists(3));
    }

    @Test
    public void testGetKeys() {
        Dictionary dictionary = new Dictionary();
        String[] keys = dictionary.getKeys();
        assertEquals(3, keys.length);
        assertEquals("чувства", keys[0]);
        assertEquals("программирование", keys[1]);
        assertEquals("смерть", keys[2]);
    }

    @Test
    public void testGetKey() {
        Dictionary dictionary = new Dictionary();
        assertEquals("чувства", dictionary.getKey(0));
        assertEquals("программирование", dictionary.getKey(1));
        assertEquals("смерть", dictionary.getKey(2));
        assertEquals("Категории с таким номером не существует", dictionary.getKey(5));
        assertEquals("Категории с таким номером не существует", dictionary.getKey(-5));
    }

    @Test
    public void testGetWordEdgeCases() {
        Dictionary dictionary = new Dictionary();
        Optional<Word> optWord1 = dictionary.getWord(3, 3);
        assertTrue(optWord1.isPresent());
        assertEquals(3, optWord1.get().getLevel());
        Optional<Word> optWord2 = dictionary.getWord(1, 2);
        assertTrue(optWord2.isPresent());
        assertEquals(2, optWord2.get().getLevel());
        assertNotEquals(optWord1.get(), optWord2.get());
    }
}
