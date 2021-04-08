package recursion;

/**
 * 344. Reverse String
 * Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 * 
 */

// 将字符串反转

public class ReverseString {
	
	// 交换即可
	public void reverseString(char[] s) {
		if(s.length < 2) return;
		
		int start = 0;
		int end = s.length - 1;
		
		while(start < end) {
			char temp = s[start];
			s[start] = s[end];
			s[end] = temp;
			start++;end--;
		}
    }
	
	// 递归方式来解决
	public void reverseString_1(char[] s) {
		if(s.length < 2) return;
		reverseChar(s, 0, s.length - 1);
	}

	private void reverseChar(char[] s, int left, int right) {
		if(left >= right) return;
		
		reverseChar(s, left+1, right-1);
		char temp = s[left];
		s[left] = s[right];
		s[right] = temp;
	}

	public static void main(String[] args) {
		char[] s = {'h','e','l','l','o'};
		ReverseString rs = new ReverseString();
		rs.reverseString_1(s);
		for(char one:s) {
			System.out.print(one);
		}
	}

}
