import javax.swing.JOptionPane;

/**
 * This class will make the interaction with the user in order to play "Bulls Hits".
 *  @author Ori Nave.
 *  @version 26.3.26
 */
public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();
        boolean playing = true;

        while (playing) {
            String guessesHistory = "";
            int guessesAmount = 0;
            boolean won = false;
            String message = "";

            while (!won) {
                message = "Enter a guess of a 4 digits number";
                if (!guessesHistory.isEmpty())
                    message = "Your guesses: \n" + guessesHistory + message;
                String guess = JOptionPane.showInputDialog(null, message, "Game Window", JOptionPane.QUESTION_MESSAGE);
                if (guess == null) {
                    message = "The game is over, Thank you for Playing!";
                    JOptionPane.showMessageDialog(null, message);
                    return;
                }

                if (!logic.isValid(guess)) {
                    message = "The input is invalid, Please make sure that: \n 1) You've entered 4 characters. \n 2) It's represents a number. \n 3) Every digit is unique.";
                    JOptionPane.showMessageDialog(null, message);
                    continue;
                }

                guessesAmount++;
                int bulls = logic.countBulls(guess);
                int hits = logic.countHits(guess);
                guessesHistory += "Guess: " + guess + ", " + "Bulls " + bulls + ", " + "Hits: " + hits + "\n";
                if (bulls == 4) {
                    won = true;
                    message = "Congratulations! You've won the game!";
                    JOptionPane.showMessageDialog(null, message);
                }
            }

            message = "Would you like to play another match?";
            int choice = JOptionPane.showConfirmDialog(null, message);
            if (choice == JOptionPane.OK_OPTION)
                logic.restartGame();
            else {
                playing = false;
                message = "You've chosen to not play another round, Thanks for playing!";
                JOptionPane.showMessageDialog(null, message);
            }
        }
    }
}