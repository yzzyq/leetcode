package heapStackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 84. Largest Rectangle in Histogram
 * 
 * Given n non-negative integers representing the histogram's bar height where the width 
 * of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10
 * 
 * @author yzzyq
 *
 */


public class LargestRectangleinHistogram {

    // ö�٣������ƽ�,��������ö�ٿ�
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1) return heights[0];
        if(heights.length == 0) return 0;
        int len = heights.length;
        int max_len = 0;
        
        for(int one_index = 0; one_index < len; one_index++) {
        	int curr_min = heights[one_index];
        	for(int two_index = one_index; two_index < len; two_index++) {
        		if(curr_min > heights[two_index]) {
        			curr_min = heights[two_index];
        		}
        		max_len = Math.max(max_len, curr_min*(two_index - one_index + 1));
        	}
        }
        
        return max_len;
    }
    
    // �����ƽ⣬ö�ٸߣ������ص��˼�룬����ĵ���ջ���Ǹ������˼��Ľ���
    public int largestRectangleArea_1(int[] heights) {
    	if(heights.length == 1) return heights[0];
        if(heights.length == 0) return 0;
        int len = heights.length;
        int max_len = 0;
        
        // �ҵ�ÿ����������������
        for(int index = 0; index < len; index++) {
        	
        	int temp = heights[index];
        	int left = index;
        	int right = index;
        	
        	// ���������������Ļ����ͻ����������ӵ�������
        	while(left - 1 >= 0 && heights[left - 1] >= temp) {
        		--left;
        	}
        	
        	while(right + 1 < len && heights[right + 1] >= temp) {
        		++right;
        	}
        	
        	max_len = Math.max(max_len, temp*(right-left + 1));
        	
        }
        
        return max_len;
    }
    
    // ����ջ
    public int largestRectangleArea_2(int[] heights) {
    	if(heights.length == 1) return heights[0];
        if(heights.length == 0) return 0;
        int len = heights.length;
        int max_len = 0;
        
        
        Deque<Integer> stack = new LinkedList<Integer>();
        // ��ȫ������ջ��
        for(int index = 0; index < len; ++index) {
        	while(!stack.isEmpty() && heights[index] < heights[stack.peek()]) {
        		int curr_height = heights[stack.pop()];
        		
        		while(!stack.isEmpty() && curr_height == heights[stack.peek()]) {
        			stack.pop();
        		}
        		int curr_width = 0;
        		if(stack.isEmpty()) {
        			curr_width = index;
        		}else {
        			curr_width = index - stack.peek() - 1;
        		}
        		max_len = Math.max(max_len, curr_width*curr_height);
        	}
        	stack.push(index);
        }
        // ��Ҫ��ȫ���������֮������ջ������Ԫ�ص����
        while(!stack.isEmpty()) {
        	int curr_height = heights[stack.pop()];
        	
        	while(!stack.isEmpty() && curr_height == heights[stack.peek()]) {
    			stack.pop();
    		}
        	int curr_width = 0;
        	if(stack.isEmpty()) {
        		curr_width = len;
        	}else {
        		curr_width = len - stack.peek() - 1;
        	}
        	max_len = Math.max(max_len, curr_width*curr_height);
        }
        
        return max_len;
    }
    
    // ��������Ҫ����ջ���ͱ���֮��ջ�л���Ԫ�ص������,�����ջ�м����ڱ��Ļ�����������
    public int largestRectangleArea_3(int[] heights) {
    	if(heights.length == 1) return heights[0];
        if(heights.length == 0) return 0;
        int len = heights.length;
        int max_len = 0;
        
        int[] new_heights = new int[len+2];
        new_heights[0] = 0;
        System.arraycopy(heights, 0, new_heights, 1, len);
        len += 2;
        new_heights[len - 1] = 0;
        
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(0);
        for(int index = 1; index < len; index++) {
        	while(new_heights[index] < new_heights[stack.peek()]) {
        		int curr_height = new_heights[stack.pop()];
        		int curr_width = index - stack.peek() - 1;
        		max_len = Math.max(max_len, curr_height*curr_width);
        	}
        	stack.push(index);
        }
        
        return max_len;
    }
    
    


	public static void main(String[] args) {
		int[] heights = {2,1,5,6,2,3};
		var leih = new LargestRectangleinHistogram();
        int max_len = leih.largestRectangleArea_3(heights);
        System.out.println(max_len);
	}

}
