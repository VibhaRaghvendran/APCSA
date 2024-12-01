import java.util.ArrayList;
import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Vibha Raghvendran
 *	@version 1.0
 *  @since November 23 2024
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private ArrayList<String> tokens;

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	private HTMLUtilities util;
	
		
	public HTMLRender() {
		util = new HTMLUtilities();
		// Initialize token arraylist
		tokens = new ArrayList<>();
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	public void run(String[] args) {
		Scanner input = null;
		String fileName = "";

		// if the command line contains the file name, then store it
//		if (args.length > 0)
//			fileName = args[0];
//			// otherwise print out usage message
//		else {
//			System.out.println("Usage: java HTMLTester <htmlFileName>");
//			System.exit(0);
//		}

		// Open the HTML file
		input = FileUtils.openToRead("example7.html");

		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			System.out.println("\n" + line);
			String [] oneLine = util.tokenizeHTMLString(line);
			for (int i = 0; i < oneLine.length; i++) {
				tokens.add(oneLine[i]);
			}
		}

		input.close();

		int count = 0;
		for (int i = 0; i < tokens.size(); i++) {
			while (!(tokens.get(i).equalsIgnoreCase("<html>"))) {
				i++;
				if (i >= tokens.size()) {
					break;
				}
			}
			if (i >= tokens.size()) {
				break;
			}
			while (!(tokens.get(i).equalsIgnoreCase("<body>"))) {
				i++;
			}
			i++;
			while (!(tokens.get(i).equals("</body>"))) {
				if (tokens.get(i).equalsIgnoreCase("<p>")) {
					int charCount = 0;
					i++;
					browser.println();
					while (!(tokens.get(i).equalsIgnoreCase("</p>"))) {
						if (tokens.get(i).equalsIgnoreCase("<br>")) {
							browser.printBreak();
							i++;
						}
						else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
							browser.printHorizontalRule();
							i++;
						}
						else if (tokens.get(i).equalsIgnoreCase("<b>")) {
							i++;
							while (!(tokens.get(i).equalsIgnoreCase("</b>"))) {
								if (tokens.get(i).equalsIgnoreCase("<br>")) {
									browser.printBreak();
								}
								else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
									browser.printHorizontalRule();
								}
								else {
									if (noSpace(i)) {
										browser.printBold(tokens.get(i));
										charCount += tokens.get(i).length();
									}
									else {
										browser.printBold(tokens.get(i) + " ");
										charCount += tokens.get(i).length() + 1;
									}
								}
								if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
									browser.println();
									charCount = 0;
								}
								i++;
							}
						}
						else if (tokens.get(i).equalsIgnoreCase("<i>")) {
							i++;
							while (!(tokens.get(i).equalsIgnoreCase("</i>"))) {
								if (tokens.get(i).equalsIgnoreCase("<br>")) {
									browser.printBreak();
								}
								else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
									browser.printHorizontalRule();
								}
								else {
									if (noSpace(i)) {
										browser.printItalic(tokens.get(i));
										charCount += tokens.get(i).length();
									}
									else {
										browser.printItalic(tokens.get(i) + " ");
										charCount += tokens.get(i).length() + 1;
									}
								}
								if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
									browser.println();
									charCount = 0;
								}
								i++;
							}
						}
						else {
							if (noSpace(i)) {
								browser.print(tokens.get(i));
								charCount += tokens.get(i).length();
							}
							else {
								browser.print(tokens.get(i) + " ");
								charCount += tokens.get(i).length() + 1;
							}
						}

						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
					browser.println();
				}
				else if (tokens.get(i).equalsIgnoreCase("<q>")) {
					int charCount = 0;
					browser.print("\"");
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</q>"))) {
						if (tokens.get(i).equalsIgnoreCase("<br>")) {
							browser.printBreak();
						}
						else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
							browser.printHorizontalRule();
						}
						else {
							if (noSpace(i)) {
								browser.print(tokens.get(i));
								charCount += tokens.get(i).length();
							}
							else {
								browser.print(tokens.get(i) + " ");
								charCount += tokens.get(i).length() + 1;
							}
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
					browser.print("\" ");
				}
				else if (tokens.get(i).equalsIgnoreCase("<b>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</b>"))) {
						if (tokens.get(i).equalsIgnoreCase("<br>")) {
							browser.printBreak();
						}
						else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
							browser.printHorizontalRule();
						}
						else {
							if (noSpace(i)) {
								browser.print(tokens.get(i));
								charCount += tokens.get(i).length();

							}
							else {
								browser.print(tokens.get(i) + " ");
								charCount += tokens.get(i).length() + 1;
							}
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<i>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</i>"))) {
						if (tokens.get(i).equalsIgnoreCase("<br>")) {
							browser.printBreak();
						}
						else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
							browser.printHorizontalRule();
						}
						else {
							if (noSpace(i)) {
								browser.print(tokens.get(i));
								charCount += tokens.get(i).length();
							}
							else {
								browser.print(tokens.get(i) + " ");
								charCount += tokens.get(i).length() + 1;
							}
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<br>")) {
					browser.printBreak();
					i++;
				}
				else if (tokens.get(i).equalsIgnoreCase("<hr>")) {
					browser.printHorizontalRule();
					i++;
				}
				else if (tokens.get(i).equalsIgnoreCase("<h1>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h1>"))) {
						if (noSpace(i)) {
							browser.printHeading1(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading1(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 40) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<h2>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h2>"))) {
						if (noSpace(i)) {
							browser.printHeading2(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading2(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 50) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<h3>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h3>"))) {
						if (noSpace(i)) {
							browser.printHeading3(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading3(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 60) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<h4>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h4>"))) {
						if (noSpace(i)) {
							browser.printHeading4(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading4(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 80) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<h5>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h5>"))) {
						if (noSpace(i)) {
							browser.printHeading5(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading5(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 100) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).equalsIgnoreCase("<h6>")) {
					int charCount = 0;
					i++;
					while (!(tokens.get(i).equalsIgnoreCase("</h6>"))) {
						if (noSpace(i)) {
							browser.printHeading6(tokens.get(i));
							charCount += tokens.get(i).length();
						}
						else {
							browser.printHeading6(tokens.get(i) + " ");
							charCount += tokens.get(i).length() + 1;
						}
						if (i < tokens.size() - 1 && tokens.get(i+1).length() + charCount > 120) {
							browser.println();
							charCount = 0;
						}
						i++;
					}
				}
				else if (tokens.get(i).length() >= 2 && tokens.get(i).charAt(0) == '<' && tokens.get(i).charAt(1) == '/') {
					i++;
				}
				else if (tokens.get(i).equalsIgnoreCase("<pre>")) {
					i++;
				}
				else if (tokens.get(i).equalsIgnoreCase("</pre>")) {
					browser.println();
					i++;
				}
				else {
					if (noSpace(i)) {
						browser.print(tokens.get(i));
						count += tokens.get(i).length();
					}
					else {
						browser.print(tokens.get(i) + " ");
						count += tokens.get(i).length() + 1;
					}
					if (i < tokens.size() - 1 && count + tokens.get(i+1).length() > 80) {
						browser.println();
						count = 0;
					}
					if ((i < tokens.size() - 1 && tokens.get(i-1).charAt(0) == '<') || (i > 0 && tokens.get(i-1).charAt(tokens.get(i-1).length() - 1) == '>')) {
						count = 0;
					}
					i++;
				}
			}
		}
	}

	public boolean noSpace (int i) {
		if (i < tokens.size() - 1) {
			if (Character.isLetter(tokens.get(i).charAt(0)) && (tokens.get(i+1).charAt(0) == '.' ||
					tokens.get(i+1).charAt(0) == ',' || tokens.get(i+1).charAt(0) == '!' || tokens.get(i+1).charAt(0) == '?' ||
					tokens.get(i+1).charAt(0) == '"' || tokens.get(i+1).charAt(0) == ';' || tokens.get(i+1).charAt(0) == ':')) {
				return true;
			}
			else if (tokens.get(i).charAt(0) == '-' && Character.isDigit(tokens.get(i+1).charAt(0))) {
				return true;
			}
			else if (util.isPunctuation(tokens.get(i).charAt(0)) && util.isPunctuation(tokens.get(i+1).charAt(0)) &&
					tokens.get(i).charAt(0) != ',') {
				return true;
			}
			else if ((tokens.get(i).charAt(0) == 'H' || tokens.get(i).charAt(0) == 'h') && Character.isDigit(tokens.get(i+1).charAt(0))) {
				return true;
			}
			else if (tokens.get(i).charAt(0) == '-' && Character.isDigit(tokens.get(i+1).charAt(0))) {
				return true;
			}
			else if (Character.isLetter(tokens.get(i).charAt(tokens.get(i).length() - 1)) && tokens.get(i+1).charAt(0) == '"') {
				return true;
			}
		}

		return false;
	}
	
	
}
