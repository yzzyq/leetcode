package binary;

/**
 * 137. Single Number II
 * 
 * Given a non-empty array of integers, every element appears three times except for one, 
 * which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without 
using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 * @author yzzyq
 *
 */

// ����Ԫ�س��������Σ��ҳ�����һ�ε�

public class SingleNumberII {
	// ˼·�����ǣ�����һ��32λ��������֮Ҳ��ɶ���������
	// ��������ӣ����ն�֮����3ȡ�࣬����0�ģ����ǳ��ֵ�1
    public int singleNumber(int[] nums) {
        int res = 0;
        
        for(int index = 0; index < 32; index++) {
        	int sum = 0;
        	for(int num:nums) {
        		sum += (num >> index) & 1;
        	}
        	res |= (sum%3) << index;
        	
        }
        
        return res;
    }
    
    // ������һ��˼·���������������ֱ𿴵�һλ���ڶ�λ�͵���λ
    public int singleNumber_1(int[] nums) {
        int first = 0, second = 0, third = 0;
        
        for(int num:nums) {
        	// �ȸ��µڶ�����
        	second |= first&num;
        	first ^= num;
        	
        	third = first&second;
        	// ֮�������һ�����͵ڶ�����
        	first &= ~third;
        	second &= ~third;
        }
        
        return first;
    }
    
    // ����һ��˼·��������뷨��������뷨һ���������Ǳ������λ����
    public int singleNumber_2(int[] nums) {
        int first = 0, second = 0;
        
        for(int num:nums) {
        	// ����д���ǳ����
        	// �����˼���ǵ�һλ�����ͬ�ˣ���ô���ڶ�λ�Ƿ���ڣ����ھͱ��0
        	// �ر�Ҫ���~ȡ��λ���������˼����secondΪ0��ʱ��
        	// first ^num������ʲô������ȫ��ͨ��
        	// ��second��first ^num��ͬ�ˣ���ô����ȫ������ͨ����
        	first = ~second & (first ^num);
        	second = ~first &(second ^num);
        }
        
        return first;
    }

	public static void main(String[] args) {
		int[] nums = {0,1,0,1,0,1,99};
		var snii = new SingleNumberII();
		int num = snii.singleNumber_2(nums);
		System.out.println(num);
		int three = 1;
		System.out.println(1 & -1);
		System.out.println(1&~1);
	}

}
