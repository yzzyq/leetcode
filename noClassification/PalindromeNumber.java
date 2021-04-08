package noClassification;
import java.util.LinkedList;
import java.util.List;

//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
//
//Example 1:
//
//Input: 121
//Output: true
//Example 2:
//
//Input: -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes 121-. 
//Therefore it is not a palindrome.
//Example 3:
//
//Input: 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
//Follow up:
//
//Coud you solve it without converting the integer to a string?

// ��������
// ���ܽ���������ת�����ַ�������


public class PalindromeNumber {
	
	// ʱ��Ϳռ������ʺܵͣ����д�������������ٷ�֮��ʮ
//    public boolean isPalindrome(int x) {
//    	if(x < 0) return false;
//    	if(x >= 0 && x < 10) return true;
//    	List<Integer> x_list = new LinkedList<Integer>();
//    	
//    	while(x >= 10) {
//    		x_list.add(x % 10);
//    		x /= 10;
//    	}
//        x_list.add(x);
//        int x_len = x_list.size();
//        int end_index = (x_len + 1) / 2;
//    	if(x_len % 2 == 0) {
//    		end_index = x_len / 2;
//    	}
//    	
//    	for(int index = 0; index < end_index; index++) {
//			if(x_list.get(index) != x_list.get(x_len-index - 1)) {
//				return false;
//			}
//		}
//        return true;
//    }
    
    // �������ĸĽ���֮ǰһֱ������ֵ�����ʵֻҪ��תһ�¾����ˣ�Ҳ����һ���Ӿͳ���ȫ��
	// ÿ�ξͳ���10����
    public boolean isPalindrome(int x) {
    	if(x < 0) return false;
    	if(x >= 0 && x < 10) return true;
    	
    	int reverse_x = 0;
    	int temp_x = x;
    	
    	while(temp_x > 0) {
    		reverse_x = reverse_x*10 + temp_x % 10;
    		temp_x /= 10;
    	}
    	
    	
        return reverse_x == x;
    }

	public static void main(String[] args) {
		int x = 121;
		PalindromeNumber pn = new PalindromeNumber();
		boolean isPalind = pn.isPalindrome(x);
		System.out.println(isPalind);

	}

}
