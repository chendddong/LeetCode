/**
 * Implement the following operations of a queue using stacks.
 */

/*
    push(x) -- Push element x to the back of queue.
    pop() -- Removes the element from in front of queue.
    peek() -- Get the front element.
    empty() -- Return whether the queue is empty.

    Notes:
    You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.

    Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.

    You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */

///////////////////////////////////////////////
// Throw elements from 1 to 2 and pop from 2 //
///////////////////////////////////////////////

public class MyQueue {
    private ArrayDeque<Integer> stack1;
    private ArrayDeque<Integer> stack2;

    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /* Only push the element to 1 stack. */
    public void push(int x) {
        stack1.push(x);
    }

    /* If stack2 is empty, throw all the elements from stack1 to stack2 */
    public int pop() {
        if (stack2.isEmpty()) {
            this.shiftOneToTwo();
        }
        return stack2.pop(); /* Pop from stack2 */   
    }

    public int peek() {
        if (stack2.isEmpty()) {
            this.shiftOneToTwo();
        }
        return stack2.peek(); /* Pop from stack2 */           
    }

    private void shiftOneToTwo() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

