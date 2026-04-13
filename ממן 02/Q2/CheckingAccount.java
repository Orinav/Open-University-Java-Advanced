/**
 * @author Ori Nave
 * @version 13.04.26
 * CheckingAccount is an abstract class which allows to write checks.
 */
public abstract class CheckingAccount extends BankAccount
{
    /**
     * Constructor.
     */
    public CheckingAccount(String accountNumber, String name, String id, double balance)
    {
        super(accountNumber, name, id, balance);
    }

    /**
     * Writes a check with an amount of money which the method gets as a parameter.
     */
    public void writeCheck(double moneyAmount) throws IllegalBalance
    {
        withdraw(moneyAmount);
    }
}