import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
		 * Inputs: A string[0-string.length()] Output: The longest palindrome
		 * contained in the string given as an input
		 * 
		 * PSEUDOCODE:
		 */

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
		String longest = "";
		String current = "";
		boolean isPalindrome = false;
		for (int k = 0; k < line.length(); k++) {
			for (int i = k; i < line.length(); i++) {
				if (line.charAt(k) == line.charAt(i)) {
					int leftpt = k;
					int rightpt = i;
					isPalindrome = true;
					while (leftpt < line.length() && rightpt > leftpt) {
						if (line.charAt(leftpt) != line.charAt(rightpt)) {
							isPalindrome = false;
							break;
						}
						leftpt++;
						rightpt--;
					}
					if (isPalindrome) {
						current = line.substring(k, i + 1);
						if (current.length() > longest.length()) {
							longest = current;
						}
					}
				}
			}
		}

		return longest;

	}

	private static String longestCommonSubstring(String str1, String str2) {
		LinkedList<Integer> subStarts = new LinkedList<Integer>();
		boolean building = false, allFailed = true;
		String smallRef, largeRef;
		String substring = "";
		int index = 0;

		// Determine which input string is larger
		if (str1.length() < str2.length()) {
			smallRef = str1;
			largeRef = str2;
		} else {
			smallRef = str2;
			largeRef = str1;
		}

		for (int i = index; i < largeRef.length(); i++) {
			// Build a list of the indices in the small string where
			// the character is the current starting character
			if (!building) {
				for (int j = 0; j < smallRef.length(); j++) {
					if (largeRef.charAt(i) == smallRef.charAt(j)) {
						subStarts.add(j);
					}
				}

				if (!subStarts.isEmpty()) {
					building = true;
				}
			} else {
				LinkedList<Integer> didntFail = new LinkedList<Integer>();
				int[] failed = new int[subStarts.size()];
				int failedIndex = 0;
				int dist = i - index;

				// Iterate through the small string's matching starting indices
				for (Integer subStart : subStarts) {
					// Ensure the next index is contained in the small string
					// and
					// the character at that next index is the same as the
					// current character
					// selected in the large string
					if (subStart + dist < smallRef.length()
							&& largeRef.charAt(i) == smallRef.charAt(subStart
									+ dist)) {
						failed[failedIndex] = 0; // signifies this starting
													// index was successful;

						// At least one of the small string's starting indices
						// was successful
						allFailed = false;
					} else {
						failed[failedIndex] = 1;
					}

					failedIndex++;
				}

				if (allFailed || (i + 1) >= largeRef.length()) {
					String temp;

					if (subStarts.getFirst() != 0) {
						if (allFailed)
							temp = smallRef.substring(subStarts.getFirst() - 1,
									subStarts.getFirst() + dist);
						else
							temp = smallRef.substring(subStarts.getFirst() - 1,
									subStarts.getFirst() + dist + 1);
					} else
						temp = smallRef.substring(subStarts.getFirst(),
								subStarts.getFirst() + dist);

					// Clear the list of substring starting locations
					subStarts.clear();

					// Determine if the newly found substring is longer than
					// the previously found substring
					if (temp.length() > substring.length()) {
						substring = temp;
					}

					index++;
					i = index - 1;
					building = false;
				} else if (i + 1 < largeRef.length()) {
					for (Integer noFail : failed) {
						if (noFail == 0) {
							didntFail.add(subStarts.get(noFail));
						}
					}

					subStarts = didntFail;

					allFailed = true;
				}
			}
		}

		return substring;

	}

}
