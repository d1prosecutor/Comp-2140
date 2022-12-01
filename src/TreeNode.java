import java.util.*;

class interviewPrep
{
    public static void main(String[] args)
    {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(2);
        Collections.sort(temp);
        graph.put(0, temp);

        temp = new ArrayList<>();
        temp.add(0);
        temp.add(2);
        temp.add(3);
        Collections.sort(temp);
        graph.put(1, temp);

        temp = new ArrayList<>();
        temp.add(0);
        temp.add(1);
        temp.add(4);
        temp.add(5);
        Collections.sort(temp);
        graph.put(2, temp);

        temp = new ArrayList<>();
        temp.add(1);
        temp.add(4);
        temp.add(6);
        Collections.sort(temp);
        graph.put(3, temp);

        temp = new ArrayList<>();
        temp.add(2);
        temp.add(3);
        temp.add(5);
        temp.add(7);
        Collections.sort(temp);
        graph.put(4, temp);

        temp = new ArrayList<>();
        temp.add(2);
        temp.add(4);
        temp.add(8);
        temp.add(9);
        Collections.sort(temp);
        graph.put(5, temp);

        temp = new ArrayList<>();
        temp.add(3);
        temp.add(7);
        Collections.sort(temp);
        graph.put(6, temp);

        temp = new ArrayList<>();
        temp.add(4);
        temp.add(6);
        Collections.sort(temp);
        graph.put(7, temp);

        temp = new ArrayList<>();
        temp.add(5);
        temp.add(9);
        Collections.sort(temp);
        graph.put(8, temp);

        temp = new ArrayList<>();
        temp.add(5);
        temp.add(8);
        Collections.sort(temp);
        graph.put(9, temp);

    }

    public int uniquePaths(int m, int n)
    {
        return uniquePaths(m, n, new HashMap<Map.Entry<Integer, Integer>, Integer>());
    }

    private int uniquePaths(int m, int n, HashMap<Map.Entry<Integer, Integer>, Integer> memo)
    {
        if (memo.containsKey(new AbstractMap.SimpleEntry<>(m, n)))
            return memo.get(new AbstractMap.SimpleEntry<>(m, n));
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;

        memo.put(new AbstractMap.SimpleEntry<>(m, n), uniquePaths(m - 1, n, memo) + uniquePaths(m, n - 1, memo));
        return memo.get(new AbstractMap.SimpleEntry<>(m, n));
    }

    public static boolean canJump(int[] nums)
    {
        HashMap<Integer, Boolean> memo = new HashMap<>();
        return canJump(nums, 0, memo);
    }

    private static boolean canJump(int[] nums, int index, HashMap<Integer, Boolean> memo)
    {
        if (memo.containsKey(index)) return memo.get(index);
        if (nums.length == 1 || index == nums.length - 1)
            return true;

        if (nums[index] == 0)
        {
            memo.put(index, false);
            return false;
        }

        for (int i = 1; i <= nums[index]; i++)
        {
            if ((canJump(nums, index + i, memo)))
            {
                memo.put(index, true);
                return true;
            }
        }
        memo.put(index, false);
        return false;
    }

    public static Long matrixRunner(int m, int n, HashMap<Map.Entry<Integer, Integer>, Long> memo)
    {
        if (memo.containsKey(new AbstractMap.SimpleEntry<>(m, n)))
        {
            return memo.get(new AbstractMap.SimpleEntry<>(m, n));
        }
        if (m == 1 && n == 1)
        {
            return (long) 1;
        }
        if (m == 0 || n == 0)
        {
            return (long) 0;
        }

        memo.put(new AbstractMap.SimpleEntry<>(m, n), matrixRunner(m - 1, n, memo) + matrixRunner(m, n - 1, memo));
        return memo.get(new AbstractMap.SimpleEntry<>(m, n));
    }

    class Solution
    {
//     public int maxDepth(TreeNode root) {
//         if(null==root)
//             return 0;

