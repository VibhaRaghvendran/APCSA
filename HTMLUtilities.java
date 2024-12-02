import java.util.ArrayList;

/**
 *	Utilities for handling HTML
 *
 *	@author	Vibha Raghvendran  
 *	@since	November 15 2024
 */
public class HTMLUtilities {

	private enum TokenState { NONE, COMMENT, PREFORMAT }
    private TokenState state = TokenState.NONE;
    private final int COMMENT_START = 3, COMMENT_END = 2, PRE_START = 4, PRE_END = 5;
    private static final String COMMENT_START_STRING = "<!--", COMMENT_END_STRING = "-->";
    private static final String PRE_START_STRING = "<pre>", PRE_END_STRING = "</pre>";   
    
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
        ArrayList<String> result = new ArrayList<>();

        for(int i = 0; i < str.length(); i++){		
			// detects the start of a comment
            if (state == TokenState.NONE && i < str.length() - COMMENT_START && str.substring(i, i + COMMENT_START + 1).equals(COMMENT_START_STRING)) {               
                i += COMMENT_START; // increments character counter
                state = TokenState.COMMENT;
            }
            // detects the end of a comment
            else if (state == TokenState.COMMENT && i < str.length() - COMMENT_END && str.substring(i, i + COMMENT_END + 1).equals(COMMENT_END_STRING)) {
                i += COMMENT_END; // increments character counter
                state = TokenState.NONE;
            }
            // detects the start of preformatted text
            else if (state == TokenState.NONE && i < str.length() - PRE_START && str.substring(i, i + PRE_START + 1).equals(PRE_START_STRING)) {
				result.add(PRE_START_STRING); // adds the token
				i += PRE_START; // increments character counter
                state = TokenState.PREFORMAT;
            }
            // detects the start of preformatted text
            else if (state == TokenState.PREFORMAT && i < str.length() - PRE_END && str.substring(i, i + PRE_END + 1).equals(PRE_END_STRING)) {
				result.add(PRE_END_STRING); // adds the token
                i += PRE_END; // increments character counter
                state = TokenState.NONE;
            }
            
            //makes the multiline comment into one token
            else if (state == TokenState.COMMENT) {
            	int start = i;
                while (i < str.length() && !(i < str.length() - COMMENT_END && str.substring(i, i + COMMENT_END + 1).equals(COMMENT_END_STRING))) {
                    i++;
                }
                result.add(str.substring(start, i));
                i--;
            }           
            // makes all the preformatted text into one token
            else if (state == TokenState.PREFORMAT) {
                int start = i;
                while (i < str.length() && !(i < str.length() - PRE_END && str.substring(i, i + PRE_END + 1).equals(PRE_END_STRING))) {
                    i++;
                }
                result.add(str.substring(start, i));
                i--; 
            }
            // regular text
            else if (state == TokenState.NONE) {
				if(str.charAt(i) == '<') { // tokenizing tags
					result.add(str.substring(i, str.indexOf('>' , i) + 1)); // substring so that the whole tag will be a token
					i = str.indexOf('>' , i);
				}
				
				else if( Character.isLetter(str.charAt(i))) { // tokenizing words
					int start = i;
					while (i < str.length() && (Character.isLetter(str.charAt(i)) || 
							(str.charAt(i) == '-' && i + 1 < str.length() && Character.isLetter(str.charAt(i + 1))))) {
						i++;
					}
					result.add(str.substring(start, i));
					i--;
				}
				
				else if(Character.isDigit(str.charAt(i)) || (str.charAt(i) == '-' && i + 1 < str.length() && 
					Character.isDigit(str.charAt(i + 1)))) { // tokenizing numbers
					int start = i;
					boolean isDecimal = false, isExponent = false; // checks if it is a decimal or exponent

					while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'e' || 
						str.charAt(i) == 'E' || str.charAt(i) == '-')) { // if it is an exponent or decimal
						if (str.charAt(i) == '.') {
							if (isDecimal) { // exits the loop
								break;
							}
							isDecimal = true; // sets the boolean to true
						} 
						// same logic for exponents
						else if (str.charAt(i) == 'e' || str.charAt(i) == 'E') {
							if (isExponent) {
								break;
							}
							isExponent = true;
							if (i < str.length() - 1 && str.charAt(i + 1) == '-') {
								i++; // takes care of negative exponents
							}
						}
						i++;
					}
					result.add(str.substring(start, i));
					i--;
				}
				
				else if(i < str.length() && isPunctuation(str.charAt(i))) { // tokenizes punctuation
					result.add(String.valueOf(str.charAt(i)));
				}
			}
		
		}

        return result.toArray(new String[0]);
    }

    /**
     * Checks if a character is punctuation
     * 
     * @param char c 		the character being checked
     * @return boolean isPunctuation 		returns if the character is punctuation or nor
     */
    public boolean isPunctuation(char c) {
        return ".,;:()?!&~=+-".indexOf(c) != -1;
    }
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

	private void testRaghu() {
		System.out.println("hello");
	}

} 
