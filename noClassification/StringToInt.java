package noClassification;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Implement atoi which converts a string to an integer.
//
//The function first discards as many whitespace characters as necessary until the 
//first non-whitespace character is found. Then, starting from this character, 
//takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
//and interprets them as a numerical value.
//
//The string can contain additional characters after those that form the integral number, 
// which are ignored and have no effect on the behavior of this function.
//
//If the first sequence of non-whitespace characters in str is not a valid integral number, 
//or if no such sequence exists because either str is empty or it contains only whitespace 
//characters, no conversion is performed.
//
//If no valid conversion could be performed, a zero value is returned.
//
//Note:
//
//Only the space character ' ' is considered as whitespace character.
//Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [鈭�231,  231 鈭� 1]. If the numerical value is out of the range of representable values, INT_MAX (231 鈭� 1) or INT_MIN (鈭�231) is returned.
//Example 1:
//
//Input: "42"
//Output: 42
//Example 2:
//
//Input: "   -42"
//Output: -42
//Explanation: The first non-whitespace character is '-', which is the minus sign.
//             Then take as many numerical digits as possible, which gets 42.
//Example 3:
//
//Input: "4193 with words"
//Output: 4193
//Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
//Example 4:
//
//Input: "words and 987"
//Output: 0
//Explanation: The first non-whitespace character is 'w', which is not a numerical 
//             digit or a +/- sign. Therefore no valid conversion could be performed.
//Example 5:
//
//Input: "-91283472332"
//Output: -2147483648
//Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
//             Thefore INT_MIN (鈭�231) is returned.



// 鏈変簡璐熷彿锛屼絾鏄笉鑳藉繕璁�+


public class StringToInt {
	
    public int myAtoi(String str) {
    	if(str == " ") return 0;
    	// 鍘绘帀澶村熬绌虹櫧
    	str = str.trim();
    	char[] to_char = str.toCharArray();
    	String num = "+,-,1,2,3,4,5,6,7,8,9,0";
    	Set num_set = new HashSet(Arrays.asList(num.split(",")));
        int index = 0;
        List num_list = new LinkedList<Integer>();
        boolean is_neg = false;
        int symbol_num = 0;
        boolean num_front = false;
        while(index < to_char.length && num_set.contains(to_char[index] + "")) {
        	if(to_char[index] == '-') {
        		if(num_front) {
        			break;
        		}
        		is_neg = true;
        		symbol_num++;
        	}else if(to_char[index] != '+'){
        		// 鍙�傜敤浜�0-9鏁板瓧锛岃繕鍙互Integer.parseInt(to_char[index] + "")
        	    num_list.add(to_char[index] - '0');
        	    num_front = true;
        	}else {
        		if(num_front) {
        			break;
        		}
        		symbol_num++;
        	}

        	index++;
        }
        if(symbol_num == 2) return 0;
        System.out.println(num_list);
        long atoi_num = 0;
        int list_size = num_list.size();
        for(int num_index = 0; num_index < list_size; num_index++) {
        	int one_num = (int) num_list.get(num_index);
        	atoi_num = (long) (atoi_num + one_num*Math.pow(10, list_size - num_index - 1));
        }
        if(!is_neg && atoi_num > Integer.MAX_VALUE) {
        	return Integer.MAX_VALUE;
        }
        if(is_neg && -atoi_num < Integer.MIN_VALUE) {
        	return Integer.MIN_VALUE;
        }
        return (int) (is_neg?-atoi_num:atoi_num);
    }

	public static void main(String[] args) {
//		String input_str = "   -42";
//		String input_str = "4193 with words";
//		String input_str = "words and 987";
//		String input_str = "0-2";
		String input_str = "-13+8";
		StringToInt sti = new StringToInt();
		int conversion_num = sti.myAtoi(input_str);
        System.out.println(conversion_num);
	}

}