        //         return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//     }
        public int maxDepth(TreeNode root)
        {
            if (null == root)
                return 0;

            Stack<Map.Entry<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new AbstractMap.SimpleEntry<>(root, 1));
            int numLevels = 1;

            while (!stack.isEmpty())
            {
                Map.Entry<TreeNode, Integer> temp = stack.pop();
                numLevels = Math.max(numLevels, temp.getValue());
                if (temp.getKey().left != null)
                {
                    stack.push(new AbstractMap.SimpleEntry<>(temp.getKey().left, temp.getValue() + 1));
                }
                if (temp.getKey().right != null)
                {
                    stack.push(new AbstractMap.SimpleEntry<>(temp.getKey().right, temp.getValue() + 1));
                }
            }
            return numLevels;

        }
    }

    public static boolean isValid(String s)
    {
        char ch[] = new char[s.length()];
        int count = 0;
        for (char c : s.toCharArray())
        {
            switch (c)
            {
                case '(':
                    ch[count++] = ')';
                    break;
                case '[':
                    ch[count++] = ']';
                    break;
                case '{':
                    ch[count++] = '}';
                    break;
                default:
                    if (count == 0 || c != ch[--count])
                        return false;
            }
        }
        return count == 0;
    }

//    public static boolean isValid(String s)
//    {
//        Stack<Character> brackets = new Stack<>();
//        char[] arr = s.toCharArray();
//
//        for (int i = 0; i < arr.length; i++)
//        {
//            if (arr[i] == '(' || arr[i] == '{' || arr[i] == '[')
//            {
//                brackets.push(arr[i]);
//            } else
//            {
//                if (!brackets.isEmpty() && (arr[i] == ')' && brackets.peek() != '(' ||
//                        arr[i] == '}' && brackets.peek() != '{' ||
//                        arr[i] == ']' && brackets.peek() != '['))
//                {
//                    return false;
//                } else if (brackets.isEmpty())
//                {
//                    return false;
//                }
//                {
//                    brackets.pop();
//                }
//            }
//        }
//        return brackets.isEmpty()
//    }

