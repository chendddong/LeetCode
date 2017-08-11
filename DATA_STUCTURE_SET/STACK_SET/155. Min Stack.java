/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 */

/*
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
    Example:
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> Returns -3.
    minStack.pop();
    minStack.top();      --> Returns 0.
    minStack.getMin();   --> Returns -2.
 */

///////////////////////////////
// Store the min ACCORDINGLY //
///////////////////////////////

public class MinStack {

    /** initialize your data structure here. */
    private ArrayDeque<Integer> stack;
    private ArrayDeque<Integer> minStack;
        
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    /* Always push to stack; Push the min to the minStack though */    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }
    
    public void pop() {
        minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

///////////////////////////////////
// Save a bit when store the min //
///////////////////////////////////

public class MinStack {

    /** initialize your data structure here. */
    private ArrayDeque<Integer> stack;
    private ArrayDeque<Integer> minStack;
        
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            if (minStack.peek() >= x) {
                minStack.push(x);
            }
        }
    }
    
    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();    
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

////////////////////
// Only one Stack //
////////////////////

public class MinStack {

    int min = Integer.MAX_VALUE; /* Need to keep track of the min value */ 
    ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

    public void push(int x) {
        /*
            Only push the old minimum value when the current minimum value
            changes after pushing the new value x.       
         */ 
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        /* 
            if pop operation could result in the changing of the current minimum
            value, pop twice and change the current minimum value to the last minimum value.
         */
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}