package heapStackQueue;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {
	// 这种方法就是汉诺塔那种，小时候经常玩
//	Deque<Integer> one_stack;
//	Deque<Integer> two_stack;
//	int num;
//	
//	public MyQueue() {
//    	one_stack = new LinkedList<Integer>();
//    	two_stack = new LinkedList<Integer>();
//    	// 记录栈中的个数
//    	num = 0;
//    }
//    
//    /** Push element x to the back of queue. */
//	// 这个方法是便于插入，但是在弹出的时候，很麻烦，并且取出第一个元素很麻烦
//	// 不如让插入麻烦，另外俩个方法就简单了
//    public void push(int x) {
//    	one_stack.push(x);
//    	++num;
//    }
//    
//    /** Removes the element from in front of queue and returns that element. */
//    public int pop() {
//    	if(two_stack.isEmpty() && !one_stack.isEmpty()) {
//    		while(!one_stack.isEmpty()) {
//        		int temp = one_stack.pop();
//        		two_stack.push(temp);
//        	}
//    	}
//    	int first_element = two_stack.pop();
//    	
//    	while(!two_stack.isEmpty()) {
//    		int temp = two_stack.pop();
//    		one_stack.push(temp);
//    	}
//    	--num;
//    	
//	    return first_element;
//
//    }
//    
//    /** Get the front element. */
//    public int peek() {
//    	if(two_stack.isEmpty() && !one_stack.isEmpty()) {
//    		while(!one_stack.isEmpty()) {
//        		int temp = one_stack.pop();
//        		two_stack.push(temp);
//        	}
//    	}
//    	int first_element = two_stack.peek();
//    	
//    	while(!two_stack.isEmpty()) {
//    		int temp = two_stack.pop();
//    		one_stack.push(temp);
//    	}
//    	
//	    return first_element;
//    }
//    
//    /** Returns whether the queue is empty. */
//    public boolean empty() {
//    	if(0 == this.num) return true;
//        return false;
//    }
    
    
	
    // 除了上面的方法，还可以将俩个栈都放入
    Deque<Integer> one_stack;
	Deque<Integer> two_stack;
	int front;
	
	public MyQueue() {
    	one_stack = new LinkedList<Integer>();
    	two_stack = new LinkedList<Integer>();
    	// 记录栈中的个数
    }
    
    /** Push element x to the back of queue. */
	// 这个方法是便于插入，但是在弹出的时候，很麻烦，并且取出第一个元素很麻烦
	// 不如让插入麻烦，另外俩个方法就简单了
    public void push(int x) {
    	if(one_stack.isEmpty()) {
    		front = x;
    	}
    	one_stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(two_stack.isEmpty()) {
    		while(!one_stack.isEmpty()) {
    			two_stack.push(one_stack.pop());
    		}
    	}
        return two_stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if(two_stack.isEmpty()) {
    		return front;
    	}
    	return two_stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
    	return one_stack.isEmpty() && two_stack.isEmpty();
    }
    
    
    
    
    

}
