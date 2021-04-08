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


// �Ծ��ͼ������ʾ��ͼ��֮�������������ַ���˳��
// ��򵥵������ҳ����ɣ�֮����ݹ�������
public class ZigZag {
	
//    public String convert(String s, int numRows) {
//    	if(s.length() <= numRows || numRows == 1) return s;
//    	
//    	// ֱ��ʹ���ַ���Ч�ʵ��£���Ϊÿ��ƴ�ӡ���ȡ���滻�������ᴴ��һ���µ�String����
//    	// һ�㶼����StringBuilder�����ַ�������ƴ�ӡ���ȡ������StringBuilder�̲߳���ȫ
//    	// StringBuffer���̰߳�ȫ��
//        String convert_zigzag = "";
//        
//    	// ����ѭ������
//        for(int one_num = 0; one_num < numRows; one_num++) {
//        	int start_index = numRows - 1;
//        	// �ַ����޸ġ����Ȼᴴ��һ��StringBuilder����ε���StringBuilder��APPEND����
//        	// ������StringBuilder��toString�������ؽ��
//        	// ��˵ĵ��ã�������һЩ����Ĳ���������һЩ��ʱ�Ķ��󣬴Ӷ�����Ч�ʵ���
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
	
    // �����ĸĽ�, ��������Ҫ���ӵ�˼����ͬ�����ֻ��ͨ������ȡ�ַ�
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
    	// ��ʼ����ʱ������Ĭ�ϴ�С�ģ���СΪ16
    	// StringBuffer��append������������ͬ������ʵ���˶��߳��µ��̰߳�ȫ
    	// ������ʵ���У�����������StringBuffer����Ϊ���޷���֤д�����ݵ�˳�򣬲�����ʵ����
    	StringBuilder convert_zigzag = new StringBuilder();
    	for(StringBuilder ret:s_rows) {
    		convert_zigzag.append(ret);
    	}
    	// ����newһ�����󣬽�StringBuilder�����е�value�����˿���
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
