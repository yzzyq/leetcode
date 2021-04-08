package noClassification;
import java.util.List;
import java.util.Set;

//Implement strStr().
//
//Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
//Example 1:
//
//Input: haystack = "hello", needle = "ll"
//Output: 2
//Example 2:
//
//Input: haystack = "aaaaa", needle = "bba"
//Output: -1

//Clarification:
//
//What should we return when needle is an empty string? This is a great question to ask 
//during an interview.
//
//For the purpose of this problem, we will return 0 when needle is an empty string. 
//This is consistent to C's strstr() and Java's indexOf().


// 从字符串中找出所需要的字符串

public class ImpleStr {
	
	// 复习
	public int strStr_two(String haystack, String needle) {
		if(haystack == null || needle == null) return 0;
		int index = 0;
		int needle_length = needle.length();
		int hay_length = haystack.length();
		while(needle_length <= hay_length - index) {
			if(needle.equals(haystack.substring(index, index+needle_length))) {
				return index;
			}
			index++;
		}
		
		return -1;
	}
	
	
	// 很快，超过了百分之一百，空间占用过大
	// 只要使用一般的匹配方法就就行
    public int strStr(String haystack, String needle) {
    	if(needle.equals("")) return 0;
    	for(int index = 0, needleLen = needle.length(); 
    		index < haystack.length(); index++) {
    		// 查看字符串位数已经不够了
    		if(index + needleLen > haystack.length() ) {
    			break;
    		}
    		// 当==是原始类型的话，那么比较的就是值
    		// 如果是复合类型，比较的就是地址
    		if(needle.equals(haystack.substring(index, index + needleLen))) {
    			return index;
    		}
    	}
        return -1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String haystack = "abac";
		String needle = "ac";
		ImpleStr is = new ImpleStr();
		int strIndex = is.strStr_two(haystack, needle);		
        System.out.println(strIndex);
	}

}
