
import java.util.*;

public class GuessTheNumber {
    private static int rnumber = 1;
    public static int playRound() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        int userGuess;
        int minRange = 1;
        int maxRange = 100;
        int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        int maxAttempts = 10; // Limiting attempts
        int attempts = 0;
        boolean guessedCorrectly = false;
        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        System.out.println("\n------ Round "+ rnumber + "------");
        System.out.println("I have generated a number between " + minRange + " and " + maxRange + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");

        while (attempts < maxAttempts) {
            System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ". Enter your guess: ");
            
            
            userGuess = sc.nextInt();
            

            attempts++;

            if (userGuess == generatedNumber) {
                System.out.println("Congratulations! You guessed the number " + generatedNumber + " correctly!");
                System.out.println("It took you " + attempts + " attempts this round.");
                guessedCorrectly = true;
                break;
            } else if (userGuess < generatedNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }
        rnumber=rnumber+1;

        if (!guessedCorrectly) {
            System.out.println("Sorry, you ran out of attempts! The number was " + generatedNumber + ".");
            return 0; // Lost the round
        } else {
            return 1; // Won the round
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalRoundsWon = 0;
        boolean playAgain = true;
        System.out.println("_________________________________________");

        System.out.println("\n Welcome to the Guess the Number Game ");
        System.out.println("_________________________________________");

        while (playAgain) {
            totalRoundsWon += playRound();
            System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgainInput = sc.next().toLowerCase();
            if(playAgainInput.equals("yes")){
                playAgain=true;
            }
            else{
                playAgain=false;
            }
        }

        System.out.println("\n----------Game Over----------");
        System.out.println("You won a total of " + totalRoundsWon + " rounds!");
        System.out.println("Thanks for playing!");
        sc.close();
    }

    
}
