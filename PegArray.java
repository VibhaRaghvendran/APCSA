/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author
 *  @since
 */

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;

	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) {

	}

	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { 

		return pegs[n]; 
	}

	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) { 
		for (int i = 0; i < pegs.length; i++) {
			if (pegs[i].getLetter() == master.getPeg(i).getLetter()) { // checks if the letters at the same index are the same
				exactMatches++;
			}
		}

		return exactMatches;
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	
	public int getPartialMatches(PegArray master) { 
		for (int i = 0; i < pegs.length; i++) {
			for (int j = 0; j < pegs.length; j++) {
				int indexI = 0;
				int indexJ = 0;
				if (master.getPeg(i).getLetter() == pegs[j].getLetter() && i != j) {
					partialMatches++;
					indexI = i;
					indexJ = j;
					if (master.getPeg(indexI).getLetter() == pegs[indexI].getLetter()) {
						partialMatches--;
					}
					else if (master.getPeg(indexJ).getLetter() == pegs[indexJ].getLetter()) {
						partialMatches--;
					}					
					else {
						pegs[indexJ].setLetter('G');
						break;
					}
				}
					
			}
		}

		return partialMatches;
	}

	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { 
		return exactMatches; 

	}
	public int getPartial() { 
		return partialMatches; 

	}

}
