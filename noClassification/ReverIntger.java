package noClassification;
import java.math.BigInteger;
import java.util.ArrayList;

//Given a 32-bit signed integer, reverse digits of an integer.
//
//Example 1:
//
//Input: 123
//Output: 321
//Example 2:
//
//Input: -123
//Output: -321
//Example 3:
//
//Input: 120
//Output: 21
//Note:
//Assume we are dealing with an environment which could only store integers within the 32-bit 
//signed integer range: [鈭�231,  231 鈭� 1]. For the purpose of this problem, 
//assume that your function returns 0 when the reversed integer overflows.

//鏈闇�瑕佹敞鎰忕殑鍦版柟鏈夊緢澶�

public class ReverIntger {
	
	// 閫昏緫绠�鍗曪紝浣嗘槸瀹炵幇澶嶆潅锛�
    public int reverse(int x) {
    	if(x < 10 && x > -10) return x;
        long tearDown = x;
        ArrayList<Long> all_nums = new ArrayList();
        boolean is_negative = false;
        // Int鐨勬渶鍊肩殑缁濆鍊兼棤娉曞緱鍒帮紝-2147483648锛�-2147483648 ~ 2147483647
        // 杩欐槸鍥犱负姝ｈ礋浣跨敤琛ョ爜鏉ュ疄鐜板緱銆備細鍙樻垚0
        tearDown = Math.abs((long)tearDown);
        while(tearDown / 10 > 0) {
        	long remain_num = tearDown % 10;
        	all_nums.add(remain_num);
        	tearDown /= 10;
        }
        all_nums.add(tearDown);
        StringBuilder temp_str = new StringBuilder();
        for(int index = 0; index < all_nums.size(); index++) {
        	if(index != 0 || all_nums.get(0) != 0) {
        		temp_str.append(all_nums.get(index).toString());
        	}
        }
        // 杞疆涔嬪悗鐨勫�煎彲鑳戒細澶т簬int涓殑鏈�澶у�硷紝鍥犺�岄渶瑕佺敤long鏉ュ鐞�
        long output_int = Long.parseLong(temp_str.toString());
        if(output_int > Integer.MAX_VALUE) return 0;
        return x<0?(int)-output_int:(int)output_int;
    }  

	public static void main(String[] args) {
		int input_int = -2147483648;
		ReverIntger ri = new ReverIntger();
		int output_int = ri.reverse(input_int);
		System.out.println(output_int);
        
	}

}
