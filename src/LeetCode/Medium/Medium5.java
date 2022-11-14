package LeetCode.Medium;

public class Medium5
{
    static int firstIndex = 0;
    static int maxPalindromeLength = 0;

    public static void main(String[] args)
    {
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s)
    {
        String result = s;
        if (s.length() >= 2)
        {
            for (int i = 0; i < s.length(); i++)
            {
                //when the string is of odd length you want to start with one character since the middle
                //is just one character
                extendPalindrome(s, i, i);

                //when the string is of even length you want to start with 2 characters and extend out since the
                //middle of the string is two characters
                extendPalindrome(s, i, i + 1);
            }
            result = s.substring(firstIndex, firstIndex + maxPalindromeLength);
        }
        return result;
    }

    public static void extendPalindrome(String s, int startIndex1, int startIndex2)
    {
        while ((startIndex1 >= 0 && startIndex2 < s.length()) && (s.charAt(startIndex1) == s.charAt(startIndex2)))
        {
            startIndex1--;
            startIndex2++;
        }

        if (maxPalindromeLength < startIndex2 - startIndex1 - 1)
        {
            firstIndex = startIndex1 + 1;
            maxPalindromeLength = startIndex2 - startIndex1 - 1;
        }
    }
}