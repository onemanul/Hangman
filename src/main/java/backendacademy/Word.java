package backendacademy;

import java.util.Set;
import java.util.stream.Collectors;

public class Word {

    private final String word;
    private String hiddenWord;
    private final String hint;
    private final Set<Character> letters;
    private int remainingAttempts;
    private final int level;
    int maxAttempts = 6;

    public Word(String word, String hint, int level) {
        this.word = word;
        this.hiddenWord = "_".repeat(word.length());
        this.hint = hint;
        this.remainingAttempts = maxAttempts;
        this.letters = word.chars().mapToObj(chr -> (char) chr).collect(Collectors.toSet());
        this.level = level;
    }

    public void checkGuess(char ch) {
        if (letters.contains(ch)) {
            for (int i = 0; i < word.length(); ++i) {
                if (word.charAt(i) == ch) {
                    hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i + 1);
                }
            }
        } else {
            remainingAttempts--;
        }
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

    public String getHint() {
        return hint;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public int getLevel() {
        return level;
    }
}
