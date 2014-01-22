import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COSC3100 Spring 2011 Homework 1
 * 
 * @author Charlie Beckwith
 * 
 */

public class StringAlgs {
	/**
	 * These are the driver and its helping methods. You must not make any
	 * changes except inserting the implementations of the three algorithms
	 * after the main method, as indicated below.
	 */
	public static void usage(String message) {
		System.out.println(message);
		System.out.println("Usage: java " + StringAlgs.class.getSimpleName()
				+ " algorithm");
		System.out.println("\twhere algorithm = lpp or lps or lcs");
		System.out.println("Example:");
		System.out.println("\tjava " + StringAlgs.class.getSimpleName()
				+ " lpp\n\t\t# test the longestPalinedromePrefix algorithm\n");
		System.out
				.println("\tjava "
						+ StringAlgs.class.getSimpleName()
						+ " lps\n\t\t# test the longestPalinedromeSubstring algorithm\n");
		System.out.println("\tjava " + StringAlgs.class.getSimpleName()
				+ " lcs\n\t\t# test the longestCommonSubstring algorithm\n");
		System.exit(0);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			usage("Undefined algorithm!");
		}

		String alg = args[0].toLowerCase();
		String inFile = "hw01-" + alg + ".in";
		Scanner sc;
		String str1 = "";
		String str2 = "";

		try {
			sc = new Scanner(new File(inFile));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				line = line.trim();
				if (line.startsWith("#Testcases")) {
					System.out.println(line);
					continue;
				} else if (line.equals("") || line.startsWith("#"))
					continue;
				if (alg.equals("lpp")) {
					System.out.println("The String: " + line);
					System.out.println("Its Longest Palindrome Prefix: "
							+ longestPalindromePrefix(line));
					System.out.println();
				} else if (alg.equals("lps")) {
					System.out.println("The String: " + line);
					System.out.println("Its Longest Palindrome SubString: "
							+ longestPalindromeSubstring(line));
					System.out.println();
				} else if (alg.equals("lcs")) {

					if (line.startsWith("string1: ")) {
						str1 = line.substring(8).trim();
						continue;
					}
					if (line.startsWith("string2: ")) {
						str2 = line.substring(8).trim();
					}
					System.out.println("String I: " + str1);
					System.out.println("String II: " + str2);
					System.out.println("The Longest Common SubString: "
							+ longestCommonSubstring(str1, str2));
					System.out.println();
				} else {
					usage("Unknown algorithm!");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert your three algorithms: longestPalindromePrefix,
	 * longestPalindromeSubstring, and longestCommonSubstring below.
	 */
	private static String longestPalindromePrefix(String line) {
		/*
		 * Inputs: A string[0-string.length()]
		 * Output: The longest palindrome contained in the string given as an input
		 * 
		 * PSEUDOCODE:
		 *  
		 */
		 //LOL okay now its working? Yes it is.
		String longest = "";
		String current = "";
		boolean isPalindrome = false;

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(0) == line.charAt(i)) {
				int leftpt = 0;
				int rightpt = i;
			    isPalindrome = true;
				while (leftpt < line.length() && rightpt >= 0) {
					if (line.charAt(leftpt) != line.charAt(rightpt)) {
						isPalindrome = false;
						break;
					}
					leftpt++;
					rightpt--;
				}
				if (isPalindrome) {
					current = line.substring(0, i + 1);

					System.out.println("i = " + i
							+ "  : Current String Printing  :  "
							+ line.substring(0, i + 1));
					if (current.length() > longest.length()) {
						longest = current;
					}
				}
			}
		}

		return longest;
	}

	private static String longestPalindromeSubstring(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String longestCommonSubstring(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

}
