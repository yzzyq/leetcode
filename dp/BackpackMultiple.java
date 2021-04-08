package dp;

public class BackpackMultiple {

	public static void main(String[] args) {
		int[] A = {1,2,3,4};
		int[] V = {2,4,4,5};
		int[] S = {3,1,3,2};
		int m = 5;
		
		BackpackMultiple bm = new BackpackMultiple();
		
		int max_value = bm.backMulti(m,V,A,S);
		System.out.println(max_value);

	}

	// 这是一种暴力的动态规划方法，就是列举出全部的个数
	// 这个适用于个数和背包容量都是在 <100，再大就会超出限制
	private int backMulti(int m, int[] v, int[] a, int[] s) {
		int[] value = new int[m+1];
		
		for(int index = 0; index < v.length; ++index) {
			for(int m_index = m; m_index > 0; --m_index) {
				// 列举全部的个数
				for(int s_index = 1; s_index <= s[index] && a[index]*s_index <= m_index; ++s_index) {
					value[m_index] = Math.max(value[m_index], 
							value[m_index - a[index]*s_index ] + v[index]*s_index);
				}
			}
		}
		
		return value[m];
	}
	
	// 个数扩大到1000，容量扩大到2000，那么上面的方法就无法完成了
	// 使用二进制优化方法
	private int backMultiII(int m, int[] v, int[] a, int[] s) {
		int[] value = new int[m+1];
		
		for(int index = 0; index < v.length; ++index) {
			for(int m_index = m; m_index > 0; --m_index) {
				// 列举全部的个数
				for(int s_index = 1; s_index <= s[index] && a[index]*s_index <= m_index; ++s_index) {
					value[m_index] = Math.max(value[m_index], 
							value[m_index - a[index]*s_index ] + v[index]*s_index);
				}
			}
		}
		
		return value[m];
	}
	

}
