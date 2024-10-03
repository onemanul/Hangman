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
        LOGGER.info("Добро пожаловать в игру Виселица! \nДля старта нажмите 1. Для выхода - 0.");
        Dictionary dict = new Dictionary();
        while (in.nextLine().equals("1")) {
            Optional<Word> optWord = chooseWordByOptions(dict);
            if (optWord.isEmpty() || !startGame(optWord.get())) {
                LOGGER.warning("Ошибка в получении слова или в полученном слове, старт невозможен");
            }
            LOGGER.info("Для новой игры нажмите 1. Для выхода из игры - 0.");
        }
        LOGGER.info("Пришло время выйти из игры.");
    }

    public static Optional<Word> chooseWordByOptions(Dictionary dict) {
        LOGGER.info("Доступные категории: " + Arrays.toString(dict.getKeys())
                    + "\nВведите порядковый номер выбранной категории или 0 для случайного выбора");
        int numberOfCategory = checkForInt(in.nextLine());
        while (numberOfCategory != 0 && dict.numberOfCategoryNotExists(numberOfCategory)) {
            LOGGER.info("Введите порядковый номер выбранной категории (их " + dict.numberOfCategories()
                        + ") или 0 для случайного выбора");
            numberOfCategory = checkForInt(in.nextLine());
        }
        if (numberOfCategory == 0) {
            LOGGER.info("Будет выбрана случайная категория");
            numberOfCategory = new SecureRandom().nextInt(dict.numberOfCategories()) + 1;
        } else {
            LOGGER.info("Выбрана категория: " + dict.getKey(numberOfCategory - 1));
        }
        LOGGER.info("Выберите уровень сложности (1,2,3) или 0 для случайного выбора");
        int level = checkForInt(in.nextLine());
        while (level != 0 && dict.levelNotExists(level)) {
            LOGGER.info("Введите уровень сложности (их " + dict.getMaxLevel()
                + ") или 0 для случайного выбора уровня");
            level = checkForInt(in.nextLine());
        }
        if (level == 0) {
            level = new SecureRandom().nextInt(dict.getMaxLevel()) + 1;
        }
        return dict.getWord(numberOfCategory, level);
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

    public static int checkForInt(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean checkForChar(String input) {
        return input.length() == 1 && input.matches("[а-яА-Я]");
    }

    public static boolean startGame(Word word) {
        if (word.getWord().isEmpty()) {
            return false;
        } else {
            if (playGame(word)) {
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
            } else if (nextGuess(input, word, usedLetters) && word.wordIsNotShown()) {
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
                word.checkGuess(ch);
                return true;
            }
        }
    }

}
