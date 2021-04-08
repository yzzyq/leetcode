package sort;

public class SelectSort {
	
	// ��ѡ������
	// ������������������ǣ����������������ǰ�������ѡ�������ǲ����ĺ������
	public void directSelect(int[] nums) {
		for(int index = 0; index < nums.length; index++) {
			// ÿһ�ֶ�ѡ�����ֵԪ��
			int min_index = index;
			for(int com_index = index + 1; com_index < nums.length; com_index++) {
				if(nums[min_index] > nums[com_index]) {
					min_index = com_index;
				}
			}
			if(min_index != index) {
				int temp = nums[min_index];
				nums[min_index] = nums[index];
				nums[index] = temp;
			}
		}
	}
	
	// ������
	public void heapSort(int[] nums) {
		int len = nums.length;
		// ������ʼ��
		for(int index = len/2 - 1; index >=0; index--) {
			adjustMaxHeap(nums, index, len);
		}
		
		// ���˳�ʼ��֮��ÿ��ֻҪ����ͷ��Ԫ�ص��������֮���ڵ�����ͷ���У�����������ѭ������
		for(int index = len - 1; index > 0; index--) {
			nums[0] = nums[0] + nums[index];
			nums[index] = nums[0] - nums[index];
			nums[0] = nums[0] - nums[index];
			adjustMaxHeap(nums, 0, index);
		}
		
	}
	
	public void adjustMaxHeap(int[] nums, int start, int len) {
		int temp = nums[start];
		for(int index = start*2 + 1; index < len; index = index*2 + 1) {
			if(index + 1 < len && nums[index] < nums[index + 1]) index++;
			
			if(nums[start] >= nums[index]) {
				break;
			}else {
				// ����е�����Ϊ��ֱ�Ӹ�ֵ��Ȼ����for�������ٸ�ֵ��ʼ��ֵ
				// �Ҿ����������ԣ��㲻�����Ļ���ֵ��û�б仯
				// û�б仯������Ҳ�������
				nums[start] = nums[index];
				nums[index] = temp;
				start = index;
			}
			
		}
		
	}

	public static void main(String[] args) {
//		int[] nums = {5,3,1,2,2,3,10};
		int[] nums = {4,6,8,5,9};
		SelectSort cs = new SelectSort();
//        cs.directSelect(nums);
		cs.heapSort(nums);
        for(int num:nums) {
        	System.out.print(num + ",");
        }
	}

}
