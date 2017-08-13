import java.util.ArrayDeque;

/**
 * This is to test and understand all the useful method in the ArrayDeque.
 */

public class ArrayDequeTest {
    public static void main(String args[]) {
        /* The order we want */
        int[] arr = new int[]{6,7,3,4,5,1,9,8,2}; 
        /* Use it as a stack */
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        /**
         * push(E) (addFirst(E))
         *
         * Pushes an element onto the stack represented by this deque
         * In other words, inserts the element at the front of this deque.
         * This method is equivalent to addFirst(E).
         */
        
        //                      Deque:
        //
        //          |       2 8 9 1 5 4 3 7 6      |
        //        front                           end
        //        first                           Last
        //                      Stack:
        //
        //                 2 8 9 1 5 4 3 7 6       |
        //        front                           end
        //        first                           Last

        System.out.println("Push all the numbers to the stack: ");
        stack.push(6);
        stack.push(7);
        stack.addFirst(3);
        stack.addFirst(4);
        stack.push(5);
        stack.push(1);
        stack.addFirst(9);
        stack.addFirst(8);
        stack.push(2);
        System.out.println(stack.toString());


        /**
         *  E pop() (E removeFirst())
         *  Pops an element from the stack represented by this deque. In other
         *  words, removes and returns the first element of this deque This
         *  method is equivalent to removeFirst().
         */
        
        //                      Deque:
        //
        //          |             1 5 4 3 7 6      |
        //        front                           end
        //        first                           Last
        //                      Stack:
        //
        //                       1 5 4 3 7 6       |
        //        front                           end
        //        first                           Last

        System.out.println("Pop 3 numbers out of the stack: ");
        stack.pop();
        stack.removeFirst();
        stack.pop();
        System.out.println(stack.toString());

        /**
         *  E peek() (E peekFirst())
         *  Retrieves, but does not remove, the head of the queue represented
         *  by this deque, or returns null if this deque is empty.This method
         *  is equivalent to peekFirst().
         */
        
        //                      Deque:
        //
        //          |             1 5 4 3 7 6      |
        //        front                           end
        //        first                           Last
        //                      Stack:
        //
        //                       1 5 4 3 7 6       |
        //        front                           end
        //        first                           Last
        //        
        System.out.println("Peeking the stack: ");
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());


        /* Use it as a Queue */
        ArrayDeque<Integer> queue = stack;

        /**
         * offer(E) (offerLast(E))
         * Inserts the specified element at the end of this deque.
         * This method is equivalent to offerLast(E).
         */
        
        //                      Deque:
        //
        //          |             1 5 4 3 7 6 2 8  |
        //        front                           end
        //        first                           Last
        //                      Queue:
        //
        //         |              1 5 4 3 7 6 2 8  |
        //        front                           end
        //        first                           Last

        System.out.println("Offer 2 and 8 to the Queue: ");
        queue.offer(2);
        queue.offerLast(8);
        System.out.println(queue.toString());

        /**
         * poll() (pollFirst())
         * Retrieves and removes the head of the queue represented by this deque
         * (in other words, the first element of this deque), or returns null if
         * this deque is empty. This method is equivalent to pollFirst().
         */
        
        //                      Deque:
        //
        //          |              4 3 7 6 2 8  |
        //        front                        end
        //        first                        Last
        //                      Queue:
        //
        //         |              4 3 7 6 2 8  |
        //        front                       end
        //        first                       Last

        System.out.println("Poll 1 and 5 to the Queue: ");
        queue.poll();
        queue.pollFirst();
        System.out.println(queue.toString());
    }
}
