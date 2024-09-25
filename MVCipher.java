import java.util.Scanner;

/**
 *	MVCipher - Encrypts/Decrypts messages 
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Vibha Raghvendran
 *	@since	20 September 2024
 */
public class MVCipher {
	
	String keyword = "";
	Scanner scan = new Scanner (System.in);
	boolean found;
		
	/** Constructor */
	public MVCipher() { 
		run();
	}

	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("Welcome to the MV Cipher machine!");
		System.out.println();

		keyword();
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
	public void keyword () {		
		found = false;
		
		System.out.println("Please input a word to use as key (letters only):");
		keyword = scan.nextLine();
		
		// update checking
		while (!found) {
			if (keyword.length() >= 3) {
				for (int i = 0; i < keyword.length(); i++) {
					if (!(keyword.charAt(i) >= 32 && keyword.charAt(i) <= 64)) {
						found = true;
					}
				}
			}
			else {
				System.out.println("ERROR: Key must be all letters and at least 3 characters long. Enter another keyword.");
				keyword = scan.nextLine();
			}		
		}
		
		System.out.println("Yay!");
	}
	public String mode () {
		String answer;
		
		/* Prompt for encrypt or decrypt */
		System.out.println("Encrypt(e) or decrypt(d)?"); 	
		answer = scan.nextLine();
		found = false;
		 
		while (!found) {
			if (answer.equalsIgnoreCase("e")) {
				found = true;
			}		
			else if (answer.equalsIgnoreCase("d")) {
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
	
	public String inputOutput () {
		String answer;
		
		/* Prompt for encrypt or decrypt */
		System.out.println("Encrypt(e) or decrypt(d)?"); 	
		answer = scan.nextLine();
		found = false;
		 
		while (!found) {
			if (answer.equalsIgnoreCase("e")) {
				found = true;
			}		
			else if (answer.equalsIgnoreCase("d")) {
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
	
	public void encrypt () {
		System.out.println("encrypting");
	}
	
	public String encryptOneWord (String a) {
		
		
		return "";
	}
	
	public void decrypt () {
		System.out.println("encrypting");
	}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
}
