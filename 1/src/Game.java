import java.util.Scanner;
public class Game {
    private Player player1;
    private Player player2;
    public int answer;

    public Game()
    {
        player1 = new Player();
        player2 = new Player();
        answer = 0;
    }

    private int calculateScore(int round)
    {
        int score = 0;
        switch (round)
        {
            case 0:
                score = 18;
                break;
            case 1:
                score = 12;
                break;
            case 2:
                score = 8;
                break;
            case 3:
                score = 5;
                break;
            case 4:
                score = 3;
                break;
            case 5:
                score = 2;
                break;
        }
        return score;
    }

    private void determineGameWinner()
    {
        System.out.println("--------------------------------------------------------");
        System.out.println("                        Game Over");
        System.out.println("--------------------------------------------------------");
        System.out.println("You scored " + player1.getScore() + " points, computer scored " + player2.getScore() + " points");
        if(player1.getScore() > player2.getScore())
        {
            System.out.println("Congrats! You win the game");
        }
        else if(player2.getScore() > player1.getScore())
        {
            System.out.println("It's a pity you lost");
        }
        else
        {
            System.out.println("No winner, it is a tie");
        }
    }

    private void displayWelcome()
    {
        System.out.println("Welcome!!");
    }

    private void gamePlay() {
        for (int i = 0; i < 4; i++) // Four rounds
        {
            // Initialise value before each round
            int currentMax = 100;
            int currentMin = 1;
            generateAnswer();

            System.out.println("--------------------------------------------------------");
            System.out.println("                     Round "+ (i+1));
            System.out.println("--------------------------------------------------------");
            // a random number generator to generator random number within the appropriate range
            NumberGenerator computerGenerator = new NumberGenerator();
            //flag is a random number determining who goes first (0 or 1).
            int flagWhoStart = (int) Math.round(Math.random());
            //flag for if anyone guess the correct answer or any player abandon the guess
            boolean flagGameFinish = false;
            for (int j = 0; j < 6; j++) // Each round 6 guesses in total
            {
                if (flagWhoStart == 0) // player's round
                {
                    System.out.println("It's your turn. ");

                    player1.getUserGuess();

                    if (player1.getGuess() > answer && player1.getGuess() <= currentMax)
                    {
                        // - 1 is because next player can not guess the same number from the last guess
                        // Similar operation for following codes are both for the same reason
                        currentMax = player1.getGuess() - 1;
                        System.out.println("Your guess is higher than the answer");
                        System.out.println();
                    }
                    else if (player1.getGuess() < answer && player1.getGuess() >= currentMin)
                    {
                        currentMin = player1.getGuess() + 1;
                        System.out.println("Your guess is lower than the answer");
                        System.out.println();
                    }
                    else if (player1.getGuess() == answer)
                    {
                        System.out.println("You're correct!!");
                        player1.addScore(calculateScore(j));
                        System.out.println("You have scored " + calculateScore(j) + " points");
                        flagGameFinish = true;
                        break;
                    }
                    else if (player1.getGuess() == 999)
                    {
                        flagGameFinish = true;
                        player2.addScore(calculateScore(j));
                        System.out.println("Computer have scored " + calculateScore(j) + " points due to user's abandon guess");
                        break;
                    }
                    else // player has entered the invalid number
                    {
                        System.out.println("Please keep in mind the current appropriate range");
                        System.out.println("You wasted a guess");
                        System.out.println();
                    }
                    flagWhoStart = 1; // switch turn

                }
                else // computer's round
                {
                    System.out.println("It's computer's turn");
                    int abandonRate = 20;
                    NumberGenerator abandonGenerator = new NumberGenerator(1, abandonRate);
                    if (abandonGenerator.getOutput() == j) // if computer decided to abandon
                    {
                        flagGameFinish = true;
                        player1.addScore(calculateScore(j));
                        System.out.println("You have scored " + calculateScore(j) + " points due to user's abandon guess");
                        break;
                    }
                    else
                    {
                        computerGenerator.Generate(currentMin, currentMax);
                        player2.setGuess(computerGenerator.getOutput());
                        System.out.println("Computer guess: " + computerGenerator.getOutput());
                        if (computerGenerator.getOutput()> answer)
                        {
                            currentMax = computerGenerator.getOutput() - 1;
                            System.out.println("Computer's guess is higher than the answer");
                            System.out.println();
                        }
                        else if (computerGenerator.getOutput() < answer)
                        {
                            currentMin = computerGenerator.getOutput() + 1;
                            System.out.println("Computer's guess is lower than the answer");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("Computer is correct");
                            player2.addScore(calculateScore(j));
                            System.out.println("Computer have scored " + calculateScore(j) + " points");
                            flagGameFinish = true;
                            break;
                        }
                        flagWhoStart = 0; // switch turn
                    }
                }

            }
            if (!flagGameFinish) // if guesses run out
            {
                System.out.println("This round is over, the answer is " + answer);
                System.out.println("Player last guess is " + player1.getGuess()+
                        " , and computer last guess is " + player2.getGuess());
                if (Math.abs(player1.getGuess() - answer) > Math.abs(player2.getGuess() - answer))
                {
                    player2.addScore(1);
                    System.out.println("Computer's guess is closer to the answer, 1 point awarded");
                }
                else if (Math.abs(player1.getGuess() - answer) < Math.abs(player2.getGuess() - answer))
                {
                    player1.addScore(1);
                    System.out.println("Your guess is closer to the answer, 1 point awarded");
                }
                else
                {
                    System.out.println("Two players have equidistant guess from the answer, no point for both players");
                }
            }
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getAnswer() {
        return answer;
    }

    private void generateAnswer()
    {
        NumberGenerator answerGenerator = new NumberGenerator();
        answer = answerGenerator.getOutput();
    }

    public static void main(String[] args)
    {
        Game myGame = new Game();

        myGame.displayWelcome();

        myGame.setUpUser();

        myGame.gamePlay();

        myGame.determineGameWinner();
    }

    private void setUpUser()
    {
        System.out.println("Please enter your name. ");
        while (true)
        {
            Scanner console = new Scanner(System.in);
            String userName = console.nextLine();
            if (userName.length() < 8)
            {
                player1 = new Player(userName);
                player2 = new Player("Computer");
                break;
            }
            else
            {
                System.out.println("Name must be less than 8 characters, try again");
            }
        }
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
