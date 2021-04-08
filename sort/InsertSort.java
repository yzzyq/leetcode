package sort;

public class InsertSort {
	// ���嶼�Ǵ�С��������
	
	// ֱ�Ӳ�������
	// 1. �ҳ�Ҫ�����λ��
	// 2. ֮�����е�Ԫ�ض�Ҫ����
	// 3. ��ֵ
	public void DirectInsertSort(int[] nums) {
		for(int index = 1; index < nums.length; index++) {
			if(nums[index] < nums[index - 1]) {
				int temp = nums[index];
				int pos_index = index - 1;
				while(pos_index >= 0 && temp < nums[pos_index]) {
					nums[pos_index + 1] = nums[pos_index];
					pos_index--;
				}
				nums[pos_index + 1] = temp;
			}
		}
	}
	
	
	
	// �۰����������Ҫ������ǲ��Ҳ���λ��ʹ�õ��Ƕ��ֲ���
	public void SplitInsertSort(int[] nums) {
		for(int index = 1; index < nums.length; index++) {
			if(nums[index] < nums[index - 1]) {
				// ʹ�ö��ַ����Ҳ���λ��
				int temp = nums[index];
				int left = 0;
				int right = index - 1;
				// �ҳ�Ҫ�����λ��
				while(left <= right) {
					int middle = (left + right) / 2;
					if(temp > nums[middle]) {
						left = middle + 1;
					}else {
						right = middle - 1;
					}
				}
				// ���ݽ��к���
				for(int temp_index  = index - 1; temp_index >= right + 1; temp_index--) {
					nums[temp_index + 1] = nums[temp_index];
				}
				nums[right + 1] = temp;
			}
			
		}
	}
	
	// ϣ��������Ҫ���������С�������ʺϱȽϴ�����ݼ�
	// ���ڴ�����ݼ������ȷֳ�С�ģ��Ƚ���ЩС�ģ�֮����кϲ��ɴ�ġ�
	// ���ڷֳ�С�ģ����ǲ�������Ŀ����ڴ������������ʵ���Ǹ��նԱȼ���,�������ܼ��ٿռ临�Ӷ�
	public void ShellSort(int[] nums) {
		int len = nums.length;
		for(int step = len / 2; step > 0; step /= 2) {
			for(int index = step; index < len; index++) {
				if(nums[index] < nums[index - step]) {
					// ÿ�����鶼ʹ��ֱ�Ӳ�������
					int temp = nums[index];
					int pos_index = index - step;
					while(pos_index >= 0 && temp < nums[pos_index]) {
						nums[pos_index + step] = nums[pos_index];
						pos_index -= step;
					}
					nums[pos_index + step]= temp;
				}
			}
			
		}
	}
	

	public static void main(String[] args) {
		int[] nums = {5,3,1,2,2,100,5};
		InsertSort is = new InsertSort();
//        is.DirectInsertSort(nums);
        is.SplitInsertSort(nums);
        for(int num:nums) {
        	System.out.print(num + ",");
        }
	}

}
