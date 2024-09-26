package backendacademy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    public void testGetWordValidCategoryAndLevel() {
        Dictionary dictionary = new Dictionary();
        Word word = dictionary.getWord(1, 1);
        assertNotNull(word);
        assertEquals(1, word.getLevel());
    }

    @Test
    public void testGetWordInvalidCategory() {
        Dictionary dictionary = new Dictionary();
        Word word = dictionary.getWord(-2, 1);
        assertNotNull(word);
    }

    @Test
    public void testGetWordInvalidLevel() {
        Dictionary dictionary = new Dictionary();
        Word word = dictionary.getWord(1, 4);
        assertNotNull(word);
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
    public void testGetWordEdgeCases() {
        Dictionary dictionary = new Dictionary();
        Word word1 = dictionary.getWord(3, 3);
        assertNotNull(word1);
        assertEquals(3, word1.getLevel());
        Word word2 = dictionary.getWord(1, 2);
        assertNotNull(word2);
        assertEquals(2, word2.getLevel());
        assertNotEquals(word1, word2);
    }
}
