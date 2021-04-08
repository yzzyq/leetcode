package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * 
 * Given a collection of integers that might contain duplicates, nums, return all 
 * possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 * 
 * @author yzzyq
 *
 */

// �������ظ���

public class SubsetsII {
	
	// ��һ���ǿ����ȥ�����ظ�����������ǰһ�����Ա���֮��Ž�ȥ
	// ���ֻ��������Ļ����ϼ���contains�����޷�ͨ��[4,4,4,1,4],��ʱ���ȥ�ؾ���Ҫ���ǽ�������
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> sub = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);
    	getSub(sub, new ArrayList<Integer>(), 0, nums);
    	
    	return sub;
    }

	private void getSub(List<List<Integer>> sub, ArrayList<Integer> temp, int start, int[] nums) {
		if(!sub.contains(temp)) {
			sub.add(new ArrayList<Integer>(temp));
		}
		
		for(int index = start; index < nums.length; index++) {
			temp.add(nums[index]);
			getSub(sub, temp, index + 1, nums);
			temp.remove(temp.size() - 1);
		}
		
	}
	
	// �����ǻ���ɲ�������ж�ǰ��Ԫ���Ƿ������Ԫ����ͬ
	public List<List<Integer>> subsetsWithDup_1(int[] nums) {
    	List<List<Integer>> sub = new ArrayList<List<Integer>>();
    	// ��������ͬԪ�ض���һ��
    	Arrays.sort(nums);
    	getSub_1(sub, new ArrayList<Integer>(), 0, nums);
    	
    	return sub;
    }

	private void getSub_1(List<List<Integer>> sub, ArrayList<Integer> temp, int start, int[] nums) {
	    sub.add(new ArrayList<Integer>(temp));
		
		for(int index = start; index < nums.length; index++) {
			if(index > start && nums[index] == nums[index - 1]) continue;
			temp.add(nums[index]);
			getSub_1(sub, temp, index + 1, nums);
			temp.remove(temp.size() - 1);
		}
		
	}

	public static void main(String[] args) {
		int[] nums = {4,1,4};

		SubsetsII ss = new SubsetsII();
		List<List<Integer>> sub = ss.subsetsWithDup_1(nums);
		System.out.println(sub);
	}

}
