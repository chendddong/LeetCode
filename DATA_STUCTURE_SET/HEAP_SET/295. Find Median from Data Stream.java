/*
    Median is the middle value in an ordered integer list. If the size of the list
    is even, there is no middle value. So the median is the mean of the two middle value.

    Examples: 
    [2,3,4] , the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
    For example:

    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3) 
    findMedian() -> 2
 */

///////////////////////////
// StraightForward Sort  //                 Got a TLE though
///////////////////////////

public class MedianFinder {
    List<Integer> slide;
    public MedianFinder() {
        slide = new ArrayList<>();        
    }
    
    public void addNum(int num) {
        slide.add(num);
        Collections.sort(slide);        
    }
    
    public double findMedian() {
        if (slide.size() % 2 == 0) {
            return (slide.get(slide.size() / 2 - 1) + slide.get(slide.size() / 2)) / 2.0;
        } else {
            return (double) slide.get(slide.size() / 2);
        }             
    }
}

///////////////
// Two Heaps //
///////////////

/* There are ways to override the comparator !!! */
public class MedianFinder {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    /* Method 1: */
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1,
        Collections.reverseOrder());
    /* Method 2: */
    // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1,
    //     new Comparator<Integer>() {
    //         @Override
    //         public int compare(Integer x, Integer y) {
    //             return y - x;
    //         }
    //     });    
    /* Method 3: */
    // class newComparator implements Comparator<Integer> {
    //     @Override
    //     public int compare(Integer x, Integer y) {
    //         return y - x;
    //     }        
    // }
    // newComparator reverse = new newComparator();
    // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1,reverse);

    public MedianFinder() {}
    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }     
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return (double) maxHeap.peek();
    }
}



