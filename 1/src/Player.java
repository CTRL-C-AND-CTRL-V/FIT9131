import java.util.Scanner;
/**
 * A class to generate player
 */
public class Player {
    private String name;
    private int guess;
    private int score;

    public Player()
    {
        name = "default";
        guess = 0;
        score = 0;
    }

    public Player(String name)
    {
        this.name = name;
        guess = 0;
        score = 0;
    }

    /**
     * Method that sum up the score by given the new score
     * @param score An integer value representing new score made in the current round
     */
    public void addScore(int score)
    {
        this.score += score;
    }

    public String getName() {
        return name;
    }

    public int getGuess() {
        return guess;
    }

    public int getScore() {
        return score;
    }

    /**
     * A method that ask user input and validate
     */
    public void getUserGuess()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your guess: ");
        while (true)
        {
            String userInput = console.nextLine();
            // determine if user input is a number
            try
            {
                // try statement to ensure wrong type won't raise an error and break the loop
                guess = Integer.parseInt(userInput);
                if (guess <=100 && guess >=1 )
                {
                    break;
                }
                else if (guess == 999)
                {
                    break;
                }
                else
                {
                    System.out.println("Please enter number between 1 and 100(inclusive), or 999 for abandon");
                }
            }
            // if user input is not a integer
            catch (Exception e)
            {
                System.out.println("Please enter an integer between 1 and 100(inclusive)");
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
