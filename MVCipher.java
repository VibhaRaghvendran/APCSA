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
		
	/** Constructor */
	public MVCipher() { 
		
	}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		Scanner scan = new Scanner (System.in);
		boolean found = false;
		
		System.out.println("Welcome to the MV Cipher machine!");
		System.out.println();
		System.out.println("Please input a word to use as key (letters only):");
		keyword = scan.nextLine();
		
		while (!found) {
			
		}
		
		
		/* Prompt for encrypt or decrypt */
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}
