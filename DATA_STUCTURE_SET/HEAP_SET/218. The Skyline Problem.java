//////////
// Heap //      Use PriorityQueue as a heap O(n log n)
//////////


/* 
    Classic :
    Comparator // Heap // Sweep Line // Traversal // Construct Answer
 */

public class Solution {

    class Edge {
        int pos;
        int height;
        boolean isStart;

        public Edge(int pos, int height, boolean isStart) {
            this.pos = pos;
            this.height = height;
            this.isStart = isStart;
        }
    }
    /* How to sort the Edge */
    class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge arg1, Edge arg2) {
            Edge l1 = (Edge) arg1;
            Edge l2 = (Edge) arg2;

            /* Not in the same spot, sort by pos asc */
            if (l1.pos != l2.pos) 
                return compareInteger(l1.pos, l2.pos);

            /* Same spot, all starts, sort by height desc */
            if (l1.isStart && l2.isStart) {
                return compareInteger(l2.height, l1.height);
            }

            /* Same spot, all ends, sort by height asc */
            if (!l1.isStart && !l2.isStart) {
                return compareInteger(l1.height, l2.height);
            }

            /* One of them is start, sort asc */
            return l1.isStart ? -1 : 1;
        }

        /* Compare asc */
        int compareInteger(int a, int b) {
            return a <= b ? -1 : 1;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        /* Edge */
        if (buildings == null || buildings.length == 0
            || buildings[0].length == 0) {
            return res;
        }

        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int[] building : buildings) {
            Edge startEdge = new Edge(building[0], building[2], true);
            edges.add(startEdge);
            Edge endEdge = new Edge(building[1], building[2], false);
            edges.add(endEdge);
        }
        Collections.sort(edges, new EdgeComparator());

        PriorityQueue<Integer> heap = new PriorityQueue<>(1, Collections.reverseOrder());

        int[] now = null;
        for (Edge edge : edges) {
            if (edge.isStart) {
                /* Only add when it is increasing */
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    now = new int[2];
                    now[0] = edge.pos;
                    now[1] = edge.height;
                    res.add(now);
                }
                heap.add(edge.height); /* Add to heap last */
            } else {
                heap.remove(edge.height); /* Remove from heap first */
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    if (heap.isEmpty()) { /* 0 as a place holder */
                        now = new int[2];
                        now[0] = edge.pos;
                        now[1] = 0;
                    } else { /* Need the higher height */
                        now = new int[2];
                        now[0] = edge.pos;
                        now[1] = heap.peek();  
                    }
                    res.add(now);
                }
            }
        }

        return res;
    }
}


//////////////
// HashHeap //                  O(n)
//////////////

public class Solution {

    class HashHeap {
        ArrayList<Integer> heap;
        String mode;
        int size_t;
        HashMap<Integer, Node> hash;

        class Node {
            public Integer id;
            public Integer num;

            Node(Node now) {
                id = now.id;
                num = now.num;
            }

            Node(Integer first, Integer second) {

                this.id = first;
                this.num = second;
            }
        }

        public HashHeap(String mod) {
            heap = new ArrayList<Integer>();
            mode = mod;
            hash = new HashMap<Integer, Node>();
            size_t = 0;
        }

        public int peek() {
            return heap.get(0);
        }

        public int size() {
            return size_t;
        }

        public Boolean isEmpty() {
            return (heap.size() == 0);
        }

        int parent(int id) {
            if (id == 0) {
                return -1;
            }
            return (id - 1) / 2;
        }

        int lson(int id) {
            return id * 2 + 1;
        }

        int rson(int id) {
            return id * 2 + 2;
        }

        boolean comparesmall(int a, int b) {
            if (a <= b) {
                if (mode == "min")
                    return true;
                else
                    return false;
            } else {
                if (mode == "min")
                    return false;
                else
                    return true;
            }

        }

