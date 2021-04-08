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


// ���ַ������ҳ�����Ҫ���ַ���

public class ImpleStr {
	
	// ��ϰ
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
	
	
	// �ܿ죬�����˰ٷ�֮һ�٣��ռ�ռ�ù���
	// ֻҪʹ��һ���ƥ�䷽���;���
    public int strStr(String haystack, String needle) {
    	if(needle.equals("")) return 0;
    	for(int index = 0, needleLen = needle.length(); 
    		index < haystack.length(); index++) {
    		// �鿴�ַ���λ���Ѿ�������
    		if(index + needleLen > haystack.length() ) {
    			break;
    		}
    		// ��==��ԭʼ���͵Ļ�����ô�Ƚϵľ���ֵ
    		// ����Ǹ������ͣ��Ƚϵľ��ǵ�ַ
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
