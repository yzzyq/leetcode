package heapStackQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * 239. Sliding Window Maximum
 * Given an array nums, there is a sliding window of size k which is moving from the very 
 * left of the array to the very right. You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
 * 
 * 
 * @author yzzyq
 *
 */

// ÿ�λ��������е����ֵ

public class SlidingWindowMaximum {
	
	// ʱ��ͦ�̵ã�97%���������ĵô洢�࣬���⻹��һ���Ż�˼�룬���õ���˫�����
    public int[] maxSlidingWindow(int[] nums, int k) {
        int max_length = nums.length - k + 1;
    	int[] max_arrays = new int[max_length];
    	if(nums.length == 0 || k == 0) return max_arrays;
    	
    	int left = 0;
    	int right = Math.min(k - 1, nums.length - 1);
    	
    	int temp_max = nums[0];
    	int index = 0;
    	for(int i = 0; i <= right; i++) {
    		temp_max = Math.max(temp_max, nums[i]);
    	}
    	
    	max_arrays[index++] = temp_max;
    	right++;
    	for(; right < nums.length; right++) {
    		if(nums[left] == temp_max) {
    			temp_max = nums[++left];
    			for(int i = left; i <= right; i++) {
    				temp_max = Math.max(temp_max, nums[i]);
    			}
    		}else {
    			left++;
    		}
    		temp_max = Math.max(temp_max, nums[right]);
    		max_arrays[index++] = temp_max;
    		
    	}
    	
        return max_arrays;    	
    }
    
    
    // ʹ��˫����������
    public int[] maxSlidingWindow_1(int[] nums, int k) {
    	if(nums.length == 0 || nums.length < 2) return nums;
    	
    	LinkedList<Integer> two_queue = new LinkedList<Integer>();
    	int[] result = new int[nums.length - k + 1];
    	
    	for(int index = 0; index < nums.length; index++) {
    		while(!two_queue.isEmpty() && nums[two_queue.peekLast()] < nums[index]) {
    			two_queue.pollLast();
    		}
    		
    		two_queue.addLast(index);
    		
    		// ��鴰���ײ��������Ƿ��ڶ�����
            if(two_queue.peek() <= index - k) {
            	two_queue.poll();
            }
    		
            //����Ƿ���Ҫ���浱ǰ�������ֵ
            if(index + 1 >= k) {
            	result[index + 1 - k] = nums[two_queue.peek()];
            }
    		
    	}
    	return result;
    }
    

	public static void main(String[] args) {
//		var swm = new SlidingWindowMaximum();
//        int[] nums = {1,3,1,2,0,5};
//		int k = 3;
//		int[] max_arrays = swm.maxSlidingWindow_1(nums, k);
//		for(int one_max:max_arrays) {
//			System.out.print(one_max + ",");
//		}
		
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue.toString());
		queue.poll();
		System.out.println(queue.toString());
		Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
		
		int e = 5;
		int old = 16;
		System.out.println(1 << 4);
		System.out.println(e & old);
		
	}

}
