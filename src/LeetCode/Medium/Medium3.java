package LeetCode.Medium;

/*******************************************************
 Given a string s, find the length of the longest substring without repeating characters.
 ******************************************************/
public class Medium3
{
    public static void main(String[] args)
    {
        String s = "dvddf";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s)
    {
        int maxLength = 0;

        if (s.length() > 0)
        {
            String tempstr = s.charAt(0) + "";
            maxLength = 1;

            int i = 1;
            while (i < s.length())
            {
                if (tempstr.contains(s.charAt(i) + ""))
                {
                    tempstr += s.charAt(i);
                    tempstr = tempstr.substring(tempstr.indexOf(s.charAt(i)) + 1);
                } else
                {
                    tempstr += s.charAt(i);
                }
                maxLength = Math.max(maxLength, tempstr.length());
                i++;
            }
        }
        return maxLength;
    }
}
