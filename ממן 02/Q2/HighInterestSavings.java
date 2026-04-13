/**
 * @author Ori Nave
 * @version 13.04.26
 * HighInterestSavings class represents a savings account with a higher interest rate and a minimum balance requirement.
 */
public class HighInterestSavings extends SavingsAccount
{
    private static final double DEFAULT_HIGHER_INTEREST = 0.1;
    private static final double DEFAULT_MINIMUM_BALANCE = 1000.0;
    private double minimumBalance;

    /**
     * Constructor that uses the default higher interest rate and minimum balance.
     */
    public HighInterestSavings(String accountNumber, String name, String id, double balance)
    {
        super(accountNumber, name, id, balance, DEFAULT_HIGHER_INTEREST);
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
    }

    /**
     * Constructor that allows custom interest rate and minimum balance.
     */
    public HighInterestSavings(String accountNumber, String name, String id, double balance, double interest, double minimumBalance)
    {
        super(accountNumber, name, id, balance, interest);
        this.minimumBalance = minimumBalance;
    }

    /**
     * Getter for minimumBalance attribute.
     */
    public double getMinimumBalance()
    {
        return this.minimumBalance;
    }

    /**
     * Setter for minimumBalance attribute.
     */
    public void setMinimumBalance(double minimumBalance)
    {
        this.minimumBalance = minimumBalance;
    }

    /**
     * Withdraws money while ensuring the balance does not drop below the minimum balance.
     */
    public void withdraw(double moneyAmount) throws IllegalBalance
    {
        if (getBalance() - moneyAmount < this.minimumBalance)
            throw new IllegalBalance("You can't withdraw money that will cause you to go below minimum balance");

        super.withdraw(moneyAmount);
    }

    /**
     * Returns a string that represents HighInterestSavings object.
     */
    public String toString()
    {
        String details = super.toString() + "\n" + "Minimum Balance: " + this.minimumBalance;
        return details;
    }

    /**
     * Checks if the two HighInterestSavings objects are the same.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof HighInterestSavings))
            return false;

        if (!super.equals(obj))
            return false;

        HighInterestSavings other = (HighInterestSavings)obj;

        if (this.minimumBalance == other.minimumBalance)
            return true;
        else
            return false;
    }
}