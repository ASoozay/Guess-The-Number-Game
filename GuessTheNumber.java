// Andrew Sousa
// 6/27/24
// CS 145
// Lab 1 Programming Assignment: Guess the Number
// File name: GuessTheNumber.java

// GuessTheNumber.java is a number guessing game. Each round, a target number is randomly chosen between 1 and 1000 (inclusive), which the player has to guess.
// The player will make a guess, and the game will tell the player whether their guess is too high or too low, depending on if their guess is higher or lower than the target.
// The player will guess until they correctly guess the target number, after which the game will congratulate them and tell them how many guesses it took.
// The player has the option to keep playing by typing any word beginning with "y" (case-insensitve), or can stop playing by typing any word beginning with "n" (case-insensitive).
// When the player is done playing, the program will print some summary stats, including total rounds, total guesses, and average guesses per round.

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.*;

public class GuessTheNumber {
    
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        int rounds = 0;
        int totalGuesses = 0;
        String play = "yes";

        intro();

        while(!play.substring(0,1).equalsIgnoreCase("n")){
            System.out.println();
            int roundGuesses = round(console);
            totalGuesses = totalGuesses + roundGuesses;
            rounds++;

            System.out.println();
            System.out.println("Would you like to play again?");
            System.out.print("Type \"yes\" to play another round, or \"no\" to quit: ");
            play = console.nextLine();
        }

        System.out.println();
        summary(rounds, totalGuesses);
    }


    // method: intro (void)
    // purpose: prints out the game introduction and tells the player how to play
    // parameters: none
    public static void intro(){
        System.out.println();
        System.out.println("Welcome to Guess The Number!");
        System.out.println();
        System.out.println("How to Play");
        System.out.println("Each round, a number between 1 and 1000 will be randomly chosen.");
        System.out.println("Every time you guess a number, you will be told whether your guess is too high or too low");
        System.out.println("Keep guessing until you guess the number. Let's begin!");
    }


    // method: round (int)
    // purpose: simulates a round of the game. returns the number of guesses it took the player to guess the target number
    // parameters:  (1) Scanner console : Scanner that takes in the player's guesses.
    public static int round(Scanner console){
        Random rand = new Random();
        int target = rand.nextInt(1000) + 1;
        int numGuesses = 1;
        int clueRange = 1;

        System.out.print("Guess a number: ");
        int guess = console.nextInt();
        console.nextLine();

        while(guess != target){
            highOrLow(guess, target);
            numGuesses++;
            System.out.println();
            if(numGuesses % 5 == 0){
                clue(target, clueRange);
                clueRange++;
            }

            System.out.print("Guess a number: ");
            guess = console.nextInt();
            console.nextLine();
        }

        System.out.println("Congratulations. You guessed the number!");
        result(numGuesses);

        return numGuesses;
    }


    // method: highOrLow (void)
    // purpose: checks if the user's guess is higher or lower than the target number, and prints out a message accordingly.
    // parameters:  (1) int guess : the player's guess
    //              (2) int target: the target number that the player's guess is compared to
    public static void highOrLow(int guess, int target){
        if(guess < target){
            System.out.println(guess + " is too low. Try again");
        } else if (guess > target){
            System.out.println(guess + " is too high. Try again");
        }
    }

    
    // method: clue (void)
    // purpose: gives the player a clue of the target number's range every 5 guesses, narrowing the range up to 3 times.
    //          to prevent the user from always guessing the middle of the range, the length of the lower and higher ends is randomly selected within an interval (ex. target = 50, low end = 14, high end = 72)
    // parameters:  (1) int target: the target number that the range is created from
    //              (2) int clueRange: the level of range that the clue is. A higher clueRange means a smaller range. Increases every five guesses.
    public static void clue(int target, int clueRange){
        Random randRange = new Random();
        int min = 0;
        int max = 0;

        if(clueRange == 1){
            min = target - randRange.nextInt(21, 51);
            max = target + randRange.nextInt(21, 51);
        } else if (clueRange == 2){
            min = target - randRange.nextInt(6, 21);
            max = target + randRange.nextInt(6, 21);
        } else if (clueRange == 3){
            min = target - randRange.nextInt(1,6);
            max = target + randRange.nextInt(1,6);
        }

        if(min < 0){
            min = 0;
        }
        
        if(max > 1000){
            max = 1000;
        }

        System.out.println("Clue: The number is between " + min + " and " + max);
    }

    
    // method: result (void)
    // purpose: when the player wins the round, it prints out a message on how many guesses it took the player to guess the target number.
    //          if the player guesses the target number on their first try, it prints out a special message.
    // parameters:  (1) int numGuesses: the number of guesses it took the player to guess the target number.
    public static void result(int numGuesses){
        if(numGuesses == 1){
            System.out.println("You guessed the number on your first try. Way to go!");
        } else {
            System.out.println("You guessed the number in " + numGuesses + " tries. Nice job!");
        }
    }


    // method: summary (void)
    // purpose: when the player ends the game, prints out total rounds played, total number of guesses, and average guesses per round. Also thanks the player.
    // parameters:  (1) int rounds: the number of rounds the player played
    //              (2) int totalGuesses: the total number of guesses the player made throughout all rounds.
    public static void summary(int rounds, int totalGuesses){
        double gpr = totalGuesses*1.0 / rounds*1.0;

        System.out.println("Summary");
        System.out.println("Rounds Played: " + rounds);
        System.out.println("Total Guesses: " + totalGuesses);
        System.out.println("Average Guesses Per Round: " + gpr);
        System.out.println();
        System.out.println("Thanks for playing!");
    }
}
