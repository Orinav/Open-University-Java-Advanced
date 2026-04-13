/**
 * @author Ori Nave
 * @version 13.04.26
 * NoServiceChargeChecking is class that represents a free checking account that enforces a minimum balance.
 */
public class NoServiceChargeChecking extends CheckingAccount
{
    private static final double DEFAULT_MINIMUM_BALANCE = 100.0;
    private double minimumBalance;

    /**
     * Constructor that uses the default minimum balance.
     */
    public NoServiceChargeChecking(String accountNumber, String name, String id, double balance)
    {
        super(accountNumber, name, id, balance);
        this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
    }

    /**
     * Constructor that allows a custom minimum balance.
     */
    public NoServiceChargeChecking(String accountNumber, String name, String id, double balance, double minimumBalance)
    {
        super(accountNumber, name, id, balance);
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
    public void withdraw (double moneyAmount) throws IllegalBalance
    {
        if (getBalance() - moneyAmount < this.minimumBalance)
            throw new IllegalBalance("You cannot withdraw an amount that will make your balance below the minimum balance");
        else
            super.withdraw(moneyAmount);
    }

    /**
     * Implementation of the abstract method of BankAccount class... This method does nothing, we only implement it in order to create a NoServiceChargeChecking object.
     */
    public void manageMonthly(){}

    /**
     * Returns a string that represents NoServiceChargeChecking object.
     */
    public String toString()
    {
        String details = super.toString() + "\n" + "Minimum Balance: " + this.minimumBalance;
        return details;
    }

    /**
     * Checks if the two NoServiceChargeChecking objects are the same.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof NoServiceChargeChecking))
            return false;

        if (!super.equals(obj))
            return false;

        NoServiceChargeChecking other = (NoServiceChargeChecking)obj;

        if (this.minimumBalance == other.minimumBalance)
            return true;
        else
            return false;
    }
}