package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 46. Permutations
 * 
 * Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 * 
 * 
 * @author yzzyq
 *
 */

// ������ȫ����һ�㶼��ʹ�û��ݷ�

public class Permutations {
	
	// ���ݷ�,�ҵ����ֻ��ݷǳ��ļ�,����һ����������¼�Ƿ��Ѿ������ʹ�
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> temp_list = new ArrayList<Integer>();
        if(nums.length == 0) return result;
        boolean[] isVisited = new boolean[nums.length];
        Arrays.fill(isVisited, false);
        getResult(result, isVisited, temp_list, nums);
        return result;
        
    }

	private void getResult(List<List<Integer>> result, boolean[] isVisited, List<Integer> temp_list, int[] nums) {
		if(temp_list.size() == nums.length) {
			result.add(new ArrayList<>(temp_list));
			return ;
		}
		
		for(int index = 0; index < nums.length; index++) {
			if(isVisited[index]) continue;
			temp_list.add(nums[index]);
			isVisited[index] = true;
			getResult(result, isVisited, temp_list, nums);
			isVisited[index] = false;
			temp_list.remove(temp_list.size() - 1);
		}
		
	}
	
	// ����һ�ֻ��ݵ�˼�룬����Ҫһ���������洢�Ƿ񱻷��ʣ�ֱ�Ӿͽ����Ϳ���
	public List<List<Integer>> permute_1(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> temp_list = new ArrayList<Integer>();
    	for(int one:nums) {
    		temp_list.add(one);
    	}
        if(nums.length == 0) return result;
        // ��Ҫ��һ����������¼������λ��
        getResult_1(result, 0, temp_list, nums);
        return result;
        
    }

	private void getResult_1(List<List<Integer>> result, int first, List<Integer> temp_list, int[] nums) {
		if(first == nums.length - 1) {
			result.add(new ArrayList<>(temp_list));
			return ;
		}
		
		for(int index = first; index < nums.length; index++) {
			Collections.swap(temp_list, first, index);
			getResult_1(result, first + 1, temp_list, nums);
			// �����������ǽ�������
			Collections.swap(temp_list, index, first);
		}
		
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3};
        var per = new Permutations();
        List<List<Integer>> result = per.permute(nums);
        System.out.println(result);
	}

}
