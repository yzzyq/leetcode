package heapStackQueue;

/**
 * 155. Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in 
 * constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

Methods pop, top and getMin operations will always be called on non-empty stacks.
 * 
 * @author yzzyq
 *
 */

// 

public class MinStack {
	
	// ʹ��һ������ջ���������Сֵ
	private int size = 10;
	private int top_pos = 0;
	private int[] stack;
	private int[] temp;
	
	public MinStack() {
		stack = new int[size];
		temp = new int[size];
		temp[0] = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
    	// ջ������
    	if(top_pos >= size - 1) {
    		addSize();
    	}
    	stack[top_pos] = x;
    	temp[top_pos + 1] = Math.min(temp[top_pos], x); 
        top_pos++;  
    }
    
    public void addSize() {
    	int[] temp_s = stack;
    	int[] temp_t = temp;
    	size *= 2;
		stack = new int[size];
		temp = new int[size];
		for(int index = 0; index <= top_pos; index++) {
			stack[index] = temp_s[index];
			temp[index] = temp_t[index];
		}
    }
    
    public void pop() {
    	if(top_pos == 0) {
    		System.out.println("ջ��");
    		return ;
    	}
    	top_pos--;
    }
    
    public int top() {
    	return stack[top_pos - 1];
    }
    
    public int getMin() {
        return temp[top_pos];
    }
    
    
    // ��������ö���ռ�Ļ�����ô����
    // ջ�оͲ��ܴ��Ԫ�أ�ֻ�ܴ������Сֵ�Ĳ�ֵ��������һ�����������Сֵ
	// �����������int��Խ�����⣬
//    private int size = 10;
//	private int top_pos = 0;
//	private int min_value = 0;
//	private int[] stack;
//	
//	// ʹ��һ������ջ���������Сֵ
//	public MinStack() {
//		stack = new int[size];
//    }
//    
//    public void push(int x) {
//    	// ջ������
//    	if(top_pos > size - 1) {
//    		addSize();
//    	}
//    	if(top_pos == 0) {
//    		min_value = x;
//    		stack[top_pos] = 0;
//    	}else {
//    		int compare = x - min_value;
//    		stack[top_pos] = compare;
//    		min_value = compare < 0?x:min_value;
//    	}
//    	top_pos++;
//    }
//    
//    public void addSize() {
//    	int[] temp_s = stack;
//    	size *= 2;
//		stack = new int[size];
//		for(int index = 0; index < top_pos; index++) {
//			stack[index] = temp_s[index];
//		}
//    }
//    
//    public void pop() {
//    	if(top_pos == 0) {
//    		System.out.println("ջ��");
//    		return ;
//    	}
//    	// ��ջ��ʱ��Ҫע����Сֵ�ı仯
//    	if(stack[top_pos - 1] < 0) {
//    		min_value -= stack[top_pos - 1];
//    	}
//    	top_pos--;
//    }
//    
//    public int top() {
//    	// ����ֱ�Ӽ��Ϸ��أ���Ϊ���ջ���պ�����Сֵ�Ļ����������Ǵ����
//    	return stack[top_pos - 1] < 0?min_value:stack[top_pos - 1] + min_value;
//    }
//    
//    public int getMin() {
//    	return min_value;
//    }
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
