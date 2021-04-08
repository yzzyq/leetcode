package heapStackQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 150. Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

 * @author yzzyq
 *
 */

// 求逆波兰表达式得结果

public class EvaluateReversePolishNotation {

	// 使用栈
    public int evalRPN(String[] tokens) {
        
    	Deque<Integer> int_stack = new LinkedList<Integer>();
    	
    	List<String> sign = Arrays.asList("+","-","*","/");
    	
    	for(String one_tokens:tokens) {
    		if(sign.contains(one_tokens)) {
    			int two = int_stack.pop();
    			int one = int_stack.pop();
    			int temp = 0;
    			
    			if(one_tokens.equals("+")) temp = one+two;
    			
    			if(one_tokens.equals("-")) temp = one-two;
    			
    			if(one_tokens.equals("*")) temp = one*two;
    			
    			if(one_tokens.equals("/")) temp = one/two;
    			
    			int_stack.push(temp);
    		}else {
    			int_stack.push(Integer.valueOf(one_tokens));
    		}
    	}
    	
    	return int_stack.pop();
    }
	
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+", "3", "*"};
		var erpn = new EvaluateReversePolishNotation();
        int sum_notation = erpn.evalRPN(tokens);
        System.out.println(sum_notation);
	}

}
