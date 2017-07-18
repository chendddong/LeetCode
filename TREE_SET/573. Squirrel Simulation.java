/*
    There's a tree, a squirrel, and several nuts. Positions are represented by
    the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
 */

/*
    Example 1:
    Input: 
    Height : 5
    Width : 7
    Tree position : [2,2]
    Squirrel : [4,4]
    Nuts : [[3,0], [2,5]]
    Output: 12
    Explanation:

    Note:
    All given positions won't overlap.
    The squirrel can take at most one nut at one time.
    The given positions of nuts have no order.
    Height and width are positive integers. 3 <= height * width <= 10,000.
    The given positions contain at least one nut, only one tree and one squirrel.
 */

public class Solution {
    
    /*
        For the first visited nut, the saving obtained, given by dd, is the difference between the distance between the tree and the current nut & the distance between the current nut and the squirrel. This is because for this nut, we need not travel from the tree to the nut, but need to travel an additional distance from the squirrel's original position to the nut.
     */
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int tot_dist = 0, d = Integer.MIN_VALUE;
        for (int[] nut: nuts) {
            tot_dist += (distance(nut, tree) * 2);
            d = Math.max(d, distance(nut, tree) - distance(nut, squirrel));
        }
        return tot_dist - d;
    }
    public int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}