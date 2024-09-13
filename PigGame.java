import java.util.Scanner;

/**
 *	The game of Pig.
 *	The player and the computer roll the die and the first one to get to 100 points wins.
 *
 *	@author Vibha Raghvendran	
 *	@since	September 9 2024
 */
public class PigGame {
	int tCS = 0;
	int tPS = 0;
	int turns = 0;
	public PigGame () {
		printIntroduction();
		Scanner scan = new Scanner(System.in);
		if (turns % 2 == 0) {
			playerTurn();
		}
		else {
			compTurn();
		}
		turns++;
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
	
	public int playerTurn() {
		int cPS = Dice.getValue();
		if (cPS != 1) {
			tPS += cPS;
		}
	}
	
	public int compTurn() {
		int cCS = Dice.getValue();
		if (cCs != 1) {
			tCS += cCS;
		}
	}
	
	public static void main (String[] args) {
		PigGame game = new PigGame();
	}
}
