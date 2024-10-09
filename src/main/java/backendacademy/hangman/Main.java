package backendacademy.hangman;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LOGGER.info("Добро пожаловать в игру Виселица!\n"
                        + "Для старта введите 1. Для выхода - что угодно, кроме единицы.");
        Dictionary dict = new Dictionary();
        while (in.nextLine().equals("1")) {
            Optional<Word> optWord = chooseWordByOptions(dict);
            if (optWord.isEmpty() || !startGame(optWord.get())) {
                LOGGER.warning("Ошибка в получении слова или в полученном слове, старт невозможен");
            }
            LOGGER.info("Для новой игры введите 1. Для выхода - что угодно, кроме единицы.");
        }
        LOGGER.info("Пришло время выйти из игры.");
    }

    public static Optional<Word> chooseWordByOptions(Dictionary dict) {
        String[] numbers = dict.getKeys().clone();
        Arrays.setAll(numbers, i -> numbers[i] + " - " + (i + 1));
        LOGGER.info("Доступные категории: " + Arrays.toString(numbers)
                    + "\nВведите порядковый номер выбранной категории или 0 для случайного выбора");
        Optional<Integer> numberOfCategory = checkForInt(in.nextLine());
        while (numberOfCategory.isEmpty()
                || (numberOfCategory.get() != 0 && dict.numberOfCategoryNotExists(numberOfCategory.get()))) {
            LOGGER.info("Введите порядковый номер выбранной категории (их " + dict.numberOfCategories()
                        + ") или 0 для случайного выбора");
            numberOfCategory = checkForInt(in.nextLine());
        }
        if (numberOfCategory.get() == 0) {
            LOGGER.info("Будет выбрана случайная категория");
            numberOfCategory = Optional.of(chooseRandomInt(1, dict.numberOfCategories()));
        } else {
            LOGGER.info("Выбрана категория: " + dict.getKey(numberOfCategory.get() - 1));
        }
        LOGGER.info("Выберите уровень сложности (1,2,3) или 0 для случайного выбора");
        Optional<Integer> level = checkForInt(in.nextLine());
        while (level.isEmpty() || (level.get() != 0 && dict.levelNotExists(level.get()))) {
            LOGGER.info("Введите уровень сложности (их " + dict.getMaxLevel()
                + ") или 0 для случайного выбора уровня");
            level = checkForInt(in.nextLine());
        }
        if (level.get() == 0) {
            level = Optional.of(chooseRandomInt(1, dict.getMaxLevel()));
        }
        return dict.getWord(numberOfCategory.get(), level.get());
    }

    public static void showStartGame(Word word, Figure dead) {
        LOGGER.info(dead.showGallows(word.getRemainingAttempts())
            + "\nСкрытое слово:  " + word.getHiddenWord() + "\nОсталось попыток:  "
            + word.getRemainingAttempts() + "\nВводите по одной букве. Для подсказки введите '!'. Удачи!");
    }

    public static void showGameStatus(Word word, Figure dead, Set<Character> usedLetters) {
        LOGGER.info(dead.showGallows(word.getRemainingAttempts())
            + "\nСкрытое слово: " + word.getHiddenWord()
            + "\nОсталось попыток: " + word.getRemainingAttempts()
            + "\nУже проверенные буквы: " + usedLetters);
    }

    public static Optional<Integer> checkForInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static boolean checkForChar(String input) {
        return input.length() == 1 && input.matches("[а-яА-Я]");
    }

    public static int chooseRandomInt(int min, int max) {
        return new SecureRandom().nextInt(max + 1 - min) + min;
    }

    public static boolean startGame(Word word) {
        if (word.getWord().isEmpty()) {
            return false;
        } else {
            boolean win = playGame(word);
            if (win) {
                LOGGER.info("Поздравляю с победой!");
            } else {
                LOGGER.info("Увы, вы оказались повешены. Загаданное слово: " + word.getWord());
            }
            return true;
        }
    }

    public static boolean playGame(Word word) {
        Figure dead = new Figure();
        Set<Character> usedLetters = new HashSet<>();
        showStartGame(word, dead);
        while (word.wordIsNotShown() && word.getRemainingAttempts() > 0) {
            String input = in.nextLine();
            if (input.equals("!")) {
                LOGGER.info("Подсказка: " + word.getHint());
            } else if (word.wordIsNotShown() && nextGuess(input, word, usedLetters)) {
                    showGameStatus(word, dead, usedLetters);
                }
        }
        return word.getRemainingAttempts() > 0;
    }

    public static boolean nextGuess(String input, Word word, Set<Character> usedLetters) {
        if (!checkForChar(input)) {
            LOGGER.warning("Введите одну русскую букву.");
            return false;
        } else {
            char ch = input.charAt(0);
            if (usedLetters.contains(ch)) {
                LOGGER.info("Эта буква уже вводилась. Введите другую. ");
                return false;
            } else {
                usedLetters.add(Character.toLowerCase(ch));
                checkGuess(word, ch);
                return true;
            }
        }
    }

    public static void checkGuess(Word word, char c) {
        char ch = Character.toLowerCase(c);
        if (word.getLetters().contains(ch)) {
            for (int i = 0; i < word.getWord().length(); ++i) {
                if (word.getWord().charAt(i) == ch) {
                    word.setHiddenWord(word.getHiddenWord().substring(0, i) + ch
                                        + word.getHiddenWord().substring(i + 1));
                }
            }
        } else {
            word.setRemainingAttempts(word.getRemainingAttempts() - 1);
        }
    }

}
