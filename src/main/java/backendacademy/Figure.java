package backendacademy;

public class Figure {

    private final String[] gallowsPictures = {
        """

    +-----+
    |     |
          |
          |
          |
          |
===========""",

        """

    +-----+
    |     |
    0     |
          |
          |
          |
===========""",

        """

    +-----+
    |     |
    0     |
    |     |
          |
          |
===========""",

        """

    +-----+
    |     |
    0     |
   /|     |
          |
          |
===========""",

        """

    +-----+
    |     |
    0     |
   /|\\    |
          |
          |
===========""",

        """

    +-----+
    |     |
    0     |
   /|\\    |
   /      |
          |
===========""",

        """

    +-----+
    |     |
    0     |
   /|\\    |
   / \\    |
          |
==========="""
    };

    public String showGallows(int remainingAttempts) {
        if (remainingAttempts >= 0 && remainingAttempts < gallowsPictures.length) {
            return gallowsPictures[gallowsPictures.length - remainingAttempts - 1];
        } else {
            return "Ошибка в количестве оставшихся попыток";
        }
    }
}
