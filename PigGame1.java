import java.util.Scanner;

/**
 *	The game of Pig.
 *	The player and the computer roll the die and the first one to get to 100 points wins.
 *
 *	@author Vibha Raghvendran	
 *	@since	September 9 2024
 */
public class PigGame1 {
	int totalCompScore = 0;
	int totalPlyScore = 0;
	int runCompScore = 0;
	boolean isPlayerTurn = true;
	Dice die = new Dice();

	public PigGame1 () {
		printIntroduction();
		if (mode().equals("g")) {
			gameRun();
		}
		else {
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

	// chooses modes
	public String mode () {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want stats(s) or game(g)?");
		String answer = scan.nextLine();
		boolean found = false;
		while (!found) {
			if (answer.equalsIgnoreCase("s")) {
				found = true;
			}		
			else if (answer.equalsIgnoreCase("g")) {
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

	// chooses roll or hold
	public String chooser () {
		Scanner scan = new Scanner(System.in);
		String answer = "";
		System.out.println("Do you want to roll(r) or hold(h)?");
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


	public void gameRun () {
		int diceValue = 0;
		while (totalCompScore < 100 && totalPlyScore < 100) {
			if (isPlayerTurn) {
				if (chooser().equals("h")) {
					isPlayerTurn = !isPlayerTurn;
					System.out.println("Your total score: " + totalPlyScore);
					continue;
				}

			}

			diceValue = die.roll();
			if (isPlayerTurn) {	
				if (diceValue == 1) {
					isPlayerTurn = !isPlayerTurn;
					System.out.println("Your total score: " + totalPlyScore);
					System.out.println();
					System.out.println("**** COMPUTER'S Turn ***");
				}
				else {
					System.out.println("You ROLL");
					die.printDice();
					totalPlyScore += diceValue;
					System.out.println("Your turn score: " + diceValue);
					System.out.println("Your total score: " + totalPlyScore);
				}

			}
			else {
				if (diceValue == 1) {
					isPlayerTurn = !isPlayerTurn;
					System.out.println();
					System.out.println("Computer's total score: " + totalCompScore);
					System.out.println();
					System.out.println("**** USER Turn ****");
					runCompScore = 0;
				}
				else {
					System.out.println("Computer will ROLL");
					die.printDice();
					totalCompScore += diceValue;
					runCompScore += diceValue;
					System.out.println("Computer's turn score: " + diceValue);
					System.out.println("Computer's total score: " + totalCompScore);

					if (runCompScore >= 20) {
						System.out.println();
						System.out.println("Computer will HOLD");
						System.out.println();
						System.out.println("Computer's total score: " + totalCompScore);
						isPlayerTurn = !isPlayerTurn;
						runCompScore = 0;
					}
				}
			}

		}

		winningMessage();
	}

	public void winningMessage() {
		String winner;
		if (totalPlyScore > totalCompScore) {
			winner = "You!";
		}
		else {
			winner = "The computer!";
		}
		System.out.println("Game over! The winner is: " + winner);
		System.out.println("Thank you for playing!");
	}

	public void stats () {
		Scanner scan = new Scanner (System.in);
		int iterations;
		boolean found = false;
		System.out.println("Run statistical analysis - \"Hold at 20\"");
		System.out.println();		
		System.out.println("Number of turns (1000 - 1000000): ");
		iterations = scan.nextInt();

		while (!found) {
			if (iterations <= 1000000 && iterations >= 1000) {
				found = true;
			}
			else {
				found = false;
				System.out.println("Not valid. Try again");
				iterations = scan.nextInt();
			}
		}

		int[] counts = new int[7];

		for (int i = 0; i < iterations; i++) {
			int returnValue = processOneTurn();
			if (returnValue == 0) {
				counts[0]++;
			}
			else {
				counts[returnValue - 19]++;
			}
		}
		
		System.out.println("        Estimated\r\n"
				+ "Score   Probability");
		for (int i = 0; i < counts.length; i++) {
			double probability = (double) counts[i]/iterations;
			if (i == 0) {
				System.out.println(" 0        " + probability);
			}
			else {
				int num = 19 + i;
				System.out.println(num + "        " + probability);
			}
		}
	}

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
		PigGame1 game = new PigGame1();
	}
}
