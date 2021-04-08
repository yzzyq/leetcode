package dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 322. Coin Change
 * 
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that 
 * amount. If that amount of money cannot be made up by any combination of the coins, 
 * return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
Example 4:

Input: coins = [1], amount = 1
Output: 1
Example 5:

Input: coins = [1], amount = 2
Output: 2
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 231 - 1
 * 
 * 
 * @author yzzyq
 *
 */

// �����ٵ�Ӳ�������
// ����Ƕ�̬�滮�е���ֵ����

public class CoinChange {
	
	// ��̬�滮
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int[] amount_coin = new int[amount + 1];
        amount_coin[0] = 0;
        
        for(int index = 1; index < amount + 1; index++) {
        	amount_coin[index] = Integer.MAX_VALUE;
        	for(int coin:coins) {
        		if(coin <= index && amount_coin[index-coin] != -1) {
        			
        			amount_coin[index] = Math.min(amount_coin[index-coin]+1, 
        					amount_coin[index]);
        		}
        	}
        	if(amount_coin[index] == Integer.MAX_VALUE) amount_coin[index] = -1;
        }
        
        return amount_coin[amount];
    }
    
    //�ݹ���ϼ��似��
    public int coinChange_1(int[] coins, int amount) {
    	HashMap<Integer, Integer> coin_map = new HashMap<Integer, Integer>();
    	coin_map.put(0, 0);
    	return change(coin_map,coins,amount);
    }

	public int change(HashMap<Integer, Integer> coin_map, int[] coins, int amount) {
		if(amount < 0) return -1;
		
		if(coin_map.containsKey(amount)) {
			return coin_map.get(amount);
		}
		
		int temp = Integer.MAX_VALUE;
		for(int coin:coins) {
			int result = change(coin_map, coins, amount - coin);
			if(result != -1) temp = Math.min(result+1, temp);
		}
		
		if(temp == Integer.MAX_VALUE) {
			coin_map.put(amount, -1);
		}else {
			coin_map.put(amount, temp);
		}
		
		return coin_map.get(amount);
	}
	
	// ̰��+��֦
	// ̰�ģ����Ǿ���ʹ�ô�Ӳ��
	// ���ֱ��ʹ��̰�ģ������һЩ����������������Ҫ����ȫ�����
	// �����������ȫ��������ͻ��ɱ����ƽ⣬���������Ҫ̰������֦���������
	
	// �������Žⷨ��������DPʲô�ö���ܶ�ܶ�
	int ans = Integer.MAX_VALUE;
	public int coinChange_2(int[] coins, int amount) {
		Arrays.sort(coins);
		getMinNum(coins,coins.length -1, amount, 0);
		return ans == Integer.MAX_VALUE?-1:ans;
	}

	
	public void getMinNum(int[] coins, int maxNum, int amount, int coin_num) {
		if(maxNum < 0) return;
		
		for(int index = amount/coins[maxNum]; index >= 0; index--) {
			int rest = amount - index*coins[maxNum];
			int new_coin = coin_num + index;
			
			// ����ɹ��ˣ���ôֱ�ӻ������ڣ�Ȼ���������
			// ���ǵ�ǰ��������Լ�֦����Ϊ����Ķ�����СӲ�һ���ģ��϶�����
			if(rest == 0) {
				ans = Math.min(ans, new_coin);
				break;
			}
			
			// �����ǰӲ������֮ǰ�Ĵ���ô�Ͳ���Ҫ�Ƚ��ˣ�ֱ�Ӽ�֦
			if(new_coin + 1 >= ans) {
				break;
			}
			getMinNum(coins, maxNum - 1, rest, new_coin);
		}
	}

	public static void main(String[] args) {
		int[] coins = {2};
		int amount = 3;
		CoinChange cc = new CoinChange();
        int min_coin_num = cc.coinChange_2(coins, amount);
        System.out.println(min_coin_num);
	}

}