//    class Solution
//    {
//        int count = 0;
//
//        public int kthSmallest(TreeNode root, int k)
//        {
//
//            if (root == null)
//                return 0;
//
//            int left = kthSmallest(root.left, k);
//            if (left > 0)
//            {
//                return left;
//            }
//            count += 1;
//            if (count == k)
//            {
//                return root.val;
//            }
//            int right = kthSmallest(root.right, k);
//
//            return right;
//        }
//    }

    public static int maxSubArray(int[] nums)
    {
        int length = nums.length;
        int sliding[] = new int[length];

        sliding[0] = nums[0];
        int max = sliding[0];
        for (int i = 1; i < length; i++)
        {
            sliding[i] = nums[i] + (sliding[i - 1] > 0 ? sliding[i - 1] : 0);
            max = Math.max(max, sliding[i]);
        }
        return max;
    }

    public static double myPow(double x, int n)
    {
        double result;

        if (n == 0)
            return 1;
        if (n > 0)
        {
            result = myPow(x, n / 2);
        } else
        {
            result = myPow(1 / x, n / 2);
        }
        return (n % 2 == 0) ? result * result : result * result * x;
    }


    //TRAPPING RAIN WATER
    public int maxArea(int[] height)
    {
        if (height.length <= 1)
        {
            return 0;
        }
        int maxArea = 0;

        int arrSize = height.length;
        int currLeft = 0;
        int currRight = arrSize - 1;
        while (currLeft < currRight)
        {
            int newArea = Math.min(height[currLeft], height[currRight]) *
                    (currRight - currLeft);
            if (newArea > maxArea)
            {
                maxArea = newArea;
            }

            if (height[currLeft] < height[currRight])
            {
                currLeft++;
            } else
            {
                currRight--;
            }
        }
        return maxArea;
    }


    public static int reverse(int x)
    {
        Boolean inputIsPositive = x > 0;
        int tempResult = 0;
        int result = 0;

        while (x != 0)
        {
            int last = x % 10;
            x = x / 10;
            result = tempResult;
            tempResult = (tempResult * 10) + last;
            int check = (tempResult - last) / 10;
            if (check != result)
            {
                return 0;
            }
        }
        return tempResult;
    }


    public static List<Integer> DFS(HashMap<Integer, List<Integer>> graph)
    {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new Stack<>();

        DFSr(graph, visited, path, 6, 7);
        return path;
    }

    public static Boolean DFSr(HashMap<Integer, List<Integer>> graph, Set<Integer> visited,
                               List<Integer> path, int vertice, int pathEnd)
    {
        visited.add(vertice);
        path.add(vertice);


//        Shortest path using DFS
//        if (graph.get(vertice).contains(pathEnd))
//        {
//            path.add(pathEnd);
//            return true;
//        }

        boolean ended = false;

        //Path using DFS
        if (vertice != pathEnd)
        {
            for (int adj : graph.get(vertice))
            {
                if (!visited.contains(adj))
                    ended = DFSr(graph, visited, path, adj, pathEnd);
                if (ended)
                    break;
            }
        } else
        {
            ended = true;
        }
        return ended;
    }


    public static List<Integer> BFS(HashMap<Integer, List<Integer>> graph)
    {
        Queue<Integer> visited = new LinkedList<>();
        List<Integer> path = new Stack<>();

        BFS(graph, 6, visited, path);
        return path;
    }

    private static void BFS(HashMap<Integer, List<Integer>> graph, int start, Queue<Integer> visited,
                            List<Integer> path)
    {
        int curr;
        visited.add(start);

        while (!visited.isEmpty())
        {
            curr = visited.poll();
            path.add(curr);
            for (int adj : graph.get(curr))
            {
                if (!visited.contains(adj) && !path.contains(adj))
                {
                    visited.add(adj);
                }
            }
        }
    }

    public static int numSubArrayWithSum(int[] A, int Sum)
    {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);

        int result = 0;
        int currSum = 0;
        for (int num : A)
        {
            currSum += num;
            int difference = currSum - Sum;

            result += prefixSum.getOrDefault(difference, 0);

            prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);
        }

        return result;
    }

    public static Stack<String> genMatchingParantheses(int n)
    {
        Stack<String> stack = new Stack<>();
        genMatchingParantheses(2 * n, 0, new Stack<String>(), stack);
        return stack;
    }

    public static void genMatchingParantheses(int num, int numOpening, Stack<String> tempComb, Stack<String> comb)
    {
        if (numOpening < 0 || numOpening > num)
        {
        } else if (num == 0)
        {
            if (numOpening == 0)
            {
                comb.push("" + tempComb);
            }
        } else
        {
            tempComb.push("(");
            genMatchingParantheses(num - 1, numOpening + 1, tempComb, comb);
            tempComb.pop();
            tempComb.push(")");
            genMatchingParantheses(num - 1, numOpening - 1, tempComb, comb);
            tempComb.pop();
        }
    }

    public static int getStationPath(int[] gas, int[] cost)
    {
        int arrLen = gas.length;
        int prevRemaining = 0;

        int candidate = 0;
        int netCost = 0;

        for (int i = 0; i < arrLen; i++)
        {
            netCost += gas[i] - cost[i];

            if (netCost < 0)
            {
                candidate = i + 1;
                prevRemaining += netCost;
                netCost = 0;
            }
        }

        if ((candidate == arrLen) || (netCost + prevRemaining) < 0)
            return -1;
        else
            return candidate;
    }

    public static String findKthPermutation(int n, int k)
    {
        List<Integer> permutation = new ArrayList<>();
        List<Integer> unusedElements = new ArrayList<>();

        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++)
        {
            unusedElements.add(i);
            factorial[i] = i * factorial[i - 1];
        }

        k -= 1;
        while (n > 0)
        {
            int lengthOfPermParts = factorial[n] / n;

            int partToSearch = k / lengthOfPermParts;
            permutation.add(unusedElements.remove(partToSearch));

            n -= 1;
            k %= lengthOfPermParts;
        }

        return permutation.toString();
    }


    public static String minSubstringContainingSmall(String big, String small)
    {
        String result = "";

        //Sizes of the Big and Sub String
        int bigLen = big.length();
        int smallLen = small.length();

        if (smallLen <= bigLen && smallLen > 0)
        {
            //The initial BigString which contains the subString is the full big String given as input
            int subStrStart = 0;
            int subStrEnd = bigLen;

            //Make a map for the subString which maps the characters to their frequencies
            Map<Character, Integer> subFreq = new HashMap<>();
            for (char c : small.toCharArray())
            {
                subFreq.put(c, subFreq.getOrDefault(c, 0) + 1);
            }

            //Now start the Sliding window from the beginning of the Big String
            //Start both left and Right pointers at 0 since the sub string being looked for might be just a character and we want to consider
            //all substrings of the big string which include the single characters in the big string

            int windowLeft = 0;

            //Hashmap for the big String
            Map<Character, Integer> bigFreq = new HashMap<>();

            //Char array containing the big string
            char[] bigString = big.toCharArray();

            //Variable for the number of conditions satisfied for the bigString to contain the subString
            //Big string must contain all the distinct characters in the sub string
            //Their frequencies must match
            int conditions = 0;

            for (int windowRight = 0; windowRight < bigLen; windowRight++)
            {
                bigFreq.put(bigString[windowRight], bigFreq.getOrDefault(bigString[windowRight], 0) + 1);

                if (subFreq.containsKey(bigString[windowRight]) && Objects.equals(subFreq.get(bigString[windowRight]), bigFreq.get(bigString[windowRight])))
                {
                    conditions += bigFreq.get(bigString[windowRight]);
                }

                if (conditions == smallLen)
                {
                    while (!subFreq.containsKey(bigString[windowLeft]) || bigFreq.get(bigString[windowLeft]) > subFreq.get(bigString[windowLeft]))
                    {
                        bigFreq.put(bigString[windowLeft], bigFreq.get(bigString[windowLeft]) - 1);
                        windowLeft++;
                    }

                    if (windowRight - windowLeft + 1 < subStrEnd - subStrStart)
                    {
                        subStrStart = windowLeft;
                        subStrEnd = windowRight;
                    }
                }
            }

            result = big.substring(subStrStart, subStrEnd + 1);
        }
        return result;
    }

    public static int trappingWater(int[] heights)
    {
        int maxWater = 0;
        int maxLeft = heights[0];
        int maxRight = heights[heights.length - 1];

        int left = 0;
        int right = heights.length - 1;

        while (left <= right)
        {
            if (maxLeft < maxRight)
            {
                left++;
                maxLeft = Integer.max(maxLeft, heights[left]);
                maxWater += maxLeft - heights[left];
            } else
            {
                right--;
                maxRight = Integer.max(maxRight, heights[right]);
                maxWater += maxRight - heights[right];
            }
        }

        return maxWater;
    }

    public static int maxRectInHistogram(int[] heights)
    {
        int arrLen = heights.length;
        Stack<Map.Entry<Integer, Integer>> rects = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < arrLen; i++)
        {
            int index = i;

            while (!rects.isEmpty() && heights[i] < rects.peek().getValue())
            {
                int prevIndex = rects.peek().getKey();
                int prevVal = rects.pop().getValue();

                maxArea = Integer.max(maxArea, prevVal * (i - prevIndex));
                index = prevIndex;
            }

            rects.push(new AbstractMap.SimpleEntry<>(index, heights[i]));
        }

        for (Map.Entry<Integer, Integer> entry : rects)
        {
            maxArea = Integer.max(maxArea, entry.getValue());
        }

        return maxArea;
    }

