package backendacademy;

import java.util.Set;
import java.util.stream.Collectors;

public class Word {

    private String word;
    private String hiddenWord;
    private String hint;
    private Set<Character> letters;
    private int remainingAttempts = 6;
    private int level;

    public Word(String word, String hint, int level) {
        this.word = word;
        this.hiddenWord = "_".repeat(word.length());
        this.hint = hint;
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
