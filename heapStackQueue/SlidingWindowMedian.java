package heapStackQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 480. Sliding Window Median
 * 
 * Median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the 
array to the very right. You can only see the k numbers in the window. Each time the sliding 
window moves right by one position. Your job is to output the median array for each window in 
the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty 
array.
Answers within 10^-5 of the actual value will be accepted as correct.
 * 
 */

// �ҳ�ÿ���ƶ����ڵ���λ��

// ���⣬java��û��ʲô�ⷨ�����������˫�ѽⷨ�����������295Find Median from Data Stream���⣬�ⷨһ��
// C++���ԵĻ�������һ�ֶ��ؼ���+�������ķ���


public class SlidingWindowMedian {
	
	// ��ʱ
    public double[] medianSlidingWindow(int[] nums, int k) {
    	int size = nums.length - k + 1;
        double[] medians = new double[size];
        
    	Deque<Integer> queue = new LinkedList<Integer>();
    	int num = 0;
    	for(int index = 0; index < nums.length; index++) {
    		queue.offer(nums[index]);
    		
    		if(queue.size() == k) {
    			// �õ���λ��
    			Object[] arr = queue.toArray();
    			Arrays.sort(arr);
    			if(k % 2 == 0) {
    				int median = k / 2;
    				medians[num] = (Long.parseLong(arr[median].toString()) + 
    						Long.parseLong(arr[median - 1].toString())) / 2.0;
    			}else {
    				medians[num] = (int)arr[k / 2];
    			}    			
    			num++;
    			queue.poll();
    		}
    		
    	}
    	
    	return medians;
    }
    
    // ˫�ѽⷨ����ҪҪ����ľ��ǻ���ɾ��Ԫ�ص����⣬������õľ����ӳ�ɾ��
    // ����ֻ�����ڶѶ���ʱ����ɾ��������λ�ö���ɾ����ֻҪά�������ѵ�ƽ�⼴��
    public double[] medianSlidingWindow_1(int[] nums, int k) {
    	int size = nums.length - k + 1;
        double[] medians = new double[size];
        
        HashMap<Integer, Integer> useless_node = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>((a,b) -> b.compareTo(a));
        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
        
        int pos = 0;
        // ���Ƚ��г�ʼ����
        for(;pos < k; pos++) max_heap.offer(nums[pos]);
        
        for(int j = 0;j < k/2;j++) min_heap.offer(max_heap.poll());
        int median_index = 0;
        // ����ʹ��for�Ļ����ͻᶪʧnumsֻ��������������Ҳ��2�����
        while(true) {
        	// ��ȡ����λ��
        	medians[median_index++] = ((k & 1) == 0)?((double)max_heap.peek() + 
        			(double)min_heap.peek())/2.0:max_heap.peek();
        	if(pos >= nums.length) break;
        	
        	int balance = 0;
        	// ɾ�������ǻ�����ƽ�⣬���������ƽ���Ƿ�������
        	balance += (nums[pos - k] <= max_heap.peek()?1:-1);
        	
        	// ������ӣ���Ҫ����ƽ��
        	useless_node.put(nums[pos - k], useless_node.getOrDefault(nums[pos - k], 0)+1);
        	if(!max_heap.isEmpty() && nums[pos] <= max_heap.peek()) {
        		--balance;
        		max_heap.offer(nums[pos++]);
        	}else{
        		++balance;
        		min_heap.offer(nums[pos++]);
        	}
        	
        	if(balance > 0) {
        		--balance;
        		max_heap.offer(min_heap.poll());
        	}else if(balance < 0) {
        		++balance;
        		min_heap.offer(max_heap.poll());
        	}
        	
        	// ���ɾ��Ԫ�ؾ��ڶѶ�����ôɾ��,Ҳ��Ҫ��map��ɾ��
        	while(!max_heap.isEmpty() && useless_node.getOrDefault(max_heap.peek(), 0) > 0) {
        		int temp = max_heap.poll();
        		useless_node.put(temp, useless_node.get(temp) - 1);
        	}
        	
        	while(!min_heap.isEmpty() && useless_node.getOrDefault(min_heap.peek(), 0) > 0) {
        		int temp = min_heap.poll();
        		useless_node.put(temp, useless_node.get(temp) - 1);
        	}

        }
                
        return medians;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var swm = new SlidingWindowMedian();
//		int[] nums = {2147483647,2147483647};
//		int k = 2;
		
		int[] nums = {1,2,3,4,2,3,1,4,2};
		int k = 3;
		
		double[] medians = swm.medianSlidingWindow_1(nums, k);
        for(double one_median:medians) {
        	System.out.print(one_median + ",");
        }
	}

}
