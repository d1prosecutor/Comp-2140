package LeetCode.Easy;

/******************************
 Given a string s consisting of words and spaces, return the length of the last word in the string.

 A word is a maximal substring consisting of non-space characters only.
 *****************************/
public class Easy58
{
    public int lengthOfLastWord(String s)
    {
        String[] test = s.split(" ");
        return (test[test.length - 1].length());
    }
}
