/**
 * @author Ori Nave
 * @version 13.04.26
 * BankAccount is an abstract class that represents generic bank account with common attributes and methods.
 */
public abstract class BankAccount
{
    private String accountNumber;
    private String name;
    private String id;
    private double balance;

    /**
     * Constructor.
     */
    public BankAccount(String accountNumber, String name, String id, double balance)
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    /**
     * Getter for accountNumber attribute.
     */
    public String getAccountNumber()
    {
        return this.accountNumber;
    }

    /**
     * Getter for name attribute.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for id attribute.
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Getter for balance attribute.
     */
    public double getBalance()
    {
        return this.balance;
    }

    /**
     * Setter for name attribute.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for balance attribute.
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * Deposits a given amount of money into the bank account.
     */
    public void deposit(double moneyAmount)
    {
        if (moneyAmount > 0)
            this.balance += moneyAmount;
    }

    /**
     * Withdraws a given amount of money from the bank account.
     */
    public void withdraw(double moneyAmount) throws IllegalBalance
    {
        if (this.balance - moneyAmount < 0)
            throw new IllegalBalance("You cannot withdraw more money that what you got in the bank");
        else
            this.balance -= moneyAmount;
    }

    /**
     * Abstract method to handle monthly management operations like interest and fee.
     */
    public abstract void manageMonthly();

    /**
     * Returns a string that represents BankAccount object.
     */
    public String toString()
    {
        String accountDetails = "Account Number: " + this.accountNumber + "\n" + "Name: " + this.name + "\n" + "ID: " + this.id + "\n" + "Balance: " + this.balance;
        return accountDetails;
    }

    /**
     * Checks if the two BankAccount objects are the same.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof BankAccount))
            return false;

        BankAccount other = (BankAccount) obj;

        if (this.accountNumber.equals(other.accountNumber) && this.name.equals(other.name) && this.id.equals(other.id) && this.balance == other.balance)
            return true;
        else
            return false;
    }
}