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

// 用最少得硬币来组合
// 这就是动态规划中得最值问题

public class CoinChange {
	
	// 动态规划
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
    
    //递归加上记忆技术
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
	
	// 贪心+剪枝
	// 贪心，就是尽量使用大硬币
	// 如果直接使用贪心，会出现一些奇葩得情况，所以需要遍历全部情况
	// 但是如果遍历全部情况，就会变成暴力破解，因此我们需要贪心来剪枝，减少情况
	
	// 这是最优解法，比上面DP什么得都快很多很多
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
			
			// 如果成功了，那么直接换成现在，然后继续遍历
			// 但是当前得这个可以剪枝，因为下面的都是用小硬币换大的，肯定会变多
			if(rest == 0) {
				ans = Math.min(ans, new_coin);
				break;
			}
			
			// 如果当前硬币数比之前的大，那么就不需要比较了，直接剪枝
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