//    public static int[] maxSlidingWindow(int[] array, int windowSize)
//    {
//        if (array == null || windowSize <= 0)
//        {
//            return new int[0];
//        }
//
//        int arrLen = array.length;
//        int[] resultArr = new int[arrLen - windowSize + 1];
//        int resultArrCounter = 0;
//
//        // store index
//        Deque<Integer> q = new ArrayDeque<>();
//
//        for (int i = 0; i < array.length; i++)
//        {
//            // remove numbers out of range of the windowSize
//            while (!q.isEmpty() && q.peek() < i - windowSize + 1)
//            {
//                q.poll();
//            }
//
//
//            // remove smaller numbers in k range as they are useless
//            while (!q.isEmpty() && array[q.peekLast()] < array[i])
//            {
//                q.pollLast();
//            }
//            // q contains index... r contains content
//            q.offer(i);
//
//            if (i - 1 >= windowSize)
//            {
//                resultArr[resultArrCounter++] = array[q.peek()];
//            }
//        }
//        return resultArr;
//    }

    public static int[] maxSlidingWindow(int[] arr, int windowSize)
    {
        if (arr == null || windowSize <= 0)
        {
            return new int[0];
        } else if (windowSize == 1)
        {
            return arr;
        }

        Deque<Map.Entry<Integer, Integer>> window = new ArrayDeque<>();

        int arrLen = arr.length;
        int[] resultArr = new int[arrLen - windowSize + 1];
        int counter = 0;

        for (int i = 0; i < arrLen; i++)
        {
            while (!window.isEmpty() && window.peekLast().getValue() < arr[i])
            {
                window.pollLast();
            }

            window.offer(new AbstractMap.SimpleEntry<>(i, arr[i]));

            if (!window.isEmpty() && window.peek().getKey() < i - windowSize + 1)
            {
                window.poll();
            }

            if (i + 1 >= windowSize)
            {
                resultArr[counter++] = window.peek().getValue();
            }
        }

        return resultArr;
    }

    public static int numberOfPermsOfSInB(String s, String b)
    {
        int smallLength = s.length();
        int bigLength = b.length();
        int result = 0;
        if (bigLength >= smallLength)
        {
            int slidingWindow = 0;
            while (slidingWindow + smallLength - 1 < bigLength)
            {
                if (sortString(b.substring(slidingWindow, slidingWindow + smallLength)).equals(s))
                {
                    result++;
                }
                slidingWindow++;
            }
        }
        return result;
    }

    public static String sortString(String unsortedString)
    {
        char[] stringAsArray = unsortedString.toCharArray();
        Arrays.sort(stringAsArray);
        return new String(stringAsArray);
    }

    public static boolean checkStringHasAllUniqueChars(String s)
    {
        HashMap<Character, Integer> freqCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
        {
            freqCount.put(s.charAt(i), freqCount.getOrDefault(s.charAt(i), 0) + 1);
            if (freqCount.get(s.charAt(i)) > 1)
                return false;
        }

        return true;
    }
//
//        int preorderIndex;
//        Map<Integer, Integer> inorderIndexMap;
//        public TreeNode buildTree(int[] preorder, int[] inorder) {
//            preorderIndex = 0;
//            // build a hashmap to store value -> its index relations
//            inorderIndexMap = new HashMap<>();
//            for (int i = 0; i < inorder.length; i++) {
//                inorderIndexMap.put(inorder[i], i);
//            }
//
//            return arrayToTree(preorder, 0, preorder.length - 1);
//        }
//
//        private TreeNode arrayToTree(int[] preorder, int left, int right) {
//            // if there are no elements to construct the tree
//            if (left > right) return null;
//
//            // select the preorder_index element as the root and increment it
//            int rootValue = preorder[preorderIndex++];
//            TreeNode root = new TreeNode(rootValue);
//
//            // build left and right subtree
//            // excluding inorderIndexMap[rootValue] element because it's the root
//            root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
//            root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
//            return root;
//        }
}


/**
 * Definition for a binary tree node.
 */
public class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode()
    {
    }

    TreeNode(int val)
    {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right)
    {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

