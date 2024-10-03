package backendacademy.hangman;

public class Figure {

    private final int numberOfPictures = 7;
    private final String[] gallowsPictures = new String[numberOfPictures];

    public Figure() {
        int i = 0;
        gallowsPictures[i] = """

    +-----+
    |     |\\
          | \\
          |  \\
          |   \\
          |    \\
==================""";
        gallowsPictures[++i] = gallowsPictures[i - 1].replace("          | \\", "    0     | \\");
        gallowsPictures[++i] = gallowsPictures[i - 1].replace("          |  \\", "    |     |  \\");
        gallowsPictures[++i] = gallowsPictures[i - 1].replace(" |     | ", "/|     | ");
        gallowsPictures[++i] = gallowsPictures[i - 1].replace("/| ", "/|\\");
        gallowsPictures[++i] = gallowsPictures[i - 1].replace("          |   \\", "   /      |   \\");
        gallowsPictures[++i] = gallowsPictures[i - 1].replace("/      |", "/ \\    |");
    }

    public String showGallows(int remainingAttempts) {
        try {
            return gallowsPictures[gallowsPictures.length - remainingAttempts - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Ошибка в количестве оставшихся попыток";
        }
    }
}
