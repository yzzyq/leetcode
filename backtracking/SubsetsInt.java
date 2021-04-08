package backtracking;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//Note: The solution set must not contain duplicate subsets.
//
//Example:
//
//Input: nums = [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]

// 回溯法的专题

public class SubsetsInt{
	
	public static <T> List<T> deepCopy(List<T> src) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteOut);
        ) {
            outputStream.writeObject(src);
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                 ObjectInputStream inputStream = new ObjectInputStream(byteIn);
            ) {
                return (List<T>) inputStream.readObject();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }
	
	// 遍历列表中的元素，每一次有新元素，就将列表中的子集都添加这个元素形成新的子集
	// 这里对于Java有一个大问题，就是列表如何进行深拷贝，对于java中的深拷贝，实现起来都很复杂
    public List<List<Integer>> subsets(int[] nums) {
    	 
    	List<List<Integer>> sub_list = new LinkedList();

    	for(int index = nums.length - 1; index >= 0; index--) {
    		int sub_list_len = sub_list.size();
    		for(int list_index = 0;list_index < sub_list_len; list_index++) {
    			List<Integer> in_sub_list = deepCopy(sub_list.get(list_index));
    			in_sub_list.add(nums[index]);
    			sub_list.add(in_sub_list);
    			System.out.println(sub_list_len);
    		}	
    		List<Integer> one_sub_list = new LinkedList<Integer>();
    		one_sub_list.add(nums[index]);
    		sub_list.add(one_sub_list);
    		System.out.println(sub_list);
    		
    	}
    	List<Integer> empty_sub_list = new LinkedList<Integer>();
    	sub_list.add(empty_sub_list);
    	return sub_list;
        
    }
    
    // 用位图法，进行迭代输出
    // 就是使用位图，之后检查位图中哪一位是1即可
    public List<List<Integer>> subsets_1(int[] nums) {
    	List<List<Integer>> sub = new ArrayList<List<Integer>>();
    	int len = nums.length;
    	for(int index = 0; index < (1 << len); index++) {
    		List<Integer> temp = new ArrayList<Integer>();
    		for(int num = 0; num < len; num++) {
    			// 先将数进行移位，移位完成之后就利用位运算符进行对比
    			if(((index >> num) & 1) == 1) {
    				temp.add(nums[num]);
    			}
    		}
    		System.out.println(temp);
    		sub.add(temp);
    	}
    	return sub;
    }
    
    // 回溯法
    public List<List<Integer>> subsets_2(int[] nums) {	
    	List<List<Integer>> sub = new ArrayList<List<Integer>>();
    	List<Integer> temp = new ArrayList<Integer>();
    	
    	getSubSet(sub,temp,0,nums);
    	
    	return sub;
    }

	private void getSubSet(List<List<Integer>> sub, List<Integer> temp, int start, int[] nums) {
		sub.add(new ArrayList(temp));
		
		for(int index = start; index < nums.length; index++) {
			// 做出选择
			temp.add(nums[index]);
			// 递归
			getSubSet(sub,temp,index+1,nums);
			// 撤销选择
			temp.remove(temp.size() - 1);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3};
//		int[] nums = {};
		SubsetsInt si = new SubsetsInt();
		List<List<Integer>> lli = si.subsets_1(nums);
		System.out.println(lli);
		
	}

}
