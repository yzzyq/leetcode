package noClassification;
//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
//(you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//And then read line by line: "PAHNAPLSIIGYIR"
//
//Write the code that will take a string and make this conversion given a number of rows:
//
//string convert(string s, int numRows);
//Example 1:
//
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
//Example 2:
//
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//
//P     I    N
//A   L S  I G
//Y A   H R
//P     I


// 以锯齿图像来显示出图像，之后按行重新排列字符的顺序
// 最简单的先是找出规律，之后根据规律再算
public class ZigZag {
	
//    public String convert(String s, int numRows) {
//    	if(s.length() <= numRows || numRows == 1) return s;
//    	
//    	// 直接使用字符串效率低下，因为每次拼接、截取、替换操作都会创建一个新的String对象
//    	// 一般都是用StringBuilder来对字符串进行拼接、截取，但是StringBuilder线程不安全
//    	// StringBuffer是线程安全的
//        String convert_zigzag = "";
//        
//    	// 进行循环设置
//        for(int one_num = 0; one_num < numRows; one_num++) {
//        	int start_index = numRows - 1;
//        	// 字符串修改。首先会创建一个StringBuilder，其次调用StringBuilder的APPEND方法
//        	// 最后调用StringBuilder的toString方法返回结果
//        	// 如此的调用，会生成一些额外的操作，多了一些临时的对象，从而导致效率低下
//        	convert_zigzag += s.charAt(one_num);
//        	int current_index = 2*start_index + one_num;
//        	int midde_index = current_index - 2*one_num;
////        	System.out.println(current_index);
//        	while(current_index < s.length() || midde_index < s.length()) {
//        		if(one_num > 0 && one_num < numRows-1) {
//        			convert_zigzag += s.charAt(current_index - 2*one_num);
//        		}
//        		if(current_index < s.length()) convert_zigzag += s.charAt(current_index);
//        		start_index += numRows - 1;
//        		current_index = 2*start_index + one_num;
//        		midde_index = current_index - 2*one_num;
//        	}
//        }
//    	
//    	return convert_zigzag;
//    }
	
    // 方法的改进, 与上面需要复杂的思考不同。这个只是通过行来取字符
    public String convert(String s, int numRows) {
    	if(s.length() <= numRows || numRows == 1) return s;
        StringBuilder[] s_rows = new StringBuilder[numRows];
    	char[] s_chars = s.toCharArray();
    	int row = 0, step = 0;
    	for(char one_char:s_chars) {
    		if(s_rows[row] == null) {
    			s_rows[row] = new StringBuilder();
    		}
    		s_rows[row].append(one_char);
    		
    		if(row == 0) {
    			step = 1;
    		}else if(row == numRows - 1) {
    			step = -1;
    		}
    		
    		row += step;
    		
    	}
    	// 初始化的时候是有默认大小的，大小为16
    	// StringBuffer在append方法上增加了同步锁，实现了多线程下的线程安全
    	// 但是在实际中，根本不会用StringBuffer，因为它无法保证写入内容的顺序，不具有实用性
    	StringBuilder convert_zigzag = new StringBuilder();
    	for(StringBuilder ret:s_rows) {
    		convert_zigzag.append(ret);
    	}
    	// 重新new一个对象，将StringBuilder对象中的value进行了拷贝
        return convert_zigzag.toString();
    	
    }
    
	

	public static void main(String[] args) {
		String s = "PAHNAPLSIIGYIR";
		int num_rows = 3;
		ZigZag zz = new ZigZag();
		String convert_zigzag = zz.convert(s, num_rows);
		System.out.println(convert_zigzag);

	}

}
