
/**
 *	Calculates the scores for each type of case
 *
 *	@author	Vibha Raghvendran
 *	@since	October 31 2024
 */ 
public class YahtzeeScoreCard {
	private int[] scores = new int[13];
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
				"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
				"---------------------------+\n");
	}

	public YahtzeeScoreCard() {
		for (int i = 0; i < scores.length; i++) {
			scores[i] = -1;
		}
	}

	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
				"---------------------------+\n");
	}

	/**
	 *  Returns the 
	 */
	public int getScore(int index) {
		return scores[index - 1];
	}
	
	public int getScoreCardTotal () {
		int total = 0;
		for (int i = 0; i < 13; i++) {
			total += scores[i];
		}
		
		return total;
	}
	
	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (scores[choice] != -1) {
			return false;
		}

		if (choice >= 1 && choice <= 6) {
			numberScore(choice, dg);
		}
		else if (choice == 7) {
			threeOfAKind(dg);
		}
		else if (choice == 8) {
			fourOfAKind(dg);
		}
		else if (choice == 9) {
			fullHouse(dg);
		}
		else if (choice == 10) {
			smallStraight(dg);
		}
		else if (choice == 11) {
			largeStraight(dg);
		}
		else if (choice == 12) {
			chance(dg);
		}
		else {
			yahtzeeScore(dg);
		}

		return true;
	}

	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int count = 0;
		for (int i = 0; i < dg.getDieArray().length; i++) {
			if (dg.getDiceValue(i) == choice) {
				count++;
			}
		}

		int score = count*choice;
		scores[choice - 1] = score;	
	}

	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		int[] numCount = new int[6];
		for (int i = 0; i < dg.getDieArray().length; i++) {
			if (dg.getDiceValue(i) == 1) {
				numCount[0]++;
			}
			else if (dg.getDiceValue(i) == 2) {
				numCount[1]++;
			}
			else if (dg.getDiceValue(i) == 3) {
				numCount[2]++;
			}
			else if (dg.getDiceValue(i) == 4) {
				numCount[3]++;
			}
			else if (dg.getDiceValue(i) == 5) {
				numCount[4]++;
			}
			else {
				numCount[5]++;
			}		
		}

		boolean found = false;
		for (int i = 0; i < numCount.length; i++) {
			if (numCount[i] >= 3) {
				found = true;
			}
		}

		if (found == true) {
			scores[6] = dg.getTotal();
		}
		else {
			scores[6] = 0;
		}
	}

	public void fourOfAKind(DiceGroup dg) {
		int[] numCount = new int[6];
		for (int i = 0; i < dg.getDieArray().length; i++) {
			if (dg.getDiceValue(i) == 1) {
				numCount[0]++;
			}
			else if (dg.getDiceValue(i) == 2) {
				numCount[1]++;
			}
			else if (dg.getDiceValue(i) == 3) {
				numCount[2]++;
			}
			else if (dg.getDiceValue(i) == 4) {
				numCount[3]++;
			}
			else if (dg.getDiceValue(i) == 5) {
				numCount[4]++;
			}
			else {
				numCount[5]++;
			}		
		}

		boolean found = false;
		for (int i = 0; i < numCount.length; i++) {
			if (numCount[i] >= 4) {
				found = true;
			}
		}

		if (found == true) {
			scores[7] = dg.getTotal();
		}
		else {
			scores[7] = 0;
		}
	}

	public void fullHouse(DiceGroup dg) {
		int[] numCount = new int[6];
		for (int i = 0; i < dg.getDieArray().length; i++) {
			if (dg.getDiceValue(i) == 1) {
				numCount[0]++;
			}
			else if (dg.getDiceValue(i) == 2) {
				numCount[1]++;
			}
			else if (dg.getDiceValue(i) == 3) {
				numCount[2]++;
			}
			else if (dg.getDiceValue(i) == 4) {
				numCount[3]++;
			}
			else if (dg.getDiceValue(i) == 5) {
				numCount[4]++;
			}
			else {
				numCount[5]++;
			}		
		}

		boolean found = false;
		for (int i = 0; i < 6; i++) {
			for (int j = i; j < 6; j++) {
				if (numCount[i] == 3 && numCount[j] == 2 || numCount[i] == 2 && numCount[j] == 3) {
					found = true;
				}
			}
		}

		if (found == true) {
			scores[8] = 25;
		}
		else {
			scores[8] = 0;
		}
	}

	public void smallStraight(DiceGroup dg) {
		for (int i = 0; i < scores.length; i++) {
			scores[i] = dg.getDiceValue(i);
		}

		scores = sortArray(scores);

		boolean found = false;
		for (int i = 0; i < scores.length - 2; i++) {
			if (scores[i] == scores[i+1] - 1 && scores[i+1] == scores[i+2] - 1) {
				found = true;
			}
		}

		if (found == true) {
			scores[9] = 30;
		}
		else {
			scores[9] = 0;
		}
	}	

	public void largeStraight(DiceGroup dg) {
		for (int i = 0; i < scores.length; i++) {
			scores[i] = dg.getDiceValue(i);
		}

		scores = sortArray(scores);

		boolean found = false;
		for (int i = 0; i < scores.length - 3; i++) {
			if (scores[i] == scores[i+1] - 1 && scores[i+1] == scores[i+2] - 1 && scores[i+2] == scores[i+3] - 1) {
				found = true;
			}
		}

		if (found == true) {
			scores[10] = 40;
		}
		else {
			scores[10] = 0;
		}
	}

	public int[] sortArray (int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {  
			int index = i;  
			for (int j = i + 1; j < numbers.length; j++){  
				if (numbers[j] < numbers[index]){  
					index = j;  
				}  
			} 

			int smallerNumber = numbers[index];   
			numbers[index] = numbers[i];  
			numbers[i] = smallerNumber;  

			for (int a = 0; a < numbers.length; a++) {
				System.out.print(numbers[a] + " ");
			}
			System.out.println();

		}
		
		return numbers;
	}

	public void chance(DiceGroup dg) {
		int total = dg.getTotal();
		scores[11] = total;
	}

	public void yahtzeeScore(DiceGroup dg) {
		boolean found = false; 
		if (dg.getDiceValue(0) == dg.getDiceValue(1) && dg.getDiceValue(1) == dg.getDiceValue(2) && 
				dg.getDiceValue(2) == dg.getDiceValue(3) && dg.getDiceValue(3) == dg.getDiceValue(4)) {
			found = true;
			scores[12] = 50;
		}
		else {
			scores[12] = 0;
		}
	}

}
