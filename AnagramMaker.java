import java.util.ArrayList;
import java.util.Scanner;

/**
 *	AnagramMaker - Generates anagrams of a user-inputted word/phrase which is split into a certain number of words.
 *  The number of words in each anagram and the number of anagrams printed to the screen is inputted by the user.
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author Vibha Raghvendran
 *	@since  January 19 2025
 */

public class AnagramMaker {

    private final String FILE_NAME = "randomWords.txt";	// file containing all words

    private WordUtilities wu;	// the word utilities for building the word
    // database, sorting the database,
    // and finding all words that match
    // a string of characters

    // Scanner to read in user input
    private Scanner scan = new Scanner(System.in);

    // phrase to make anagrams out of
    private String phrase;

    // variables for constraining the print output of AnagramMaker
    private int numWords;		// the number of words in a phrase to print
    private int maxPhrases;		// the maximum number of phrases to print
    private int numPhrases;		// the number of phrases that have been printed

    /*	Initialize the database inside WordUtilities
     *	The database of words does NOT have to be sorted for AnagramMaker to work,
     *	but the output will appear in order if you DO sort.
     */
    public AnagramMaker() {
        wu = new WordUtilities();
        wu.readWordsFromFile(FILE_NAME);
        wu.sortWords();
    }

    public static void main(String[] args) {
        AnagramMaker am = new AnagramMaker();
        am.run();
    }

    /**	The top routine that prints the introduction and runs the anagram-maker.
     */
    public void run() {
        printIntroduction();
        runAnagramMaker();
        System.out.println("\nThanks for using AnagramMaker!\n");
    }

    /**
     *	Print the introduction to AnagramMaker
     */
    public void printIntroduction() {
        System.out.println("\nWelcome to ANAGRAM MAKER");
        System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
        System.out.println("You can choose the number of words in the anagram.");
        System.out.println("You can choose the number of anagrams shown.");
        System.out.println("\nLet's get started!");
    }

    /**
     *	Prompt the user for a phrase of characters, then create anagrams from those
     *	characters.
     */
    public void runAnagramMaker() {
        System.out.println("Word(s), name or phrase (q to quit):");
        phrase = scan.nextLine();
        while (!phrase.equalsIgnoreCase("q")) {
            System.out.println("Number of words in anagram:");
            numWords = scan.nextInt();
            System.out.println("Maximum number of anagrams to print:");
            numPhrases = scan.nextInt();
            String actualPhrase = "";
            for (int i = 0; i < phrase.length(); i++) {
                if (Character.isLetter(phrase.charAt(i))) {
                    actualPhrase += phrase.charAt(i);
                }
            }
            ArrayList<String> anagram = new ArrayList<>();
            phraseRecursion(actualPhrase, numWords, numPhrases, anagram);

            System.out.println();
            System.out.println("Stopped at " + maxPhrases + " anagrams.");
            System.out.println();
            System.out.println("Word(s), name or phrase (q to quit):");
            scan.nextLine();
            phrase = scan.nextLine();
        }
    }

    public void phraseRecursion (String phrase, int numWords, int numPhrases, ArrayList<String> anagram) {
        if (phrase.length() == 0 && numWords == 0 && maxPhrases < numPhrases) {
            for (int i = 0; i < anagram.size(); i++) {
                System.out.print(anagram.get(i) + " ");
            }
            System.out.println();
            maxPhrases++;
        }
        else if (maxPhrases < numPhrases && numWords > 0) {
            ArrayList<String> allWords = wu.allWords(phrase);
            for (int i = 0; i < allWords.size(); i++) {
                anagram.add(allWords.get(i));
                phraseRecursion(removeLetters(phrase, allWords.get(i)), numWords - 1, numPhrases, anagram);
                anagram.remove(anagram.get(anagram.size() - 1));
            }
        }

    }
    
    public String removeLetters (String str, String remove) {
		for (int j = 0; j < remove.length(); j++) {
             str = str.replaceFirst(remove.charAt(j) + "", "");
        }
        
        return str;
	}
}
