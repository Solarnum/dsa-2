import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * COSC3100 Spring 2014 Homework 1
 * 
 * Algorithms and Data Structures 2
 * Marquette University
 * 
 * @author Charlie Beckwith and Mark Schlottke
 * 
 */

public class StringAlgs
{
  /**
   * These are the driver and its helping methods. You must not make any changes
   * except inserting the implementations of the three algorithms after the main
   * method, as indicated below.
   */
  public static void usage(String message)
  {
    System.out.println(message);
    System.out.println("Usage: java " + StringAlgs.class.getSimpleName() + " algorithm");
    System.out.println("\twhere algorithm = lpp or lps or lcs");
    System.out.println("Example:");
    System.out.println("\tjava " + StringAlgs.class.getSimpleName()
        + " lpp\n\t\t# test the longestPalinedromePrefix algorithm\n");
    System.out.println("\tjava " + StringAlgs.class.getSimpleName()
        + " lps\n\t\t# test the longestPalinedromeSubstring algorithm\n");
    System.out.println("\tjava " + StringAlgs.class.getSimpleName()
        + " lcs\n\t\t# test the longestCommonSubstring algorithm\n");
    System.exit(0);
  }

  public static void main(String[] args)
  {
    if (args.length < 1)
    {
      usage("Undefined algorithm!");
    }

    String alg = args[0].toLowerCase();
    String inFile = "hw01-" + alg + ".in";
    Scanner sc;
    String str1 = "";
    String str2 = "";

    try
    {
      sc = new Scanner(new File(inFile));
      while (sc.hasNextLine())
      {
        String line = sc.nextLine();
        line = line.trim();
        if (line.startsWith("#Testcases"))
        {
          System.out.println(line);
          continue;
        } else if (line.equals("") || line.startsWith("#"))
          continue;
        if (alg.equals("lpp"))
        {
          System.out.println("The String: " + line);
          System.out.println("Its Longest Palindrome Prefix: " + longestPalindromePrefix(line));
          System.out.println();
        } else if (alg.equals("lps"))
        {
          System.out.println("The String: " + line);
          System.out.println("Its Longest Palindrome SubString: "
              + longestPalindromeSubstring(line));
          System.out.println();
        } else if (alg.equals("lcs"))
        {

          if (line.startsWith("string1: "))
          {
            str1 = line.substring(8).trim();
            continue;
          }
          if (line.startsWith("string2: "))
          {
            str2 = line.substring(8).trim();
          }
          System.out.println("String I: " + str1);
          System.out.println("String II: " + str2);
          System.out.println("The Longest Common SubString: " + longestCommonSubstring(str1, str2));
          System.out.println();
        } else
        {
          usage("Unknown algorithm!");
        }
      }
    } catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Insert your three algorithms: longestPalindromePrefix,
   * longestPalindromeSubstring, and longestCommonSubstring below.
   */
  private static String longestPalindromePrefix(String line)
  {
    /*
     * Inputs: A string[0-string.length()] Output: The longest palindrome
     * contained in the string given as an input
     * 
     * PSEUDOCODE:
     */
    
    /* longestPalindromePrefix(L)
     * Input: String L
     * Output: a string representing the longest palindrome prefix in L
     * 
     * PSEUDOCODE:
     1. LONGEST <- Empty String
     2. CURRENT <- Empty String
     3. isPalindrome <- false
     4. for i <- 0 to i < L.length()
     6.   if L[0] = L[i]
     7.     leftpt <- 0
     8.     rightpt <- i
     9.     isPalindrome <- true
    10.     while leftpt < L.length() and rightpt < leftpt
    11.       if L[leftpt] not = L[rightpt]
    12.         isPalindrome <- false
    13.         leftpt <- L.length()
    14.       end-if
    15.       leftpt <- leftpt+1
    16.       rightpt <- rightpt-1
    17.     end-while
    18.     if isPalindrome = true
    19.       CURRENT <- substring of L from k to i+1
    20.       if CURRENT.length() > LONGEST.length()
    21.         LONGEST <- CURRENT
    22.       end-if
    23.     end-if
    24.   end-if
    26. end-for
    27. return LONGEST
    */

    String longest = "";
    String current = "";
    boolean isPalindrome = false;

    for (int i = 0; i < line.length(); i++)
    {
      if (line.charAt(0) == line.charAt(i))
      {
        int leftpt = 0;
        int rightpt = i;
        isPalindrome = true;
        while (leftpt < line.length() && rightpt >= 0)
        {
          if (line.charAt(leftpt) != line.charAt(rightpt))
          {
            isPalindrome = false;
            leftpt += line.length();
          }
          leftpt++;
          rightpt--;
        }
        if (isPalindrome)
        {
          current = line.substring(0, i + 1);
          if (current.length() > longest.length())
          {
            longest = current;
          }
        }
      }
    }

    return longest;
  }

  /* longestPalindromeSubstring(L)
   * Input: String L
   * Output: a string representing the longest palindrome substring in L
   *
   * PSEUDOCODE:
   1. LONGEST <- Empty String
   2. CURRENT <- Empty String
   3. isPalindrome <- false
   4. for k <- 0 to k < L.length()
   5.   for i <- k to i < L.length()
   6.     if L[k] = L[i]
   7.       leftpt <- k
   8.       rightpt <- i
   9.       isPalindrome <- true
  10.       while leftpt < length of L and rightpt < leftpt
  11.         if L[leftpt] not = L[rightpt]
  12.           isPalindrome <- false
  13.           leftpt <- L.length()
  14.         end-if
  15.         leftpt <- leftpt+1
  16.         rightpt <- rightpt-1
  17.       end-while
  18.       if isPalindrome = true
  19.         CURRENT <- substring of L from k to i+1
  20.         if CURRENT.length() > LONGEST.length()
  21.           LONGEST <- CURRENT
  22.         end-if
  23.       end-if
  24.     end-if
  25.   end-for
  26. end-for
  27. return LONGEST
  */
  private static String longestPalindromeSubstring(String line)
  {
    // TODO Auto-generated method stub
    String longest = "";
    String current = "";
    boolean isPalindrome = false;
    for (int k = 0; k < line.length(); k++)
    {
      for (int i = k; i < line.length(); i++)
      {
        if (line.charAt(k) == line.charAt(i))
        {
          int leftpt = k;
          int rightpt = i;
          isPalindrome = true;
          while (leftpt < line.length() && rightpt > leftpt)
          {
            if (line.charAt(leftpt) != line.charAt(rightpt))
            {
              isPalindrome = false;
              leftpt += line.length();
            }
            leftpt++;
            rightpt--;
          }
          if (isPalindrome)
          {
            current = line.substring(k, i + 1);
            if (current.length() > longest.length())
            {
              longest = current;
            }
          }
        }
      }
    }

    return longest;

  }

  private static String longestCommonSubstring(String str1, String str2)
  {

    /* longestCommonSubstring( S1, S2)
     * Input: String S1
     *        String S2 
     * Output: a string representing the longest common substring in both S1 and S2
     *
     * PSEUDOCODE:
     1. CURRENT <- Empty String
     2. LONGEST <- Empty String
     3. for t <- 0 to length of S1
     4.  for k <- 0 to length of S2
     5.    if S1[t] = S2[k]
     6.      oneIndex <- t
     7.      twoIndex <- k
     8.      while oneIndex < S1.length() and twoIndex < S2.length()
     9.       if S1[oneIndex] not = S2[twoIndex]
    10.          oneIndex <- S1.length()
    11.        end-if
    12.        oneIndex <- oneIndex+1
    13.        twoIndex <- twoIndex+1
    14.      end-while   
    15.      CURRENT <- substring of S2 from k to twoIndex;
    16.      if CURRENT.length() > LONGEST.length()
    17.        LONGEST <- CURRENT
    18.      end-if
    19.    end-if
    20.  end-for
    21. end-for
    22. return LONGEST
     */
    String longest = "";
    String current = "";

    for (int t = 0; t < str1.length(); t++)
    {
      for (int k = 0; k < str2.length(); k++)
      {
        if (str1.charAt(t) == str2.charAt(k))
        {
          int oneIndex = t, twoIndex = k;
          while (oneIndex < str1.length() && twoIndex < str2.length())
          {
            if (str1.charAt(oneIndex) != str2.charAt(twoIndex))
            {
              oneIndex += str1.length();
            }
            oneIndex++;
            twoIndex++;
          }
          current = str2.substring(k, twoIndex);
          if (current.length() > longest.length())
          {
            longest = current;
          }

        }
      }
    }
    return longest;

  }

}
