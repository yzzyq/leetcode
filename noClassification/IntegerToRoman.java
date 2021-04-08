package noClassification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//Symbol       Value
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
//
//Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//I can be placed before V (5) and X (10) to make 4 and 9. 
//X can be placed before L (50) and C (100) to make 40 and 90. 
//C can be placed before D (500) and M (1000) to make 400 and 900.
//Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
//
//Example 1:
//
//Input: 3
//Output: "III"
//Example 2:
//
//Input: 4
//Output: "IV"
//Example 3:
//
//Input: 9
//Output: "IX"
//Example 4:
//
//Input: 58
//Output: "LVIII"
//Explanation: L = 50, V = 5, III = 3.
//Example 5:
//
//Input: 1994
//Output: "MCMXCIV"
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


public class IntegerToRoman {
	// 将数字转化为罗马符号
	public String intToRoman_two(int num) {
		List<Integer> integer_num = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
		List<String> roman_String = List.of("M","CM","D","CD", "C","XC","XL","X","IX","IV","I");
		StringBuilder roman = new StringBuilder();
		for(int index = 0; index < integer_num.size(); index++) {
			while(num >= integer_num.get(index)) {
				roman.append(roman_String.get(index));
				num -= integer_num.get(index);
			}
		}
		
		return roman.toString();
		
	}
	
	
    public String intToRoman(int num) {
//    	List<String> roman_string = List.of("I", "V", "X", "L", "C", "D", "M");
//    	List<Integer> roman_int = List.of(1,5,10,50,100,500,1000);
    	
    	List<String> roman_string = List.of("M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I");
    	List<Integer> roman_int = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
    	StringBuilder roman = new StringBuilder();
    	for(int index=0; index < roman_int.size(); index++) {
    		while(num >= roman_int.get(index)) {
    			roman.append(roman_string.get(index));
    			num -= roman_int.get(index);
    		}
    	}
    	
    	return roman.toString();
        
    }

	public static void main(String[] args) {
		int input_int = 400;
		IntegerToRoman itr = new IntegerToRoman();
		String roman = itr.intToRoman(input_int);
        System.out.println(roman);
	}

}
