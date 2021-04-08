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

// 找出每次移动窗口的中位数

// 这题，java并没有什么解法，大多数都是双堆解法，建议先理解295Find Median from Data Stream那题，解法一样
// C++语言的话，还有一种多重集合+迭代器的方法


public class SlidingWindowMedian {
	
	// 超时
    public double[] medianSlidingWindow(int[] nums, int k) {
    	int size = nums.length - k + 1;
        double[] medians = new double[size];
        
    	Deque<Integer> queue = new LinkedList<Integer>();
    	int num = 0;
    	for(int index = 0; index < nums.length; index++) {
    		queue.offer(nums[index]);
    		
    		if(queue.size() == k) {
    			// 得到中位数
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
    
    // 双堆解法，主要要解决的就是滑动删除元素的问题，这里采用的就是延迟删除
    // 就是只在它在堆顶的时候再删除，其他位置都不删除，只要维持俩个堆的平衡即可
    public double[] medianSlidingWindow_1(int[] nums, int k) {
    	int size = nums.length - k + 1;
        double[] medians = new double[size];
        
        HashMap<Integer, Integer> useless_node = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>((a,b) -> b.compareTo(a));
        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
        
        int pos = 0;
        // 首先进行初始设置
        for(;pos < k; pos++) max_heap.offer(nums[pos]);
        
        for(int j = 0;j < k/2;j++) min_heap.offer(max_heap.poll());
        int median_index = 0;
        // 这里使用for的话，就会丢失nums只有俩个数，滑动也是2的情况
        while(true) {
        	// 提取出中位数
        	medians[median_index++] = ((k & 1) == 0)?((double)max_heap.peek() + 
        			(double)min_heap.peek())/2.0:max_heap.peek();
        	if(pos >= nums.length) break;
        	
        	int balance = 0;
        	// 删除操作是会引起不平衡，但是这个不平衡是反过来的
        	balance += (nums[pos - k] <= max_heap.peek()?1:-1);
        	
        	// 进行添加，需要保持平衡
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
        	
        	// 如果删除元素就在堆顶，那么删除,也需要从map中删除
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
