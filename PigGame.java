import java.util.Scanner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *	The game of Pig.
 *	The player and the computer roll the die and the first one to get to 100 points wins.
 *
 *	@author Vibha Raghvendran	
 *	@since	September 9 2024
 */
public class PigGame {
	int totalCompScore = 0; // computer's total score
	int totalPlyScore = 0; // player's total score
	int runCompScore = 0; // score per turn (for computer)
	int runPlyScore = 0; // score per turn (for player)
	Scanner scan = new Scanner(System.in); // creates a class-wide scanner
	boolean isPlayerTurn = true; // sees whose turn it is (equals true if its the player's turn, equals false if its the computer's turn
	Dice die = new Dice();

	/** Constructor checks which mode (statistics or game) 
	 * 
	 * 	if game (g) - run gameRun()
	 * 	if stats (s) - run stats() 
	 */
	public PigGame () {
		printIntroduction();
		if (mode().equals("p")) {
			// prints intial prompts
			System.out.println("Your turn score: " + runPlyScore); 
			System.out.println("Your total score: " + totalPlyScore);
			System.out.println();
			// calls method to run the game
			gameRun();
		}
		else {
			// calls method to run statistics simulation
			stats();
		}
	}

	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
				+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
				+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
				+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
				+ "turn goes to other player");
		System.out.println("\n");
	}

	/** 
	 * 	Asks which mode the user wants, checks if the input is valid in a while loop, returns the answer (game or stats)
	 * 	
	 * 	@return String answer
	 */
	public String mode () {
		System.out.println("Play game(p) or statistics(s)?");
		String answer = scan.nextLine();
		boolean found = false;
		while (!found) {
			if (answer.equalsIgnoreCase("s")) {
				found = true;
			}		
			else if (answer.equalsIgnoreCase("p")) {
				found = true;
			}
			else {
				found = false;
				System.out.println("Not valid. Try again");
				answer = scan.nextLine();
			}
		}

		return answer;
	}

	/** 
	 * 	Asks if the user wants to roll or hold, checks if the input is valid in a while loop, returns the answer (roll or hold)
	 * 	
	 * 	@return String answer
	 */
	public String chooser () {		
		String answer = "";
		System.out.println("Roll(r) or hold(h)?");
		answer = scan.nextLine();
		boolean found = false;
		while (!found) {
			if (answer.equalsIgnoreCase("r")) {
				found = true;
			}		
			else if (answer.equalsIgnoreCase("h")) {
				found = true;
			}
			else {
				found = false;
				System.out.println("Not valid. Try again");
				answer = scan.nextLine();
			}
		}

		return answer;
	}

	/** 
	 * 	Returns if "enter" is pressed
	 */
	
	
	/** 
	 * 	Plays the actual game: 
	 * 	Runs the game until the user or the computer reaches 100 points using a while loop
	 */
	public void gameRun () {
		int diceValue = 0;
		while ((totalCompScore + runCompScore) < 100 && (totalPlyScore + runPlyScore) < 100) {
			if (isPlayerTurn) { 
				if (chooser().equals("h")) {
					isPlayerTurn = !isPlayerTurn; // if hold - switch over to the opponent's turn (true becomes false, false becomes true)
					System.out.println("Your total score: " + totalPlyScore); 
					continue;
				}

			}

			diceValue = die.roll(); // diceValue is the value of the dice rolled
			if (isPlayerTurn) {	// checks whose turn it is to see if the turn has to be held at 20
				if (diceValue == 1) {
					isPlayerTurn = !isPlayerTurn; // if diceValue is 1, switch to the opponent's turn
					totalPlyScore += runPlyScore; // add on the the total score (player)
					System.out.println();
					System.out.println("You have rolled a 1. The computer will play now.");
					System.out.println();
					System.out.println("Your total score: " + totalPlyScore);
					System.out.println();
					System.out.println("**** COMPUTER Turn ****"); // prints out the opponent's turn (computer)
					System.out.println();
					System.out.println("Computer's turn score: " + runCompScore);
					System.out.println("Computer's total score: " + totalCompScore);
					System.out.println();
					runPlyScore = 0;
				}
				else {
					System.out.println("You ROLL");
					die.printDice(); // print out the die with the corresponding value
					runPlyScore += diceValue; // adds on to the turn score (player)
					System.out.println("Your turn score: " + runPlyScore);
					System.out.println("Your total score: " + totalPlyScore);
					System.out.println();
				}

			}
			else { // else - must be the computer's turn
				System.out.println("Press \"a\" for computer's turn:"); // user can press to progress the computer's turn
				String a = scan.nextLine();
				boolean isFound = false;
				
				while (!isFound) {
					if (a.equals("a")) {
						isFound = true;
					}
				}

				if (diceValue == 1) {
					isPlayerTurn = !isPlayerTurn; // if diceValue is 1, switch to the opponent's turn
					totalCompScore += runCompScore; // add on the the total score (computer)
					System.out.println();
					System.out.println("The computer has rolled a 1. You will play now.");
					System.out.println();
					System.out.println("Computer's total score: " + totalCompScore);
					System.out.println();
					System.out.println("**** USER Turn ****"); // prints out the opponent's turn (user)
					System.out.println();
					System.out.println("Your turn score: " + runPlyScore);
					System.out.println("Your total score: " + totalPlyScore);
					System.out.println();
					runCompScore = 0;
				}
				else {
					System.out.println("Computer will ROLL");
					die.printDice(); // print out the die with the corresponding value
					runCompScore += diceValue; // adds on to the turn score (computer)
					System.out.println("Computer's turn score: " + runCompScore);
					System.out.println("Computer's total score: " + totalCompScore);

					if (runCompScore >= 20) { // when the turn score reaches 20, hold
						System.out.println();
						System.out.println("Computer will HOLD");
						System.out.println();
						totalCompScore += runCompScore; // add on the the total score (computer)
						System.out.println("Computer's total score: " + totalCompScore);
						System.out.println();
						System.out.println("**** USER Turn ****"); // prints out the opponent's turn (user)
						System.out.println();
						System.out.println("Your turn score: " + runPlyScore);
						System.out.println("Your total score: " + totalPlyScore);
						System.out.println();
						isPlayerTurn = !isPlayerTurn; // switch to player's turn
						runCompScore = 0; // resets turn score
					}
				}
			}

		}

		winningMessage(); // once someone reaches 100, print the winning message
	}

	/** 
	 * 	Generates the winning message:
	 * 	Cases on whether the computer or the player won based on whose score is greater
	 */
	public void winningMessage() {
		
		if ((totalPlyScore + runPlyScore) > (totalCompScore + runCompScore)) {
			totalPlyScore += runPlyScore;
			
			System.out.println("Congratulations! You won!");
			System.out.println("Your total score is: " + totalPlyScore);
		}
		else {
			System.out.println("Aw, you lost. Better luck next time!");
		}
		System.out.println();
		System.out.println("Thanks for playing the Pig Game!");
	}

	/** 
	 * 	Calculates the probabilities of each case (0, 20, 21, etc.)
	 */
	public void stats () {
		Scanner scan = new Scanner (System.in);
		int iterations;
		boolean found = false;
		System.out.println("Run statistical analysis - \"Hold at 20\"");
		System.out.println();		
		System.out.println("Number of turns (1000 - 1000000): ");
		iterations = scan.nextInt(); // asks user input for how many times the game should be simulated

		while (!found) { // checks for valid input
			if (iterations <= 1000000 && iterations >= 1000) {
				found = true;
			}
			else {
				found = false;
				System.out.println("Not valid. Try again");
				iterations = scan.nextInt();
			}
		}

		int[] counts = new int[7]; // stores values for how many times each point value occurs

		for (int i = 0; i < iterations; i++) {
			int returnValue = processOneTurn();
			// counts how many times each value is counted - each time a value is counted, the corresponding array element is incremented
			if (returnValue == 0) {
				counts[0]++;
			}
			else {
				counts[returnValue - 19]++;
			}
		}
		
		// formats everything into a table
		System.out.println("        Estimated\r\n"
				+ "Score   Probability");
		for (int i = 0; i < counts.length; i++) {
			double probability = (double) counts[i]/iterations; // finds the probability by doing times detected/total iterations
			if (i == 0) {
				System.out.println(" 0        " + probability);
			}
			else {
				int num = 19 + i;
				System.out.println(num + "        " + probability);
			}
		}
	}
	
	/** 
	 * 	Runs one iteration (up to 20 points)
	 */
	public int processOneTurn () {
		runCompScore = 0;

		while (runCompScore < 20) {
			int diceValue = die.roll();
			if (diceValue == 1) {
				return 0;
			}
			runCompScore += diceValue;
		}	

		return runCompScore;
	}

	public static void main (String[] args) {
		PigGame game = new PigGame();
	}
}
