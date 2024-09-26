package backendacademy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        LOGGER.info("Добро пожаловать в игру Виселица! \n\nДля старта нажмите 1. Для выхода - 0.");
        while (IN.nextLine().equals("1")) {
            Word word = chooseWordByOptions();
            playGame(word);
            LOGGER.info("Для новой игры нажмите 1. Для выхода из игры - 0.");
        }
        LOGGER.info("Пришло время выйти из игры.");
    }

    public static Word chooseWordByOptions() {
        Dictionary dict = new Dictionary();
        LOGGER.info("Доступные категории: " + Arrays.toString(dict.getKeys())
                    + "\nВведите номер выбранной категории или 0 для случайного выбора");
        int numberOfCategory = checkForInt(IN.nextLine());
        LOGGER.info("Выбереите уровень сложности (1,2,3) или 0 для случайного выбора");
        return dict.getWord(numberOfCategory, checkForInt(IN.nextLine()));
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
            return 0;
        }
    }

    public static char checkForChar() {
        while (true) {
            String input = IN.nextLine();
            if (input.equals("!") || (input.length() == 1 && input.matches("[а-яА-Я]"))) {
                return Character.toLowerCase(input.charAt(0));
            } else {
                LOGGER.warning("Введите одну букву.");
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
        while (word.wordIsNotShown() && word.getRemainingAttempts() != 0) {
            char ch = checkForChar();
            if (ch == '!') {
                LOGGER.info("Подсказка: " + word.getHint());
                continue;
            }
            if (!usedLetters.contains(ch)) {
                usedLetters.add(ch);
                word.checkGuess(ch);
                showGameStatus(word, dead, usedLetters);
            } else {
                LOGGER.info("Эта буква уже вводилась.");
            }
        }
        if (word.getRemainingAttempts() == 0) {
            LOGGER.info("Увы, вы оказались повешены. Загаданное слово: " + word.getWord());
        } else {
            LOGGER.info("Поздравляю с победой!");
        }
    }
}
