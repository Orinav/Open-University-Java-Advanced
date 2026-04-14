import java.util.ArrayList;

/**
 * @author Ori Nave
 * @version 10.04.26
 * This class represents Polynom.
 */
public class Polynom
{
    private ArrayList<Term> polynom;

    /**
     * Polynom constructor.
     */
    public Polynom(double[] bases, int[] powers) throws Exception
    {
        if (bases.length != powers.length)
            throw new Exception("bases array length must be equal to powers array length");

        polynom = new ArrayList<Term>();

        for (int i = 0; i < bases.length; i++)
        {
            double currentBase = bases[i];
            int currentPower = powers[i];

            if (currentBase == 0)
                continue;

            boolean powerExists = false;

            for (int j = 0; j < polynom.size(); j++)
            {
                Term existingTerm = polynom.get(j);

                if (existingTerm.getPower() == currentPower)
                {
                    double newBase = existingTerm.getBase() + currentBase;
                    existingTerm.setBase(newBase);
                    powerExists = true;

                    if (newBase == 0)
                        polynom.remove(j);
                    break;
                }
            }

            if (powerExists == false)
                polynom.add(new Term(currentBase, currentPower));
        }



        //Selection Sort (sorting by highest power)
        for (int i = 0; i < polynom.size() - 1; i++)
        {
            int biggestPowerIndex = i;

            for (int j = i + 1; j < polynom.size(); j++)
            {
                int jCurrentPower =  polynom.get(j).getPower();
                int biggestPower = polynom.get(biggestPowerIndex).getPower();

                if (jCurrentPower > biggestPower)
                    biggestPowerIndex = j;
            }

            if (biggestPowerIndex != i)
            {
                Term temporary = polynom.get(i);
                polynom.set(i, polynom.get(biggestPowerIndex));
                polynom.set(biggestPowerIndex, temporary);
            }
        }
    }

    /**
     * Returns a string that represents Polynom object.
     */
    public String toString()
    {
        String str = "";

        for (int i = 0; i < polynom.size(); i++)
        {
            Term currentTerm = polynom.get(i);
            double base = currentTerm.getBase();
            int power = currentTerm.getPower();

            // ##### Base handler #####
            if (base == 0)
                continue;

            if (str.equals(""))
            {
                if (!(base == 1 && power > 0))
                {
                    if (base == -1 && power > 0)
                            str += "-";
                    else
                        str += base;
                }
            }

            else // i != 0
            {
                if (base == 1 && power > 0)
                    str += "+";
                else if (base == -1 && power > 0)
                    str += "-";
                else if (base == 1 || base == -1)
                {
                    if (base == 1)
                        str += "+" + (int)base;
                    else //base == -1
                        str += (int)base;
                }
                else
                {
                    if (base > 0)
                        str += "+" + base;
                    else //base < 0
                        str += base;
                }


            }

            // ##### Power handler #####
            if (power == 1)
                str += "x";
            else if (power > 1)
                str += "x" + "^" + power;
        }

        if (str.equals("") || str.equals("-"))
            return "0";

        return str;
    }

    /**
     * Gets the sum of our polynom with other polynom.
     */
    public Polynom plus(Polynom other) throws Exception
    {
        ArrayList<Double> combinedBases = new ArrayList<>();
        ArrayList<Integer> combinedPowers = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < this.polynom.size() && j < other.polynom.size())
        {
            Term thisTerm = this.polynom.get(i);
            double thisBase = thisTerm.getBase();
            int thisPower = thisTerm.getPower();

            Term otherTerm = other.polynom.get(j);
            double otherBase = otherTerm.getBase();
            int otherPower = otherTerm.getPower();

            if (thisPower > otherPower)
            {
                combinedBases.add(thisBase);
                combinedPowers.add(thisPower);
                i++;
            }
            else if (thisPower < otherPower)
            {
                combinedBases.add(otherBase);
                combinedPowers.add(otherPower);
                j++;
            }
            else //thisPower == otherPower
            {
                double basesSum = thisBase + otherBase;
                if (basesSum != 0) {
                    combinedBases.add(basesSum);
                    combinedPowers.add(thisPower);
                }
                i++;
                j++;
            }
        }

        while (i < this.polynom.size())
        {
            Term restTerm = this.polynom.get(i);
            combinedBases.add(restTerm.getBase());
            combinedPowers.add(restTerm.getPower());
            i++;
        }

        while (j < other.polynom.size())
        {
            Term restTerm = other.polynom.get(j);
            combinedBases.add(restTerm.getBase());
            combinedPowers.add(restTerm.getPower());
            j++;
        }

        double[] bases = new double[combinedBases.size()];
        int[] powers = new int[combinedPowers.size()];

        for (int k = 0; k < combinedBases.size(); k++)
        {
            bases[k] = combinedBases.get(k);
            powers[k] = combinedPowers.get(k);
        }

        Polynom p = new Polynom(bases, powers);
        return p;
    }

    /**
     * Gets the result of the subtraction of other polynom from our polynom.
     * This methods relies on plus method and on the identity A-B = A+(-B)
     */
    public Polynom minus(Polynom other) throws Exception
    {
        int otherSize = other.polynom.size();
        double[] negatedBases = new double[otherSize];
        int[] powers = new int[otherSize];

        for (int i = 0; i < otherSize ; i++)
        {
            Term otherTerm = other.polynom.get(i);
            negatedBases[i] = -(otherTerm.getBase());
            powers[i] = otherTerm.getPower();
        }

        Polynom otherNegated = new Polynom(negatedBases, powers);

        return this.plus(otherNegated);
    }


    /**
     * Gets the derivative of our polynom object.
     */
    public Polynom derivative() throws Exception
    {
        ArrayList<Double> derivativeBases = new ArrayList<>();
        ArrayList<Integer> derivativePowers = new ArrayList<>();

        for (int i = 0; i < this.polynom.size(); i++)
        {
            Term currentTerm = this.polynom.get(i);
            double base = currentTerm.getBase();
            int power = currentTerm.getPower();

            if (power > 0)
            {
                derivativeBases.add(base * power);
                derivativePowers.add(power - 1);
            }
        }

        if (derivativeBases.isEmpty())
        {
            Polynom p = new Polynom(new double[]{0.0}, new int[]{0});
            return p;
        }

        double[] bases = new double[derivativeBases.size()];
        int[] powers = new int[derivativePowers.size()];

        for (int j = 0; j < derivativeBases.size(); j++)
        {
            bases[j] = derivativeBases.get(j);
            powers[j] = derivativePowers.get(j);
        }

        Polynom p = new Polynom(bases, powers);
        return p;
    }

    /**
     * Checks if the two Polynoms objects are the same polynom.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Polynom))
            return false;

        Polynom other = (Polynom) obj;

        if (this.polynom.size() != other.polynom.size())
            return false;

        for (int i = 0; i < this.polynom.size(); i++)
        {
            Term thisTerm = this.polynom.get(i);
            double thisBase = thisTerm.getBase();
            int thisPower = thisTerm.getPower();

            Term otherTerm = other.polynom.get(i);
            double otherBase = otherTerm.getBase();
            int otherPower = otherTerm.getPower();

            if (thisBase != otherBase || thisPower != otherPower)
                return false;
        }

        return true;
    }
}