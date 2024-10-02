package backendacademy.hangman;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        LOGGER.info("Добро пожаловать в игру Виселица! \nДля старта нажмите 1. Для выхода - 0.");
        Dictionary dict = new Dictionary();
        while (IN.nextLine().equals("1")) {
            Word word = chooseWordByOptions(dict);
            if (word == null || word.getWord().isEmpty()) {
                LOGGER.warning("Ошибка в получении слова или в полученном слове");
            } else {
                playGame(word);
            }
            LOGGER.info("Для новой игры нажмите 1. Для выхода из игры - 0.");
        }
        LOGGER.info("Пришло время выйти из игры.");
    }

    public static Word chooseWordByOptions(Dictionary dict) {
        LOGGER.info("Доступные категории: " + Arrays.toString(dict.getKeys())
                    + "\nВведите порядковый номер выбранной категории или 0 для случайного выбора");
        int numberOfCategory = checkForInt(IN.nextLine());
        while (numberOfCategory != 0 && dict.numberOfCategoryNotExists(numberOfCategory)) {
            LOGGER.info("Введите порядковый номер выбранной категории (их " + dict.numberOfCategories()
                        + ") или 0 для случайного выбора");
            numberOfCategory = checkForInt(IN.nextLine());
        }
        if (numberOfCategory == 0) {
            LOGGER.info("Будет выбрана случайная категория");
            numberOfCategory = new SecureRandom().nextInt(dict.numberOfCategories()) + 1;
        } else {
            LOGGER.info("Выбрана категория: " + dict.getKey(numberOfCategory - 1));
        }
        LOGGER.info("Выберите уровень сложности (1,2,3) или 0 для случайного выбора");
        int level = checkForInt(IN.nextLine());
        while (level != 0 && dict.levelNotExists(level)) {
            LOGGER.info("Введите уровень сложности (их " + dict.getMaxLevel()
                + ") или 0 для случайного выбора уровня");
            level = checkForInt(IN.nextLine());
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

    public static int checkForInt(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static char getCharFromUser() {
        while (true) {
            String input = IN.nextLine();
            if (input.equals("!") || (input.length() == 1 && input.matches("[а-яА-Я]"))) {
                return Character.toLowerCase(input.charAt(0));
            } else {
                LOGGER.warning("Введите одну русскую букву.");
            }
        }
    }

    public static void showGameStatus(Word word, Figure dead, Set<Character> usedLetters) {
        LOGGER.info(dead.showGallows(word.getRemainingAttempts())
            + "\nСкрытое слово: " + word.getHiddenWord()
            + "\nОсталось попыток: " + word.getRemainingAttempts()
            + "\nУже проверенные буквы: " + usedLetters);
    }

    public static void playGame(Word word) {
        Figure dead = new Figure();
        Set<Character> usedLetters = new HashSet<>();
        showStartGame(word, dead);
        while (word.wordIsNotShown() && word.getRemainingAttempts() > 0) {
            char ch = getCharFromUser();
            if (ch == '!') {
                LOGGER.info("Подсказка: " + word.getHint());
                continue;
            }
            if (!usedLetters.contains(ch)) {
                usedLetters.add(ch);
                word.checkGuess(ch);
                showGameStatus(word, dead, usedLetters);
            } else {
                LOGGER.info("Эта буква уже вводилась. Введите другую. ");
            }
        }
        if (word.getRemainingAttempts() <= 0) {
            LOGGER.info("Увы, вы оказались повешены. Загаданное слово: " + word.getWord());
        } else {
            LOGGER.info("Поздравляю с победой!");
        }
    }
}
