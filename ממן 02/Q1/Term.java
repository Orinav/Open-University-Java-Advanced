/**
 * @author Ori Nave
 * @version 10.04.26
 * This class represents Term.
 */
public class Term
{
    private double base;
    private int power;

    /**
     * Term Constructor.
     */
    public Term(double base, int power)
    {
        this.base = base;
        this.power = power;
    }

    /**
     * Getter for base attribute.
     */
    public double getBase()
    {
        return this.base;
    }

    /**
     * Setter for base attribute.
     */
    public void setBase(double base)
    {
        this.base = base;
    }

    /**
     * Getter for power attribute.
     */
    public int getPower()
    {
        return this.power;
    }

    /**
     * Setter for power attribute.
     */
    public void setPower(int power)
    {
        this.power = power;
    }
}
