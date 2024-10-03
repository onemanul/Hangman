package backendacademy.hangman;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class Dictionary {

    private final LinkedHashMap<String, List<Word>> categories = new LinkedHashMap<>();
    private final String[] keys;
    private final int maxLevel = 3;
    private final int maxAttempts = 6;

    public Dictionary() {
        this.categories.put("чувства", List.of(
            new Word("гнев", "Сильное эмоциональное возбуждение", 1, maxAttempts),
            new Word("страх", "Ощущение опасности или угрозы", 1, maxAttempts),
            new Word("обида", "Чувство оскорбления или ущерба", 1, maxAttempts),
            new Word("любовь", "Сильнейшее человеческое чувство", 1, maxAttempts),
            new Word("печаль", "Состояние грусти или меланхолии", 1, maxAttempts),
            new Word("счастье", "Исходное состояние души", 2, maxAttempts),
            new Word("радость", "Положительное эмоциональное состояние", 2, maxAttempts),
            new Word("гордость", "Чувство удовлетворения от достижений", 2, maxAttempts),
            new Word("надежда", "Вера в лучшее будущее", 2, maxAttempts),
            new Word("тревога", "Состояние нервного напряжения", 2, maxAttempts),
            new Word("отчаяние", "Чувство безнадежности", maxLevel, maxAttempts),
            new Word("сожаление", "Чувство раскаяния", maxLevel, maxAttempts),
            new Word("уверенность", "Чувство безопасности и стабильности", maxLevel, maxAttempts),
            new Word("зависть", "Чувство неудовлетворенности или ревности", maxLevel, maxAttempts),
            new Word("презрение", "Чувство неуважения", maxLevel, maxAttempts)));

        this.categories.put("программирование", List.of(
            new Word("алгоритм", "Последовательность шагов для решения проблемы", 1, maxAttempts),
            new Word("программа", "Набор инструкций для компьютера", 1, maxAttempts),
            new Word("код", "Набор инструкций, написанных на языке программирования", 1, maxAttempts),
            new Word("функция", "Блок кода, выполняющий определенное действие", 1, maxAttempts),
            new Word("цикл", "Последовательность шагов, повторяющихся несколько раз", 1, maxAttempts),
            new Word("условие", "Правило, определяющее выполнение определенного действия", 2, maxAttempts),
            new Word("оператор", "Символ, используемый для выполнения определенного действия", 2, maxAttempts),
            new Word("массив", "Коллекция данных, хранящихся в одной переменной", 2, maxAttempts),
            new Word("объект", "Коллекция данных и функций, описывающих его поведение", 2, maxAttempts),
            new Word("класс", "Шаблон для создания объектов", 2, maxAttempts),
            new Word("переменная", "Место хранения данных в программе", maxLevel, maxAttempts),
            new Word("синтаксис", "Правила написания кода", maxLevel, maxAttempts),
            new Word("наследование", "Связь между классами, принцип ООП", maxLevel, maxAttempts),
            new Word("полиморфизм", "Способность объектов принимать разные формы", maxLevel, maxAttempts),
            new Word("абстракция", "Механизм, позволяющий скрывать детали реализации", maxLevel, maxAttempts)));

        this.categories.put("смерть", List.of(
            new Word("похороны", "Прощание с умершим", 1, maxAttempts),
            new Word("могила", "Последнее пристанище", 1, maxAttempts),
            new Word("поминки", "Вспоминание о умершем", 1, maxAttempts),
            new Word("пепел", "Остатки сожженного тела", 1, maxAttempts),
            new Word("траур", "Горе от утраты", 1, maxAttempts),
            new Word("скелет", "Остатки тела", 2, maxAttempts),
            new Word("мертвец", "Тело без жизни", 2, maxAttempts),
            new Word("убийство", "Преступление против жизни", 2, maxAttempts),
            new Word("самоубийство", "Свободный выбор смерти", 2, maxAttempts),
            new Word("мемориал", "Память о погибших", 2, maxAttempts),
            new Word("надгробие", "Памятник на могиле", maxLevel, maxAttempts),
            new Word("эпитафия", "Надпись на могиле", maxLevel, maxAttempts),
            new Word("катафалк", "Вещь, на которой несут гроб", maxLevel, maxAttempts),
            new Word("некромантия", "Практика общения с умершими", maxLevel, maxAttempts),
            new Word("танатология", "Наука о смерти и умирании", maxLevel, maxAttempts)));
        this.keys = categories.keySet().toArray(String[]::new);
    }

    public Optional<Word> getWord(int numberOfCategory, int level) {      // optional
        if (numberOfCategoryNotExists(numberOfCategory) || levelNotExists(level)) {
            return Optional.empty();
        } else {
            List<Word> words = new ArrayList<>();
            for (Word w : categories.get(
                keys[numberOfCategory - 1])) {   // изменяем порядковый номер на индекс в массиве
                if (w.getLevel() == level) {
                    words.add(w);
                }
            }
            if (!words.isEmpty()) {
                int randIndex = new SecureRandom().nextInt(words.size());
                return Optional.of(words.get(randIndex));
            } else {
                return Optional.empty();
            }
        }
    }

    public int numberOfCategories() {
        return keys.length;
    }

    public boolean numberOfCategoryNotExists(int number) {
        return (number < 1 || number > numberOfCategories());
    }

    public boolean levelNotExists(int level) {
        return (level < 1 || level > maxLevel);
    }

    public String[] getKeys() {
        return keys;
    }

    public String getKey(int index) {
        try {
            return keys[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Категории с таким номером не существует";
        }
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
