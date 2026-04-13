/**
 * @author Ori Nave
 * @version 13.04.26
 * SavingAccount class represents a standard savings bank account that earns monthly interest.
 */
public class SavingsAccount extends BankAccount
{
    private static final double DEFAULT_INTEREST = 0.05;
    private double interest;

    /**
     * Constructor that uses the default interest rate.
     */
    public SavingsAccount(String accountNumber, String name, String id, double balance) {
        super(accountNumber, name, id, balance);
        this.interest = DEFAULT_INTEREST;
    }

    /**
     * Constructor that allows custom interest rate.
     */
    public SavingsAccount(String accountNumber, String name, String id, double balance, double interest) {
        super(accountNumber, name, id, balance);
        this.interest = interest;
    }

    /**
     * Getter for interest attribute.
     */
    public double getInterest() {
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
     * Calculates the interest amount based on the account balance.
     */
    public double calculateInterest()
    {
        return getBalance() * this.interest;
    }

    /**
     * Deposits the calculated monthly interest into the account.
     */
    public void manageMonthly() {
        double interest = calculateInterest();
        deposit(interest);
    }

    /**
     * Returns a string that represents SavingsAccount object.
     */
    public String toString()
    {
        String details = super.toString() + "\n" + "Interest Rate: " + this.interest;
        return details;
    }

    /**
     * Checks if the two SavingsAccount objects are the same.
     */
    public boolean equals(Object obj) {

        if (!(obj instanceof SavingsAccount))
            return false;

        if (!super.equals(obj))
            return false;

        SavingsAccount other = (SavingsAccount) obj;

        if (this.interest == other.interest)
            return true;
        else
            return false;

    }
}