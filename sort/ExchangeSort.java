package sort;

import java.util.Arrays;

public class ExchangeSort {

	// ð������
	// �Ƚ�����Ԫ�أ���Ϊ������������
	public void BubbleSort(int[] nums) {
		for(int index = nums.length - 1; index > 0; index--) {
			// �鿴�Ƿ����˽���
			boolean flag = false;
			for(int com_index = 0; com_index < index; com_index++) {
				if(nums[com_index] > nums[com_index + 1]) {
					int temp = nums[com_index];
					nums[com_index] = nums[com_index + 1];
					nums[com_index + 1] = temp;
					flag = true;
				}
			}
			if(!flag) return;
		}
	}
	
	
	// ��������
	// ��Ҫ���ǵģ���һ���ǻ�׼���ѡ�񣬵ڶ����ǻ�׼ֵ����ų�����
	public void QuickSort(int[] nums) {
		int low = 0;
		int high = nums.length - 1;
		QuickRes(nums, low, high);
//		QuickResComm(nums, low, high);
	}
	
	// һ��汾�Ŀ�������
	public void QuickResComm(int[] nums, int low, int high) {
		// ��׼��������ߣ���ô�����ʹ��ұ߿�ʼ
		int left = low, right = high, pri = nums[left];
		while(left < right) {
			while(left < right && nums[right] > pri) right--;
			nums[left] = nums[right];
			while(left < right && nums[left] < pri) left++;
			nums[right] = nums[left];
		}
		nums[left] = pri;
		QuickRes(nums, low, left - 1);
		QuickRes(nums, left + 1, high);
	}
	
	// ��ֵѡ��Ŀ�������
	public void QuickRes(int[] nums, int low, int high) {
		if(low < high) {
			// ��׼����ֵѡ���м��
			int middle = (low + high) / 2;
			if(nums[low] > nums[middle]) swap(nums, low, middle);
			if(nums[low] > nums[high]) swap(nums, low, high);
			if(nums[middle] > nums[high]) swap(nums, middle, high);
			
			int partion = nums[middle];
			// ��׼�������ұߣ���ô������ʱ�򣬾���Ҫ�ȴ���߿�ʼ��
			swap(nums, middle, high - 1);
			int left = low, right = high - 1;
			while(left < right) {
				while(nums[++left] < partion) ;
				while(nums[--right] > partion) ;
				if(left < right) {
					swap(nums,left,right);
				}else {
					break;
				}
			}
			swap(nums, left, high - 1);
			QuickRes(nums, low, left - 1);
			QuickRes(nums, left + 1, high);
		}
	}

    // ����λ��
	private void swap(int[] nums, int first, int second) {
		int temp = nums[first];
		nums[first] = nums[second];
		nums[second] = temp;		
	}


	// ��������
	public static void main(String[] args) {
		int[] nums = {5,3,1,2,2,3,10};
		ExchangeSort is = new ExchangeSort();
//        is.BubbleSort(nums);
        is.QuickSort(nums);
        for(int num:nums) {
        	System.out.print(num + ",");
        }
	}
}
