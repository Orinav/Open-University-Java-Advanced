/**
 * @author Ori Nave
 * @version 13.04.26
 * InterestChecking is class that represents checking account with a minimum balance that earns interest.
 */
public class InterestChecking extends NoServiceChargeChecking
{
    private static final double DEFAULT_INTEREST = 0.05;
    private static final double DEFAULT_MINIMUM_BALANCE = 1000.0;
    private double interest;

    /**
     * Constructor using default interest rate and a higher default minimum balance.
     */
    public InterestChecking(String accountNumber, String name, String id, double balance)
    {
        super(accountNumber, name, id, balance, DEFAULT_MINIMUM_BALANCE);
        this.interest = DEFAULT_INTEREST;
    }

    /**
     * Constructor that allows custom minimum balance and interest rate.
     */
    public InterestChecking(String accountNumber, String name, String id, double balance, double minimumBalance, double interest)
    {
        super(accountNumber, name, id, balance, minimumBalance);
        this.interest = interest;
    }

    /**
     * Getter for interest attribute.
     */
    public double getInterest()
    {
        return this.interest;
    }

    /**
     * Setter for interest attribute.
     */
    public void setInterest(double interest)
    {
        this.interest = interest;
    }

    /**
     * Calculates the interest amount which based on the account balance.
     */
    public double calculateInterest()
    {
        double result = getBalance() * this.interest;
        return result;
    }

    /**
     * Adds the calculated monthly interest to the account balance.
     */
    public void manageMonthly()
    {
        double interest = calculateInterest();
        deposit(interest);
    }
    /**
     * Returns a string that represents InterestChecking object.
     */
    public String toString()
    {
        String details = super.toString() + "\n" + "Interest Rate: " + this.interest;
        return details;
    }

    /**
     * Checks if the two InterestChecking objects are the same.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof InterestChecking))
            return false;

        if (!super.equals(obj))
            return false;

        InterestChecking other = (InterestChecking)obj;

        if (this.interest == other.interest)
            return true;
        else
            return false;
    }
}