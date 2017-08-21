/*
    There are some trees, where each tree is represented by (x,y) coordinate in
    a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.
 */

/*
    Example 1:
    Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
    Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
    Explanation:

    Example 2:
    Input: [[1,2],[2,2],[4,2]]
    Output: [[1,2],[2,2],[4,2]]
    Explanation:

    Even you only have trees in a line, you need to use rope to enclose them. 
    Note:

    All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
    All input integers will range from 0 to 100.
    The garden has at least one tree.
    All coordinates are distinct.
    Input points have NO order. No order required for output.
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

//////////////////////
// Jarvis Algorithm //              Time O(m * n)
//////////////////////

public class Solution {
    public int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }
    public boolean inBetween(Point p, Point i, Point q) {
        boolean a = i.x >= p.x && i.x <= q.x || i.x <= p.x && i.x >= q.x;
        boolean b = i.y >= p.y && i.y <= q.y || i.y <= p.y && i.y >= q.y;
        return a && b;
    }
    public List < Point > outerTrees(Point[] points) {
        HashSet < Point > hull = new HashSet < > ();
        if (points.length < 4) {
            for (Point p: points)
                hull.add(p);
            return new ArrayList<Point>(hull);
        }
        int left_most = 0;
        for (int i = 0; i < points.length; i++)
            if (points[i].x < points[left_most].x)
                left_most = i;
        int p = left_most;
        do {
            int q = (p + 1) % points.length;
            for (int i = 0; i < points.length; i++) {
                if (orientation(points[p], points[i], points[q]) < 0) {
                    q = i;
                }
            }
            for (int i = 0; i < points.length; i++) {
                if (i != p && i != q && orientation(points[p], points[i], points[q]) == 0 && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }
            hull.add(points[q]);
            p = q;
        }
        while (p != left_most);
        return new ArrayList<Point>(hull);
    }
}


////////////////////
// Monotone Chain //
////////////////////

/*
    Time: O(nlogn) 
    Space: O(n)
 */

public class Solution {
    public int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }
    public List < Point > outerTrees(Point[] points) {
        Arrays.sort(points, new Comparator < Point > () {
            public int compare(Point p, Point q) {
                return q.x - p.x == 0 ? q.y - p.y : q.x - p.x;
            }
        });
        Stack < Point > hull = new Stack < > ();
        for (int i = 0; i < points.length; i++) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }
        hull.pop();
        for (int i = points.length - 1; i >= 0; i--) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }
        return new ArrayList < > (new HashSet < > (hull));
    }
}



