package slidingWindow;

//Given n non-negative integers a1, a2, ..., an , where each represents a point at 
//coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) 
//and (i, 0). Find two lines, which together with x-axis forms a container, 
//such that the container contains the most water.
//
//Note: You may not slant the container and n is at least 2.
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49


// ������һ��װˮ���⣬���������С�˻�Ϊ���

public class ContainerWithWter {
	
	// ����˫ָ�뷨����ϰ
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
	
	
	// ��򵥵ı����ƽ�ģ�ʱ��ռ�ܲ�
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
    
    // ˫ָ�뷨��˼����ǣ��޷Ǿ��ǿ�ȴ���߿�ȴ󣬴����߿�ʼ�㣬���߿϶�������
    // ���������ܴ󣬾������߶�С��
	// ���á���û�кܴ���������ٷ�֮��ʮ��û��
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
