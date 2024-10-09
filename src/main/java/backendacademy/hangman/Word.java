package backendacademy.hangman;

import java.util.Set;
import java.util.stream.Collectors;

public class Word {

    private final String word;
    private String hiddenWord;
    private final String hint;
    private final Set<Character> letters;
    private int remainingAttempts;
    private final int level;
    private final int maxAttempts;

    public Word(String word, String hint, int level, int maxAttempts) {
        this.word = word;
        this.hiddenWord = "_".repeat(word.length());
        this.hint = hint;
        this.remainingAttempts = maxAttempts;
        this.letters = word.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        this.level = level;
        this.maxAttempts = maxAttempts;
    }

    public boolean wordIsNotShown() {
        return !hiddenWord.equals(word);
    }

    public String getWord() {
        return word;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getHint() {
        return hint;
    }

    public Set<Character> getLetters() {
        return letters;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public void setRemainingAttempts(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
