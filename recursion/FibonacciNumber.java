package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 509. Fibonacci Number
 * 
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the 
 * Fibonacci sequence, such that each number is the sum of the two preceding ones, 
 * starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).

Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

Note:

0 �� N �� 30.
 * 
 * @author yzzyq
 *
 */

// 쳲��������е����
// �ٷ����������ֽⷨ��ֱ�Ӱ�������

public class FibonacciNumber {
	
	// �ݹ飬Ч������
	public int fib(int N) {
		if(N == 1 || N == 0) {
			return N;
		}
        
		return fib(N - 1) + fib(N - 2);
		
    }
	
	// �Ľ��ݹ飬�ݹ��Ч��������Ϊ���ڴ������ظ��Լ���,��ô�ҾͿ��Լ�¼�����������ֵ
	// �ٶ�˲��������
	public int fib_2(int N) {
		if(N <= 1) {
			return N;
		}
        
		Map<Integer, Integer> mom = new HashMap<Integer, Integer>();
		return fibMomerize(N,mom);
    }
	
	
	private int fibMomerize(int n, Map<Integer, Integer> mom) {
		if(mom.containsKey(n)) {
			return mom.get(n);
		}
		
		if(n <= 1) {
			return n;
		}
		
		int sum = fibMomerize(n - 1, mom) + fibMomerize(n - 2, mom);
		mom.put(n, sum);
		return sum;
	}

	// ��̬�滮,�ٶȺܿ죬Ӣ��leetcode������100%
	public int fib_1(int N) {
		if(N == 1 || N == 0) {
			return N;
		}
		
		int[] nums = new int[N + 1];
		nums[0] = 0;
		nums[1] = 1; 
		
		
		for(int index = 2; index <= N; index++) {
			nums[index] = nums[index - 1] + nums[index - 2];
		}
		
		return nums[N];
    }

	public static void main(String[] args) {
		int index = 4;
		var fn = new FibonacciNumber();
		int sum = fn.fib(index);
        System.out.println(sum);
	}

}
