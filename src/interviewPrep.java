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

        while (left < right)
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

    public int[] maxSlidingWindow(int[] array, int windowSize)
    {
        if (array == null || windowSize <= 0)
        {
            return new int[0];
        }

        int arrLen = array.length;
        int[] resultArr = new int[arrLen - windowSize + 1];
        int resultArrCounter = 0;

        // store index
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < array.length; i++)
        {
            // remove numbers out of range of the windowSize
            while (!q.isEmpty() && q.peek() < i - windowSize + 1)
            {
                q.poll();
            }

            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && array[q.peekLast()] < array[i])
            {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i - 1 >= windowSize)
            {
                resultArr[resultArrCounter++] = array[q.peek()];
            }
        }
        return resultArr;
    }
}
