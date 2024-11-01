import java.util.Scanner;

/**
 *	The game of Yahtzee.
 *
 *	There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her dice up to 3 times in order 
 *  to get the desired combination. On the first roll, the player rolls all five of the dice at once. On the 
 *  second and third rolls, the player can roll any number of dice he/she wants to, including none or all of them, 
 *  trying to get a good combination. The player can choose whether he/she wants to roll once, twice or three times
 *  in each turn. After the three rolls in a turn, the player must put his/her score down on the scorecard, under 
 *  any one of the thirteen categories. The score that the player finally gets for that turn depends on the 
 *  category/box that he/she chooses and the combination that he/she got by rolling the dice. But once a box is 
 *  chosen on the score card, it can't be chosen again.
 *
 *	@author	Vibha Raghvendran
 *	@since	October 31 2024
 */ 
public class Yahtzee {

	private String name1, name2, startingName, otherName; // names of players
	private int finalScore1, finalScore2, roundNum; // final scores of players, number of rounds
	private final int NUM_ROUNDS = 13; // total number of rounds
	Scanner scan; // scanner
	DiceGroup dg; // dice group for the game
	YahtzeePlayer player1, player2; // instance of yahtzee player for each player

	public static void main (String[] args) {
		Yahtzee game = new Yahtzee();
	}

	public Yahtzee () {
		scan = new Scanner (System.in);
		dg = new DiceGroup();
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		printHeader();
		initSetup();
		do {
			run1();
			run2();
		} while (roundNum < NUM_ROUNDS);
		end();
	}

	/**
	 *	Prints the introduction
	 */ 
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}

	/**
	 *	Prints the initial prompt for players and decides who is going first
	 */ 
	public void initSetup() {
		int tempScore1 = 0, tempScore2 = 0;

		// gets the names of the players
		System.out.println("Player 1, please enter your first name:");
		name1 = scan.nextLine();
		player1.setName(name1);
		System.out.println("Player 2, please enter your first name:");
		name2 = scan.nextLine();
		player2.setName(name2);

		// rolls the dice once for each player
		String pushed= "";		
		System.out.println("Let's see who will go first. " + name1 + ", Please press \"a\" to roll the dice.");
		pushed = scan.nextLine();
		if (pushed.equals("a")) {
			dg.rollDice();
			dg.printDice();
			tempScore1 = dg.getTotal();
		}
		System.out.println(name2 + ", it's your turn, Please press \"a\" to roll the dice.");
		pushed = scan.nextLine();
		if (pushed.equals("a")) {
			dg.rollDice();
			dg.printDice();
			tempScore2 = dg.getTotal();
		}

		// checks which score is greater
		if (tempScore1 >= tempScore2) {
			startingName = name1;
			otherName = name2;
		}
		else {
			startingName = name2;
			otherName = name1;
		}

		// starts with the greater score
		System.out.println(name1 + ", you rolled a sum of 16, and " + name2 + ", you rolled a sum of 17.");
		System.out.println(startingName + ", since your sum was higher, you'll roll first.");
		// prints out initial scorecard
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
	}

	/**
	 *	Runs the game for 13 rounds
	 */ 
	public void run1 () {	
		String pushed = "";
		String rawHold = "";			
		System.out.println();
		System.out.println("Round " + (roundNum + 1) + " of 13"); // generates round message
		System.out.println();
		System.out.println(startingName + ", it's your turn to play. Please press \"a\" to roll the dice.");
		pushed = scan.nextLine();
		if (pushed.equals("a")) {
			dg.rollDice();
			dg.printDice();
			System.out.println();

			int numReroll = 0;
			while (numReroll < 1) { // allows opportunity to roll twice
				System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without \r\n"
						+ "spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125 "
						+ "(enter -1 if you'd like to end the turn)");
				rawHold = scan.nextLine(); // scans which dice to hold
				if (rawHold.equals("-1")) { // exits the loop if there are no dice to hold
					break;
				}					
				dg.rollDice(rawHold); // rolls the dice that aren't held
				dg.printDice(); // prints the dice
				System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without \r\n"
						+ "spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125 "
						+ "(enter -1 if you'd like to end the turn)");
				rawHold = scan.nextLine(); // prompts user again
				dg.rollDice(rawHold);
				dg.printDice();

				numReroll++; // increments number of rerolls 
			}
			// prints the score card
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);
			System.out.println();
			// prompts the user to enter a choice
			System.out.println(startingName + ", now you need to make a choice. pick a valid integer from the list above (1 - 13):");
			int choice = scan.nextInt();
			// checks the choice
			while (choice > 13) {
				System.out.println("Invalid input. Try again.");
				choice = scan.nextInt();
			}
			// inputs corresponding scores into the score card
			if (startingName.equals(name1)) {
				player1.getScoreCard().changeScore(choice, dg);
				finalScore1 += dg.getTotal();
			}
			else {
				player2.getScoreCard().changeScore(choice, dg);
				finalScore1 += dg.getTotal();
			}
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);
		}

	}

	public void run2 () {
		String pushed = "";
		String rawHold = "";
		// same process for player 2
		System.out.println();
		System.out.println(otherName + ", it's your turn to play. Please press \"a\" to roll the dice.");
		pushed = scan.nextLine();
		if (pushed.equals("a")) {
			dg.rollDice();
			dg.printDice();
			System.out.println();

			int numReroll = 0;
			while (numReroll < 1) {
				System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without \r\n"
						+ "spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125 \r\n"
						+ "(enter -1 if you'd like to end the turn)");
				rawHold = scan.nextLine();
				if (rawHold.equals("-1")) {
					break;
				}
				dg.rollDice(rawHold);
				dg.printDice();
				System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without \r\n"
						+ "spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125 \r\n"
						+ "(enter -1 if you'd like to end the turn)");
				rawHold = scan.nextLine();
				dg.rollDice(rawHold);
				dg.printDice();

				numReroll++;
			}

			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);	
			System.out.println();
			System.out.println(name2 + ", now you need to make a choice. pick a valid integer from the list above (1 - 13):");
			int choice = scan.nextInt();
			while (choice > 13) {
				System.out.println("Invalid input. Try again.");
				choice = scan.nextInt();
			}
			if (otherName.equals(name1)) {
				player1.getScoreCard().changeScore(choice, dg);
				finalScore1 += dg.getTotal();
			}
			else {
				player2.getScoreCard().changeScore(choice, dg);
				finalScore1 += dg.getTotal();
			};
			player2.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);			
		}

		roundNum++;
	}


/**
 *	Prints end message
 */ 
public void end () {
	System.out.println(name1 + "               score total : " + finalScore1);
	System.out.println(name2 + "               score total : " + finalScore2);

	// determines who has a greater score and prints the corresponding message 
	if (finalScore1 > finalScore2) {
		System.out.println("Congratulations, " + name1 + "! You WON!");
	}
	else if (finalScore1 < finalScore2) {
		System.out.println("Congratulations, " + name2 + "! You WON!");
	}
	else {
		System.out.println("You TIED.");
	}
}
}
