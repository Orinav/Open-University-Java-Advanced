import java.util.Random;

/**
 * This class will implement the game logic of "Bulls Hits".
 * @author Ori Nave.
 * @version 26.3.26
 */
public class Logic
{
    private static final int VALID_LENGTH = 4;
    private static final int NUMBER_OF_DIGITS = 10;
    private String secretNumber;

    /**
     * Constructor of the game logic class.
     */
    public Logic()
    {
        restartGame();
    }

    /**
     * restartGame function will be called when you want to start a game or play again and will produce a random valid 4 digits number.
     */
    public void restartGame()
    {
        Random random = new Random();
        String newSecretNumber = "";
        boolean[] usedDigits = new boolean[NUMBER_OF_DIGITS];

        while (newSecretNumber.length() < VALID_LENGTH)
        {
            int randomDigit = random.nextInt(10);
            if (!usedDigits[randomDigit])
            {
                newSecretNumber += randomDigit;
                usedDigits[randomDigit] = true;
            }
        }
        secretNumber = newSecretNumber;
    }

    /**
     * countHits will count how many hits were made in each guess.
     */
    public int countHits(String guess)
    {
        int counter = 0;
        for (int i = 0; i < guess.length(); i++)
        {
            char currentDigit = guess.charAt(i);
            int index = secretNumber.indexOf(currentDigit);
            if (index != -1 && index != i) //If the current digit is found somewhere in the number and is not "bull" then count it
                counter++;
        }
        return counter;
    }

    /**
     * countBulls will count how many bulls were made in each guess.
     */
    public int countBulls(String guess)
    {
        int counter = 0;
        for (int i = 0; i < guess.length(); i++)
        {
            if (guess.charAt(i) == secretNumber.charAt(i))
                counter++;
        }
        return counter;
    }

    /**
     * isValid will check if the string we entered as a guess is valid, the string is valid if and only if:
     * 1) It's in the length of VALID_LENGTH (VALID LENGTH=4 for Maman 01).
     * 2) It's represents a number.
     * 2) Every digit is unique.
     */
    public boolean isValid(String str)
    {
        if (str.length() != VALID_LENGTH)
            return false;
        for (int i = 0 ; i < str.length() ; i++)
        {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        int[] seen = new int[NUMBER_OF_DIGITS];
        for (int i = 0 ; i < str.length() ; i++)
        {
            int digit = str.charAt(i) - '0';
            if (seen[digit] != 0)
                return false;
            seen[digit]++;
        }
        return true;
    }
}
