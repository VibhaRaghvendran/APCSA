import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MVCipher {

	private String keyword; // keyword that user inputs
	private String upperKeyword; // uppercase version of the keyword
	private int keyLength; // length of the keyword
	private int option; // choosing encrypt or decrypt
	private int count; // keeps track of which character of the keyword to use
	private final int ALPHABET_LENGTH = 'z' - 'a' + 1; // length of the alphabet (how many letters are there)
	Scanner scan = new Scanner (System.in); // scanner to read in user inputs
	
	public MVCipher () {
		run();
	}
	
	/** 
	 *	Asks for a keyword
	 * 	Asks for encrypt/decrypt
	 * 
	 * 	Calls methods based on the answer (encryption method for encrypt, decryption method for decrypt)
	 */
	
	public void run () {
		System.out.println("Welcome to the MV Cipher game!");
		System.out.println();
		
		// asks for keyword
		System.out.println("Please input a word to use as key (letters only):");
		keyword = scan.nextLine();
		upperKeyword = keyword.toUpperCase();
		keyLength = keyword.length();
			
		boolean found = false; 
		
		//asks for encryption/decryption
		while (!found) {
			System.out.println("Encrypt(1) or decrypt(2)?");
			option = scan.nextInt();
			
			if (option == 1 || option == 2) {
				found = true;
			}
			else {
				System.out.println("That is not a valid answer. Please enter again.");
			}
		}
		
		scan.nextLine();
		
		// calls the methods based on option
		if (option == 1) {
			encryption();
		}
		else {
			decryption();
		}
		
	}
	
	/** 
	 *	Returns how much to offset: gets the character of the keyword (uppercase), gets the alphabet number by subtracting 
	 * 	the ASCII value of 'A' from the ASCII value of the character
	 * 
	 *	@return int retChar
	 */
	public int getKeyOffset () {
		char retChar = upperKeyword.charAt(count%keyLength);		
		count++;
		
		return retChar - 'A' + 1;
	}
	
	/** 
	 *	Returns if the character in the input file is uppercase, lowercase, or not a letter (1 for lowercase, 
	 * 	2 for uppercase, 0 for not a letter)
	 * 
	 * 	@param char a
	 *	@return int validChar
	 */
	public int validChar (char a) {
		if (a >= 'a' && a <= 'z') {
			return 1;
		}
		else if (a >= 'A' && a <= 'Z') {
			return 2;
		}
		
		return 0;
	}
	
	/** 
	 *	Encrypts the file:
	 * 
	 * 	Reads in input and output file
	 * 	Opens a new file with the output file name using PrintWriter
	 * 	Creates a new StringBuffer that adds on to each encrypted character 
	 * 	Reads the input file and encrypts each character using encryptOneChar()
	 * 	Uses PrintWriter to write the StringBuffer onto the ouput file
	 */
	public void encryption () {
		String inputFile;
		String outputFile;
		
		System.out.println("Name of file to encrypt:");
		inputFile = scan.nextLine();
		System.out.println("Name of output file:");
		outputFile = scan.nextLine();
		
		PrintWriter out = FileUtils.openToWrite(outputFile); // creates a new file
		StringBuffer outFileSB = new StringBuffer(); // creates a new StringBuffer
		
		try (FileReader reader = new FileReader(inputFile)) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char origChar = (char) charValue;  
                char newChar = encryptOneChar(origChar); // calls encryptOneChar to encrypt each character
                outFileSB.append(newChar); // adds on each encrypted character to the StringBuffer
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
		
		out.write(outFileSB.toString());
		out.flush();
		out.close();
		
		System.out.println("The encrypted file " + outputFile + " has been encrypted using the keyword: " + upperKeyword); // prints the finishing message
	}
	
	/** 
	 *	Uses the old char and the amount to add to encrypt a new char
	 * 
	 * 	@param char pc (plain char)
	 *	@return char ec (encrypted char)
	 */
	public char encryptOneChar (char pc) {
		char ec = ' ';
		int additionFromKey = 0;
		int charType = validChar(pc); // checks if pc is uppercase, lowercase or not a letter
		if (charType != 0) { // if the character is uppercase or lowercase, implement the encryption algorithm			
			additionFromKey = getKeyOffset(); // get the addition value from getKeyOffset()
			if (charType == 2) { // if the character is uppercase
				int pcOffset = pc - 'A'; // finds the alphabet number of the original character
				int ecOffset = ((pcOffset + additionFromKey) % ALPHABET_LENGTH); // adds the corresponding keyword character to the original character
				ec = (char) (ecOffset + 'A'); // converts the alphabet number to its ASCII value by adding a capital A
			}
			else { // if the character is lowercase
				int pcOffset = pc - 'a'; // finds the alphabet number of the original character
				int ecOffset = ((pcOffset + additionFromKey) % ALPHABET_LENGTH); // adds the corresponding keyword character to the original character
				ec = (char) (ecOffset + 'a'); // converts the alphabet number to its ASCII value by adding a capital A
			}
		}
		else { // if the character is not a letter, keep it the same
			ec = pc;
		}
		
		return ec; // return the encrypted character
	}
	
	/** 
	 *	Decrypts the file:
	 * 
	 * 	Reads in input and output file
	 * 	Opens a new file with the output file name using PrintWriter
	 * 	Creates a new StringBuffer that adds on to each decrypted character 
	 * 	Reads the input file and decrypts each character using decryptOneChar()
	 * 	Uses PrintWriter to write the StringBuffer onto the ouput file
	 */
	public void decryption () {
		String inputFile;
		String outputFile;
		
		System.out.println("Name of file to decrypt:");
		inputFile = scan.nextLine();
		System.out.println("Name of output file:");
		outputFile = scan.nextLine();
		
		PrintWriter out = FileUtils.openToWrite(outputFile); // creates a new file
		StringBuffer outFileSB = new StringBuffer(); // creates a new StringBuffer
		
		try (FileReader reader = new FileReader(inputFile)) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char origChar = (char) charValue;  
                char newChar = decryptOneChar(origChar); // calls decryptOneChar to encrypt each character
                outFileSB.append(newChar); // adds on each encrypted character to the StringBuffer
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
		
		out.write(outFileSB.toString());
		out.flush();
		out.close();
		
		System.out.println("The decrypted file " + outputFile + " has been decrypted using the keyword: " + upperKeyword); // prints the finishing message
	}
	
	/** 
	 *	Uses the old char and the amount to subtract to decrypt a new char
	 * 
	 * 	@param char pc (plain char)
	 *	@return char ec (encrypted char)
	 */
	public char decryptOneChar (char pc) {
		char ec = ' ';
		int additionFromKey = 0; 
		int charType = validChar(pc); // if the character is uppercase or lowercase, implement the decryption algorithm
		if (charType != 0) { // if the character is uppercase or lowercase, implement the decryption algorithm			
			additionFromKey = getKeyOffset(); // get the addition value from getKeyOffset()
			if (charType == 2) { // if the character is uppercase
				int pcOffset = pc - 'A'; // get the subtraction value from getKeyOffset()
				int ecOffset = 0;
				if (pcOffset >= additionFromKey) { // if the original character is >= the subtraction value, do a direct subtraction
					ecOffset = ((pcOffset - additionFromKey) % ALPHABET_LENGTH);
				}
				else { // if the original character is < the subtraction value, take the absolute difference and subtract that from alphabet length
					ecOffset = ALPHABET_LENGTH - Math.abs(pcOffset - additionFromKey);
				}
				ec = (char) (ecOffset + 'A'); // converts the alphabet number to its ASCII value by adding a lowercase a
			}
			else { // if the character is lowercase
				int pcOffset = pc - 'a'; // get the subtraction value from getKeyOffset()
				int ecOffset = 0;
				if (pcOffset >= additionFromKey) { // if the original character is >= the subtraction value, do a direct subtraction
					ecOffset = ((pcOffset - additionFromKey) % ALPHABET_LENGTH);
				}
				else { // if the original character is < the subtraction value, take the absolute difference and subtract that from alphabet length
					ecOffset = ALPHABET_LENGTH - Math.abs(pcOffset - additionFromKey);
				}
				ec = (char) (ecOffset + 'a'); // converts the alphabet number to its ASCII value by adding a lowercase a
			}
		}
		else { // if the character is not a letter, keep it the same
			ec = pc;
		}
		
		return ec; // return the decrypted character
	}
	
	public static void main (String[] args) {
		MVCipher cipher = new MVCipher();
	}
}
