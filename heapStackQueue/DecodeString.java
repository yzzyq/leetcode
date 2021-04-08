package heapStackQueue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 394. Decode String
 * 
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is 
being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are 
well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are 
only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 * 
 * 
 * @author yzzyq
 *
 */

// 对字母进行编码

public class DecodeString {
	
	// 使用栈进行处理,写了一个多小时都没写出来，虽然有思路，但是就是不停的出错，处处都是细节都需要考虑好
	// 下面就是我当时写的，超时版本的，一直跑不通
	public String decodeString_abandon(String s) {		
		Deque<Character> stack_char = new LinkedList<Character>();
		char[] s_char = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		String string_behind = "";
		int index = 0;
		
        for(char one_char:s_char) {
        	if(one_char != ']') {
        		stack_char.push(one_char);
        		
        		if(one_char == '[') index++;
        		
        	}else {
        		StringBuffer temp = new StringBuffer();
        		char peek_first = stack_char.pop();
        		while(peek_first != '[') {
        			temp.insert(0,peek_first);
        			peek_first = stack_char.pop();
        		}
        		System.out.println(stack_char.peek());
        	    index--;
        	    
        	    StringBuffer num_char = new StringBuffer();
				while(!stack_char.isEmpty() && Character.isDigit(stack_char.peek())) {
					num_char.insert(0, stack_char.pop());
        	    }
        	    
        		int num = Integer.valueOf(num_char.toString());
        		System.out.println(num);
        		StringBuffer one = new StringBuffer();
        		for(int i = 0; i < num; i++) {
        			one.append(temp.toString() + string_behind);
        		}

        		if(index > 0) {
        			string_behind = one.toString();
        		}else {
        			while(!stack_char.isEmpty()) {
        				one.insert(0,stack_char.pop());
        	        }
        			sb.append(one);
        			
        		}
        		
        	}
        }
        System.out.println(sb.toString());
        StringBuffer temp = new StringBuffer();
        while(!stack_char.isEmpty()) {
        	temp.insert(0,stack_char.pop());
        }
		
        return sb.append(temp).toString();
    }
	
	
	// 判断是否到了第几个
	int ptr;
	
	public String decodeString(String s) {
		Deque<String> stack_char = new LinkedList<String>();
		ptr = 0;
		
        while(ptr < s.length()) {
        	char one_char = s.charAt(ptr);
        	if(Character.isDigit(one_char)) {
        		String digit = getDigit(s);
        		stack_char.push(digit);
        		
        	}else if(Character.isLetter(one_char) || one_char == '[') {
        		stack_char.push(String.valueOf(s.charAt(ptr++)));    	
        	}else {
        		++ptr;
        		StringBuffer temp = new StringBuffer();
        		String peek_first = stack_char.pop();
        		while(!peek_first.equals("[")) {
        			temp.insert(0,peek_first);
        			peek_first = stack_char.pop();
        		}
        	    
        	    
        		int num = Integer.valueOf(stack_char.pop());
        		System.out.println(num);
        		StringBuffer one = new StringBuffer();
        		for(int i = 0; i < num; i++) {
        			one.append(temp.toString());
        		}
        		stack_char.push(one.toString());
        		
        	}
        }
        
        StringBuffer sb = new StringBuffer();
        while(!stack_char.isEmpty()) {
        	sb.append(stack_char.pollLast());
        }
		
        return sb.toString();
	}

	private String getDigit(String s) {
		StringBuffer sb = new StringBuffer();
		while(Character.isDigit(s.charAt(ptr))) {
			sb.append(s.charAt(ptr++));
		}
		return sb.toString();
	}

	
    // 还可以使用递归,递归的思想就是依次递归，如果是数字，那么直接就把这个符号展开变成字符串，不然继续递归
	int pos;
	String s_process;
	
	public String decodeString_1(String s) {
		pos = 0;
		s_process = s;
		return processString();
	}
	
	private String processString() {
		if(pos > s_process.length() - 1 || s_process.charAt(pos) == ']') {
			return "";
		}
		String current = "";
		if(Character.isDigit(s_process.charAt(pos))) {
			int num = Integer.valueOf(getDigit_1(s_process));
			// 去除左括号
			++pos;
			String get_str = processString();
			++pos;
			while(num-- > 0) {
				current = current + get_str;
			}
			
		}else {
			current = String.valueOf(s_process.charAt(pos++));
		}
		
		
		return current+processString();
	}
	
	private String getDigit_1(String s) {
		StringBuffer sb = new StringBuffer();
		while(Character.isDigit(s.charAt(pos))) {
			sb.append(s.charAt(pos++));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "2[abc]3[cd]ef";
		DecodeString ds = new DecodeString();
		String decode_str = ds.decodeString_1(s);
        System.out.println(decode_str);
	}

}
