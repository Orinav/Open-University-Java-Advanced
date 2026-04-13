import java.util.ArrayList;

/**
 * @author Ori Nave
 * @version 10.04.26
 * This class represents Polynom.
 */
public class Polynom
{
    private ArrayList<double[]> polynom;

    /**
     * Polynom constructor.
     */
    public Polynom(double[] bases, int[] powers) throws Exception
    {
        if (bases.length != powers.length)
            throw new Exception("bases array length must be equal to powers array length");

        polynom = new ArrayList<double[]>();

        for (int i = 0; i < bases.length; i++)
        {
            double[] element = new double[2];
            element[0] = bases[i];
            element[1] = powers[i];
            polynom.add(element);
        }

        //Selection Sort (sorting by highest power)
        for (int i = 0; i < polynom.size() - 1; i++)
        {
            int biggestPowerIndex = i;

            for (int j = i + 1; j < polynom.size(); j++)
            {
                if (polynom.get(j)[1] > polynom.get(biggestPowerIndex)[1])
                    biggestPowerIndex = j;
            }

            if (biggestPowerIndex != i)
            {
                double[] temporary = polynom.get(i);
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
            double base = polynom.get(i)[0];
            int power = (int) polynom.get(i)[1];

            // ##### Base handler #####
            if (base == 0)
                continue;

            if (str.equals(""))
            {
                if (base == 1 && power > 0){}
                else if (base == -1 && power > 0)
                    str += "-";
                else
                    str += base;
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
            double thisBase = this.polynom.get(i)[0];
            int thisPower = (int) this.polynom.get(i)[1];

            double otherBase = other.polynom.get(j)[0];
            int otherPower = (int) other.polynom.get(j)[1];

            if (thisPower > otherPower)
            {
                combinedBases.add(thisBase);
                combinedPowers.add(thisPower);
                i++;
            } else if (thisPower < otherPower)
            {
                combinedBases.add(otherBase);
                combinedPowers.add(otherPower);
                j++;
            } else //thisPower == otherPower
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
            combinedBases.add(this.polynom.get(i)[0]);
            combinedPowers.add((int) this.polynom.get(i)[1]);
            i++;
        }

        while (j < other.polynom.size())
        {
            combinedBases.add(other.polynom.get(j)[0]);
            combinedPowers.add((int) other.polynom.get(j)[1]);
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
     */
    public Polynom minus(Polynom other) throws Exception
    {
        ArrayList<Double> combinedBases = new ArrayList<>();
        ArrayList<Integer> combinedPowers = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < this.polynom.size() && j < other.polynom.size())
        {
            double thisBase = this.polynom.get(i)[0];
            int thisPower = (int) this.polynom.get(i)[1];

            double otherBase = other.polynom.get(j)[0];
            int otherPower = (int) other.polynom.get(j)[1];

            if (thisPower > otherPower)
            {
                combinedBases.add(thisBase);
                combinedPowers.add(thisPower);
                i++;
            } else if (thisPower < otherPower)
            {
                combinedBases.add(-otherBase);
                combinedPowers.add(otherPower);
                j++;
            } else //thisPower == otherPower
            {
                double basesSub = thisBase - otherBase;
                if (basesSub != 0) {
                    combinedBases.add(basesSub);
                    combinedPowers.add(thisPower);
                }
                i++;
                j++;
            }
        }

        while (i < this.polynom.size())
        {
            combinedBases.add(this.polynom.get(i)[0]);
            combinedPowers.add((int) this.polynom.get(i)[1]);
            i++;
        }

        while (j < other.polynom.size())
        {
            combinedBases.add(-other.polynom.get(j)[0]);
            combinedPowers.add((int) other.polynom.get(j)[1]);
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
     * Gets the derivative of our polynom object.
     */
    public Polynom derivative() throws Exception
    {
        ArrayList<Double> derivativeBases = new ArrayList<>();
        ArrayList<Integer> derivativePowers = new ArrayList<>();

        for (int i = 0; i < this.polynom.size(); i++)
        {
            double base = this.polynom.get(i)[0];
            int power = (int) this.polynom.get(i)[1];

            if (power > 0) {
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
            double thisBase = this.polynom.get(i)[0];
            double otherBase = other.polynom.get(i)[0];

            int thisPower = (int) this.polynom.get(i)[1];
            int otherPower = (int) other.polynom.get(i)[1];

            if (thisBase != otherBase || thisPower != otherPower)
                return false;
        }

        return true;
    }
}