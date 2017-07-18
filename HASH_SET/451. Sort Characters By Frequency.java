/**
 * Given a string, sort it in decreasing order based on the frequency of 
 * characters.
 */

/*
    Example 1:

    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:

    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:

    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
 */

public class Solution {
    /* We need to sort the HashMap Entry and print the Key */
    public String frequencySort(String s) {
        /* Put all the String characters to the HashMap */   
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        } 

        /* Override the Comparing method */
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> e1, 
                                   Map.Entry<Character, Integer> e2) {
                    return e2.getValue() - e1.getValue();
                }
            }
        );

        /* Add all the map entry to the pq !!! */
        pq.addAll(map.entrySet());

        /* Printout the new string */
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            int size = (int) e.getValue(); /* Don't forget to cast */
            for (int i = 0; i < size; i++) {
                sb.append(e.getKey());
            }
        }

        return sb.toString();

    }
}
