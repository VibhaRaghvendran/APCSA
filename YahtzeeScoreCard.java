
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

	public int getScore(int index) {
		return scores[index - 1];
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
		
		int count = 0;
		for (int i = 0; i < dg.getDieArray().length; i++) {
			if (dg.getDiceValue(i) == choice) {
				count++;
			}
		}
		
		if (count == 0) {
			return false;
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
		if (changeScore(choice, dg) == true) {
			//put in score
		}
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
		
		for (int i = 0; i < numCount.length; i++) {
			if (numCount[i] >= 3) {
				// valid for 3 of a kind
			}
		}
		
		// else not valid
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
		
		for (int i = 0; i < numCount.length; i++) {
			if (numCount[i] >= 4) {
				// valid for 3 of a kind
			}
		}
		
		// else not valid
	}

	public void fullHouse(DiceGroup dg) {}

	public void smallStraight(DiceGroup dg) {}

	public void largeStraight(DiceGroup dg) {}

	public void chance(DiceGroup dg) {}

	public void yahtzeeScore(DiceGroup dg) {}

}
