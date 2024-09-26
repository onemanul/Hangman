package backendacademy;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Dictionary {

    private final LinkedHashMap<String, List<Word>> categories = new LinkedHashMap<>();
    private final String[] keys;
    private final int maxLevel = 3;

    public Dictionary() {
        this.categories.put("чувства", List.of(
            new Word("гнев", "Сильное эмоциональное возбуждение", 1),
            new Word("страх", "Ощущение опасности или угрозы", 1),
            new Word("обида", "Чувство оскорбления или ущерба", 1),
            new Word("любовь", "Сильнейшее человеческое чувство", 1),
            new Word("печаль", "Состояние грусти или меланхолии", 1),
            new Word("счастье", "Исходное состояние души", 2),
            new Word("радость", "Положительное эмоциональное состояние", 2),
            new Word("гордость", "Чувство удовлетворения от достижений", 2),
            new Word("надежда", "Вера в лучшее будущее", 2),
            new Word("тревога", "Состояние нервного напряжения", 2),
            new Word("отчаяние", "Чувство безнадежности", maxLevel),
            new Word("сожаление", "Чувство раскаяния", maxLevel),
            new Word("уверенность", "Чувство безопасности и стабильности", maxLevel),
            new Word("зависть", "Чувство неудовлетворенности или ревности", maxLevel),
            new Word("презрение", "Чувство неуважения", maxLevel)));

        this.categories.put("программирование", List.of(
            new Word("алгоритм", "Последовательность шагов для решения проблемы", 1),
            new Word("программа", "Набор инструкций для компьютера", 1),
            new Word("код", "Набор инструкций, написанных на языке программирования", 1),
            new Word("функция", "Блок кода, выполняющий определенное действие", 1),
            new Word("цикл", "Последовательность шагов, повторяющихся несколько раз", 1),
            new Word("условие", "Правило, определяющее выполнение определенного действия", 2),
            new Word("оператор", "Символ, используемый для выполнения определенного действия", 2),
            new Word("массив", "Коллекция данных, хранящихся в одной переменной", 2),
            new Word("объект", "Коллекция данных и функций, описывающих его поведение", 2),
            new Word("класс", "Шаблон для создания объектов", 2),
            new Word("переменная", "Место хранения данных в программе", maxLevel),
            new Word("синтаксис", "Правила написания кода", maxLevel),
            new Word("наследование", "Механизм, позволяющий создавать новые классы на основе существующих", maxLevel),
            new Word("полиморфизм", "Способность объектов принимать разные формы", maxLevel),
            new Word("абстракция", "Механизм, позволяющий скрывать детали реализации", maxLevel)));

        this.categories.put("смерть", List.of(
            new Word("похороны", "Прощание с умершим", 1),
            new Word("могила", "Последнее пристанище", 1),
            new Word("поминки", "Вспоминание о умершем", 1),
            new Word("пепел", "Остатки сожженного тела", 1),
            new Word("траур", "Горе от утраты", 1),
            new Word("скелет", "Остатки тела", 2),
            new Word("мертвец", "Тело без жизни", 2),
            new Word("убийство", "Преступление против жизни", 2),
            new Word("самоубийство", "Свободный выбор смерти", 2),
            new Word("мемориал", "Память о погибших", 2),
            new Word("надгробие", "Памятник на могиле", maxLevel),
            new Word("эпитафия", "Надпись на могиле", maxLevel),
            new Word("катафалк", "Вещь, на которой несут гроб", maxLevel),
            new Word("некромантия", "Практика общения с умершими", maxLevel),
            new Word("танатология", "Наука о смерти и умирании", maxLevel)));
        this.keys = categories.keySet().toArray(String[]::new);
    }

    public Word getWord(int number, int level) {
        int numberOfCategory = number;
        if (numberOfCategory <= 0 || numberOfCategory > keys.length) {
            numberOfCategory = new SecureRandom().nextInt(keys.length);
        } else {
            numberOfCategory--;
        }
        if (level >= 1 && level <= maxLevel) {
            List<Word> words = new ArrayList<>();
            for (Word w : categories.get(keys[numberOfCategory])) {
                if (w.getLevel() == level) {
                    words.add(w);
                }
            }
            if (!words.isEmpty()) {
                int randIndex = new SecureRandom().nextInt(words.size());
                return words.get(randIndex);
            }
        }
        int randIndex = new SecureRandom().nextInt(categories.get(keys[numberOfCategory]).size());
        return categories.get(keys[numberOfCategory]).get(randIndex);
    }

    public String[] getKeys() {
        return keys;
    }
}
