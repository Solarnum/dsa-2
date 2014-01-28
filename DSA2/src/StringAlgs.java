import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * COSC3100 Spring 2014 Homework 1
 * 
 * @author Charlie Beckwith and Mark Schlotke
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
		
		/*
		
		Input: A string string1 and a string string2
		Output: A string
		
		string CURRENT
		string LONGEST
		FOR t=0 to length of string1 do
			for k=0 to length of string2 do
				IF string1[t] equals string2[k] THEN
					int oneIndex = t
					int twoIndex = k
					WHILE oneIndex < length of string1 and twoIndex < length of string2
						IF string1[oneIndex] is not equal to string2[twoIndex]
							BREAK
						END IF
						INCREMENT oneIndex
						INCREMENT twoIndex
					END WHILE
						
					CURRENT = substring of string2 from k to twoIndex
					IF length of CURRENT > length of LONGEST
						LONGEST = CURRENT
					END IF
				END IF
			END FOR
		END FOR
					
						
		RETURN LONGEST
						
		 */
		String longest = "";
		String current = "";
		
		for(int t = 0; t < str1.length();t++){
			for(int k = 0; k < str2.length();k++){
				if(str1.charAt(t) == str2.charAt(k)){
					int oneIndex = t, twoIndex=k;
					while(oneIndex < str1.length() && twoIndex < str2.length()){
						if(str1.charAt(oneIndex) != str2.charAt(twoIndex)){
							break;
						}
						oneIndex++;
						twoIndex++;
					}
					current = str2.substring(k,twoIndex);
					if(current.length() > longest.length()){
						longest = current;
					}
					
				}
			}
		}
		return longest;


	}

}
