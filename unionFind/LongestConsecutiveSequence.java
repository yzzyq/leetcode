package unionFind;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers, find the length of the longest consecutive 
 * elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
Therefore its length is 4.
 * 
 * 
 * @author yzzyq
 *
 */

// 一个未排序的整数数组，找出最长连续序列的长度

public class LongestConsecutiveSequence {
	
	// 这题不难，但是细节过多，需要好好思考，是不是有重复的，重复的该怎么处理
	// 直接进行排序，之后依次检查，这是最容易想到的思路
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
    	if(nums.length == 1) return 1;
        
        int max_len = 1;
        
        Arrays.sort(nums);
        int left = 0;
        int right = 1;
        
        while(right < nums.length) {
        	// 依次检查是否连续
        	if(nums[right] - nums[left] == 1 || nums[right] - nums[left] == 0) {
        		int current_len = 1;
        		while(right < nums.length && (nums[right] - nums[left] == 1 || nums[right] - nums[left] == 0)) {
        			if(nums[right] - nums[left] == 1) {
        				current_len++;
        			}
        			right++;
        			left++;
        		}
        		max_len = Math.max(max_len, current_len);
        	}else {
        		right++;
        		left++;
        	}
        	
        }
        
        return max_len;
    }
    
    // 使用哈希表，就是不用枚举，直接看集合中是否有连续的就行,这种效率就最高的
    // java中的set就是哈希表实现的
    public int longestConsecutive_1(int[] nums) {
    	if(nums.length == 0) return 0;
    	if(nums.length == 1) return 1;
    	
    	Set<Integer> num_set = new HashSet<Integer>();
    	for(int one:nums) {
    		num_set.add(one);
    	}
    	
    	int long_streak = 1;
    	
    	for(int one:num_set) {
    		// 不包含前置的才需要进行列举
    		if(!num_set.contains(one - 1)) {
    			int temp = 1;
    			
    			while(num_set.contains(one + 1)) {
    				++temp;
    				++one;
    			}
    			
    			long_streak = Math.max(long_streak, temp);
    		}
    	}
    	
    	return long_streak;
    	
    }
    
    
    
    // 这题还可以使用并查集来解决，不过比不上哈希方法
    Map<Integer, Integer> parent_map;
    Map<Integer, Integer> size_map;
    int max = 1;
    public int longestConsecutive_2(int[] nums) {
    	if(nums.length == 0) return 0;
    	if(nums.length == 1) return 1;
    	
    	parent_map = new HashMap<Integer, Integer>();
    	size_map = new HashMap<Integer, Integer>();
    	
    	for(int one:nums) {
    		parent_map.put(one, one);
    		size_map.put(one, 1); 
    	}
    	
    	for(int one:nums) {
    		if(parent_map.containsKey(one - 1)) {
    			union(one,one-1);
    		}
    	}
    	
    	return max;
    }
    	
    public void union(int node1, int node2) {
    	int parent_node1 = getParent(node1);
    	int parent_node2 = getParent(node2);
    	
    	if(parent_node1 != parent_node2) {
    		int sum_size = size_map.get(parent_node1) + size_map.get(parent_node2);
    		if(size_map.get(parent_node1) > size_map.get(parent_node2)) {
    			parent_map.put(parent_node2, parent_node1);
    			size_map.put(parent_node1, sum_size);
    		}else {
    			parent_map.put(parent_node1, parent_node2);
    			size_map.put(parent_node2, sum_size);
    		}
    		
    		max = Math.max(max, sum_size);
    	}
    	 	
    }
    	
    public int getParent(int node) {
    	if(parent_map.get(node) != node) {
    		int temp = getParent(parent_map.get(node));
    		parent_map.put(node, temp);
    	}
    	return parent_map.get(node);	
    }
    	

    

	public static void main(String[] args) {
		int[] nums = {0,2,1,1};
		var longCon = new LongestConsecutiveSequence();
        int leng_long = longCon.longestConsecutive_2(nums);
        System.out.println(leng_long);
	}

}
