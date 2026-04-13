/**
 * @author Ori Nave
 * @version 13.04.26
 * IllegalBalance class is an exception that's been thrown when bank account has insufficient money.
 */
public class IllegalBalance extends Exception
{
    /**
     * Empty Constructor.
     */
    public IllegalBalance()
    {
        super();
    }

    /**
     * Constructor that accepts a message that will explain the user why the exception has been thrown.
     */
    public IllegalBalance(String message)
    {
        super(message);
    }
}