import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MVCipher1 {

	private String keyword;
	private String upperKeyword;
	private int keyLength;
	private int option;
	private int count;
	private final int ALPHABET_LENGTH = 'z' - 'a' + 1;
	Scanner scan = new Scanner (System.in); 
	
	public MVCipher1 () {
		run();
	}
	
	public void run () {
		System.out.println("Welcome to the MV Cipher game!");
		System.out.println();
		
		//boolean found = false;
		//while (!found) {
			System.out.println("Please input a word to use as key (letters only):");
			keyword = scan.nextLine();
			upperKeyword = keyword.toUpperCase();
			keyLength = keyword.length();
			// add checking for keyword
		//}
			
		boolean found = false; 
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
		
		if (option == 1) {
			encryption();
		}
		else {
			decryption();
		}
		
	}
	
	// returns offset of key from the beginning
	public int getKeyOffset (boolean isLower) {
		char retChar = upperKeyword.charAt(count%keyLength);		
		count++;
//		
//		if (isLower) {
//			return retChar - 'a' + 1;
//		} else {
//			return retChar - 'A' + 1;
//		}
		
		return retChar - 'A' + 1;
	}
	
	// 0 - invalid, 1 - lower, 2 - upper
	public int validChar (char a) {
		if (a >= 'a' && a <= 'z') {
			return 1;
		}
		else if (a >= 'A' && a <= 'Z') {
			return 2;
		}
		
		return 0;
	}
	
	public void encryption () {
		String inputFile;
		String outputFile;
		
		System.out.println("Name of file to encrypt:");
		inputFile = scan.nextLine();
		System.out.println("Name of output file:");
		outputFile = scan.nextLine();
		
		PrintWriter out = FileUtils.openToWrite(outputFile);
		StringBuffer outFileSB = new StringBuffer();
		
		try (FileReader reader = new FileReader(inputFile)) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char origChar = (char) charValue;  
                char newChar = encryptOneChar(origChar);
                outFileSB.append(newChar);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
		
		out.write(outFileSB.toString());
		out.flush();
		out.close();
		
		System.out.println("The encrypted file " + outputFile + " has been encrypted using the keyword: " + upperKeyword);
	}
	
	public char encryptOneChar (char pc) {
		char ec = ' ';
		int additionFromKey = 0;
		int charType = validChar(pc);
		if (charType != 0) {			
			additionFromKey = getKeyOffset(charType == 1);
			if (charType == 2) {
				int pcOffset = pc - 'A';
				int ecOffset = ((pcOffset + additionFromKey) % ALPHABET_LENGTH);
				ec = (char) (ecOffset + 'A');
			}
			else {
				int pcOffset = pc - 'a';
				int ecOffset = ((pcOffset + additionFromKey) % ALPHABET_LENGTH);
				ec = (char) (ecOffset + 'a');
			}
		}
		else {
			ec = pc;
		}
		
		return ec;
	}
	
	public void decryption () {
		String inputFile;
		String outputFile;
		
		System.out.println("Name of file to decrypt:");
		inputFile = scan.nextLine();
		System.out.println("Name of output file:");
		outputFile = scan.nextLine();
		
		PrintWriter out = FileUtils.openToWrite(outputFile);
		StringBuffer outFileSB = new StringBuffer();
		
		try (FileReader reader = new FileReader(inputFile)) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char origChar = (char) charValue;  
                char newChar = decryptOneChar(origChar);
                outFileSB.append(newChar);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
		
		out.write(outFileSB.toString());
		out.flush();
		out.close();
		
		System.out.println("The decrypted file " + outputFile + " has been decrypted using the keyword: " + upperKeyword);
	}
	
	public char decryptOneChar (char pc) {
		char ec = ' ';
		int additionFromKey = 0;
		int charType = validChar(pc);
		if (charType != 0) {			
			additionFromKey = getKeyOffset(charType == 1);
			if (charType == 2) {
				int pcOffset = pc - 'A';
				int ecOffset = 0;
				if (pcOffset >= additionFromKey) {
					ecOffset = ((pcOffset - additionFromKey) % ALPHABET_LENGTH);
				}
				else {
					ecOffset = ALPHABET_LENGTH - Math.abs(pcOffset - additionFromKey);
				}
				ec = (char) (ecOffset + 'A');
			}
			else {
				int pcOffset = pc - 'a';
				int ecOffset = 0;
				if (pcOffset >= additionFromKey) {
					ecOffset = ((pcOffset - additionFromKey) % ALPHABET_LENGTH);
				}
				else {
					ecOffset = ALPHABET_LENGTH - Math.abs(pcOffset - additionFromKey);
				}
				ec = (char) (ecOffset + 'a');
			}
		}
		else {
			ec = pc;
		}
		
		return ec;
	}
	
	public static void main (String[] args) {
		MVCipher1 cipher = new MVCipher1();
	}
}
