package noClassification;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an array of integers, 
//return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution, 
//and you may not use the same element twice.
//
//Example:
//
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].

public class twoSum {
	public boolean isContain(int[] nums, int temp, int used_index){
        boolean is_contain = false;
        for(int one_num = 0;one_num < nums.length; one_num++){
            if(nums[one_num] == temp && one_num != used_index){
                is_contain = true;
                break;
            }
        }
        return is_contain;
    }

    public int findIndex(int[] nums, int temp, int used_index){
        int index = 0;
        for(int one_num = 0;one_num < nums.length; one_num++){
            if(nums[one_num] == temp && one_num != used_index){
                index = one_num;
                break;  
            }
        }
        return index;
    }

    public int[] findTwoIndex(int[] nums, int target) {
        int[] two_index = new int[2];
        int[] sort_nums = nums.clone();
        // 首先对数组进行排序
        Arrays.sort(sort_nums);
        System.out.println(Arrays.toString(nums));
        for(int one_num = nums.length - 1; one_num >= 0; one_num--){
            if(target >= 0 && sort_nums[one_num] <= target){
                int temp = target - sort_nums[one_num];
                // 查看数组中是否有剩余的数字
                if(isContain(sort_nums,temp,one_num)){
                    two_index[0] = findIndex(nums, sort_nums[one_num],-1);
                    two_index[1] = findIndex(nums, temp, two_index[0]);
                    break;
                }
            }else if(target < 0 && sort_nums[one_num] >= target) {
            	int temp = target - sort_nums[one_num];
                // 查看数组中是否有剩余的数字
                if(isContain(sort_nums,temp,one_num)){
                    two_index[0] = findIndex(nums, sort_nums[one_num],-1);
                    two_index[1] = findIndex(nums, temp, two_index[0]);
                    break;
                }	
            }
        }
        if((0 == two_index[0]) && (two_index[0] == two_index[1])){
            System.out.println("数组中没有此元素");
        }else{
            System.out.println("数组中有此元素，位置分别是：" + two_index[0] + ',' + two_index[1]);
        }

        return two_index;        
    }
    
    // 别人的改进,使用hashmap
    public int[] findTwoIndexPerfect(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); 
        
        for (int i = 0; i < nums.length; ++i) {
            if (seen.containsKey(target - nums[i])) 
                return new int[] {seen.get(target - nums[i]), i}; 
            seen.put(nums[i], i); 
        }
        return new int[] {}; // to compile 
    }

    public static void main(String[] args) {
        int target = -8;
        int[] all_nums = {-1, -2, -3, -4, -5};
        int[] results = new int[2];
        twoSum one_solu = new twoSum();
//        results = one_solu.findTwoIndex(all_nums,target);
        results = one_solu.findTwoIndexPerfect(all_nums,target);
        if(results[0] != 0 && results[1] != 0) {
        	System.out.println(results[0] + "," + results[1]);        	
        }

    }

}
