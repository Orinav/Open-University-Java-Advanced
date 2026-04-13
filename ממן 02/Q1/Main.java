import java.util.Scanner;

/**
 * @author Ori Nave
 * @version 10.04.26
 * Main class is a class that sets to test Polynom methods.
 */
public class Main
{
    /**
     * main function is set to execute the program.
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        try
        {
            //Gets the first Polynom
            System.out.println("First Polynom:");
            Polynom p1 = scanPolynom(scan);

            //Gets the second Polynom
            System.out.println("\nSecond Polynom:");
            Polynom p2 = scanPolynom(scan);

            //toString
            System.out.println("\nThe Polynoms are:");
            System.out.println("P1: " + p1);
            System.out.println("P2: " + p2);


            System.out.println("\nMethods results:");

            //plus
            System.out.println("P1 + P2 = " + p1.plus(p2));

            //minus
            System.out.println("P1 - P2 = " + p1.minus(p2));

            //derivative
            System.out.println("P1 derivative = " + p1.derivative());

            //equals
            System.out.println("P1 equals P2: " + p1.equals(p2));

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * scanPolynom used to get polynom input from the user.
     */
    private static Polynom scanPolynom(Scanner scan) throws Exception
    {
        System.out.print("Enter the number of elements you desire in the current polynom: ");
        int n = scan.nextInt();

        double[] bases = new double[n];
        int[] powers = new int[n];

        for (int i = 0; i < n; i++)
        {
            System.out.print("Enter a base: ");
            bases[i] = scan.nextDouble();
            System.out.print("Enter a power: ");
            powers[i] = scan.nextInt();

        }
        return new Polynom(bases, powers);
    }
}