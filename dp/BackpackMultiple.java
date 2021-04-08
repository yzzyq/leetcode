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

	// ����һ�ֱ����Ķ�̬�滮�����������оٳ�ȫ���ĸ���
	// ��������ڸ����ͱ������������� <100���ٴ�ͻᳬ������
	private int backMulti(int m, int[] v, int[] a, int[] s) {
		int[] value = new int[m+1];
		
		for(int index = 0; index < v.length; ++index) {
			for(int m_index = m; m_index > 0; --m_index) {
				// �о�ȫ���ĸ���
				for(int s_index = 1; s_index <= s[index] && a[index]*s_index <= m_index; ++s_index) {
					value[m_index] = Math.max(value[m_index], 
							value[m_index - a[index]*s_index ] + v[index]*s_index);
				}
			}
		}
		
		return value[m];
	}
	
	// ��������1000����������2000����ô����ķ������޷������
	// ʹ�ö������Ż�����
	private int backMultiII(int m, int[] v, int[] a, int[] s) {
		int[] value = new int[m+1];
		
		for(int index = 0; index < v.length; ++index) {
			for(int m_index = m; m_index > 0; --m_index) {
				// �о�ȫ���ĸ���
				for(int s_index = 1; s_index <= s[index] && a[index]*s_index <= m_index; ++s_index) {
					value[m_index] = Math.max(value[m_index], 
							value[m_index - a[index]*s_index ] + v[index]*s_index);
				}
			}
		}
		
		return value[m];
	}
	

}
