import java.util.Random;
/**
 * A class to generate random number by default, or by given range
 */
public class NumberGenerator
{
    private int min, max, output;

    public NumberGenerator()
    {
        // default value 1 to 100 inclusive
        min = 1;
        max = 100;
        Random r = new Random();
        output = r.nextInt(100) + 1;
    }

    public NumberGenerator(int min, int max)
    {
        // Validation for min and max field
        if (min > 0 && max > 0 && max >= min)
        {
            this.min = min;
            this.max = max;
            Random r = new Random();
            output = r.nextInt(max + 1 - min) + min;
        }
        else
        {
            System.out.println("Invalid values for NumberGenerator");
        }
    }

    public void DisplayNumber()
    {
        System.out.println("Minimal value: "+ min + " Maximum value: " + max + " Random number: " + output);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getOutput() {
        return output;
    }

    /**
     * Method that generates random number by given range
     * @param min An integer gives lower bound for the random number
     * @param max An integer gives upper bound for the random number
    */
    public int Generate(int min, int max)
    {
        Random r = new Random();
        output = r.nextInt(max + 1 - min) + min;
        // update current max and min
        this.max = max;
        this.min = min;
        return output;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
