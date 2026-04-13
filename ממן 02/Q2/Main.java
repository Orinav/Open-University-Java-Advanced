/**
 * @author Ori Nave
 * @version 13.04.26
 * Main class is a class that sets to test the bank account hierarchy, perform actions like deposit and withdraw and applying monthly management.
 */
public class Main
{
    /**
     * main function is set to execute the program.
     */
    public static void main(String[] args)
    {
        BankAccount[] accounts = new BankAccount[5];

        //Constructor
        accounts[0] = new ServiceChargeChecking("101", "Ori Nave", "123456789", 1000.0, 20.0);
        accounts[1] = new NoServiceChargeChecking("102", "Yossi Cohen", "987654321", 500.0, 100.0);
        accounts[2] = new InterestChecking("103", "Dana Levi", "112233445", 2000.0, 1000.0, 0.05);
        accounts[3] = new SavingsAccount("104", "Avi Mizrahi", "556677889", 5000.0, 0.03);
        accounts[4] = new HighInterestSavings("105", "Galit Peretz", "998877665", 10000.0, 0.08, 2000.0);

        System.out.println("Initializing accounts:");
        printAllAccounts(accounts);

        System.out.println("Starting deposit and withdraw actions: \n");
        for (int i = 0; i < accounts.length; i++)
        {
            BankAccount currentAccount = accounts[i];
            System.out.println("Starting deposit and withdraw actions for account number: " + currentAccount.getAccountNumber());

            //deposit
            System.out.println("Action: Depositing 500.0 \n");
            currentAccount.deposit(500.0);
            printSingleAccount(currentAccount);

           //withdraw
            try {
                double withdrawAmount = 1200.0;
                System.out.println("Action: attempting to withdraw " + withdrawAmount + "\n");
                currentAccount.withdraw(withdrawAmount);
                printSingleAccount(currentAccount);
            } catch (IllegalBalance e)
            {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        //manageMonthly
        System.out.println("Starting monthly management for all accounts: \n");
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].manageMonthly();
            System.out.println("After monthly management:");
            printSingleAccount(accounts[i]);
        }
    }

    /**
     * printSingleAccount function will print a single account details.
     */
    private static void printSingleAccount(BankAccount account)
    {
        String type = "";
        if (account instanceof HighInterestSavings) type = "High Interest Savings";
        else if (account instanceof SavingsAccount) type = "Savings Account";
        else if (account instanceof InterestChecking) type = "Interest Checking";
        else if (account instanceof NoServiceChargeChecking) type = "No Service Charge Checking";
        else if (account instanceof ServiceChargeChecking) type = "Service Charge Checking";

        System.out.println("Account Type: " + type);

        //toString
        System.out.println(account + "\n");
    }

    /**
     * printAllAccount function will print all the accounts and their details.
     */
    private static void printAllAccounts(BankAccount[] accounts)
    {
        for (int i = 0; i < accounts.length; i++)
        {
            printSingleAccount(accounts[i]);
        }
    }
}