/**
 * @author Ori Nave
 * @version 13.04.26
 * ServiceChargeChecking is class that represents a checking account that charges a monthly fee.
 */
public class ServiceChargeChecking extends CheckingAccount
{
    private static final double DEFAULT_FEE = 10.0;
    private double monthlyFee;

    /**
     * Constructor that uses the default monthly fee.
     */
    public ServiceChargeChecking(String accountNumber, String name, String id, double balance)
    {
        super(accountNumber, name, id, balance);
        this.monthlyFee = DEFAULT_FEE;
    }

    /**
     * Constructor that allows a custom monthly fee.
     */
    public ServiceChargeChecking(String accountNumber, String name, String id, double balance, double monthlyFee)
    {
        super(accountNumber, name, id, balance);
        this.monthlyFee = monthlyFee;
    }

    /**
     * Getter for monthlyFee attribute.
     */
    public double getMonthlyFee()
    {
        return this.monthlyFee;
    }

    /**
     * Setter for monthlyFee attribute.
     */
    public void setMonthlyFee(double monthlyFee)
    {
        this.monthlyFee = monthlyFee;
    }

    /**
     * subtracts the monthly fee from the account balance.
     */
    public void manageMonthly()
    {
        double currentBalance = getBalance();
        setBalance(currentBalance - monthlyFee);
    }

    /**
     * Returns a string that represents ServiceChargeChecking object.
     */
    public String toString()
    {
        String details = super.toString() + "\n" + "Monthly Fee: " + this.monthlyFee;
        return details;
    }

    /**
     * Checks if the two ServiceChargeChecking objects are the same.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof ServiceChargeChecking))
            return false;

        if (!super.equals(obj))
            return false;

        ServiceChargeChecking other = (ServiceChargeChecking)obj;

        if (this.monthlyFee == other.monthlyFee)
            return true;
        else
            return false;
    }
}