        void swap(int idA, int idB) {
            int valA = heap.get(idA);
            int valB = heap.get(idB);

            int numA = hash.get(valA).num;
            int numB = hash.get(valB).num;
            hash.put(valB, new Node(idA, numB));
            hash.put(valA, new Node(idB, numA));
            heap.set(idA, valB);
            heap.set(idB, valA);
        }

        public Integer poll() {
            size_t--;
            Integer now = heap.get(0);
            Node hashnow = hash.get(now);
            if (hashnow.num == 1) {
                swap(0, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > 0) {
                    siftdown(0);
                }
            } else {
                hash.put(now, new Node(0, hashnow.num - 1));
            }
            return now;
        }

        public void add(int now) {
            size_t++;
            if (hash.containsKey(now)) {
                Node hashnow = hash.get(now);
                hash.put(now, new Node(hashnow.id, hashnow.num + 1));

            } else {
                heap.add(now);
                hash.put(now, new Node(heap.size() - 1, 1));
            }

            siftup(heap.size() - 1);
        }

        public void delete(int now) {
            size_t--;
            Node hashnow = hash.get(now);
            int id = hashnow.id;
            int num = hashnow.num;
            if (hashnow.num == 1) {

                swap(id, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > id) {
                    siftup(id);
                    siftdown(id);
                }
            } else {
                hash.put(now, new Node(id, num - 1));
            }
        }

        void siftup(int id) {
            while (parent(id) > -1) {
                int parentId = parent(id);
                if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                    break;
                } else {
                    swap(id, parentId);
                }
                id = parentId;
            }
        }

        void siftdown(int id) {
            while (lson(id) < heap.size()) {
                int leftId = lson(id);
                int rightId = rson(id);
                int son;
                if (rightId >= heap.size()
                    || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
                    son = leftId;
                } else {
                    son = rightId;
                }
                if (comparesmall(heap.get(id), heap.get(son)) == true) {
                    break;
                } else {
                    swap(id, son);
                }
                
                id = son;
            }
        }
    }
    
    class Edge {
        int pos;
        int height;
        boolean isStart;

        public Edge(int pos, int height, boolean isStart) {
            this.pos = pos;
            this.height = height;
            this.isStart = isStart;
        }
    }

    class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge arg1, Edge arg2) {
            Edge l1 = (Edge) arg1;
            Edge l2 = (Edge) arg2;
            if (l1.pos != l2.pos)
                return compareInteger(l1.pos, l2.pos);
            if (l1.isStart && l2.isStart) {
                return compareInteger(l2.height, l1.height);
            }
            if (!l1.isStart && !l2.isStart) {
                return compareInteger(l1.height, l2.height);
            }
            return l1.isStart ? -1 : 1;
        }

        int compareInteger(int a, int b) {
            return a <= b ? -1 : 1;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();


        if (buildings == null || buildings.length == 0
            || buildings[0].length == 0) {
            return res;
        }
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int[] building : buildings) {
            Edge startEdge = new Edge(building[0], building[2], true);
            edges.add(startEdge);
            Edge endEdge = new Edge(building[1], building[2], false);
            edges.add(endEdge);
        }
        Collections.sort(edges, new EdgeComparator());

        HashHeap heap = new HashHeap("max");

        int[] now = null;
        for (Edge edge : edges) {
            if (edge.isStart) {
                /* Only add when it is increasing */
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    now = new int[2];
                    now[0] = edge.pos;
                    now[1] = edge.height;
                    res.add(now);
                }
                heap.add(edge.height); /* Add to heap last */
            } else {
                heap.delete(edge.height); /* Remove from heap first */
                if (heap.isEmpty() || edge.height > heap.peek()) {
                    if (heap.isEmpty()) { /* 0 as a place holder */
                        now = new int[2];
                        now[0] = edge.pos;
                        now[1] = 0;
                    } else { /* Need the higher height */
                        now = new int[2];
                        now[0] = edge.pos;
                        now[1] = heap.peek();  
                    }
                    res.add(now);
                }
            }
        }

        return res;
    }


}