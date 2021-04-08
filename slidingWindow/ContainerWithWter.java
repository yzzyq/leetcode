package slidingWindow;

//Given n non-negative integers a1, a2, ..., an , where each represents a point at 
//coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) 
//and (i, 0). Find two lines, which together with x-axis forms a container, 
//such that the container contains the most water.
//
//Note: You may not slant the container and n is at least 2.
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49


// 类似于一种装水问题，横纵坐标大小乘积为最大

public class ContainerWithWter {
	
	// 采用双指针法，复习
	public int maxArea_two(int[] container_water) {
		int start = 0;
		int end = container_water.length - 1;
		
		int max_area = 0;
		while(start < end) {
			int temp = Math.min(container_water[start], container_water[end])*(end - start);
			max_area = Math.max(max_area, temp);
			if(container_water[start] < container_water[end]) {
				start++;
			}else {
				end--;
			}
		}
		
		return max_area;
	}
	
	
	// 最简单的暴力破解的，时间空间很差
//    public int maxArea(int[] height) {
//    	if(height.length < 2) return 0;
//    	int height_len = height.length;
//    	int maxA = 0;
//    	for(int one_index = 0; one_index < height_len - 1; one_index++) {
//    		for(int two_index = one_index + 1; two_index < height_len; two_index++) {
//    			int temp = Math.min(height[one_index], height[two_index])*(two_index - one_index);
//    			maxA = Math.max(temp, maxA);
//    		}
//    	}
//    	
//        return maxA;
//    }
    
    // 双指针法，思想就是，无非就是宽度大或者宽度大，从俩边开始搞，俩边肯定宽度最大
    // 后来后续能大，就抛弃高度小的
	// 还好。并没有很大的提升，百分之五十都没过
    public int maxArea(int[] height) {
    	if(height.length < 2) return 0;
    	int maxA = 0;
    	int start_pointer = 0;
    	int end_pointer = height.length - 1;
    	while(start_pointer < end_pointer) {
    		int temp = Math.min(height[start_pointer], height[end_pointer])*(end_pointer - start_pointer);
    		maxA = Math.max(temp, maxA);
    		temp = height[start_pointer] > height[end_pointer]? end_pointer--:start_pointer++;
    	}
    	return maxA;
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] container_water = {1,8,6,2,5,4,8,3,7};
		ContainerWithWter cw = new ContainerWithWter();
		int maxA = cw.maxArea(container_water);
		System.out.println(maxA);
	}

}
