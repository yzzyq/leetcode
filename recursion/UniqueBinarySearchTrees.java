import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {
	
	
	// 动态规划
    public int numTrees(int n) {
    	int[] dp = new int[n+1];
    	
    	dp[0] = 1;
    	dp[1] = 1;
    	
    	for(int index = 2; index <= n; ++index) {
    		for(int num = 0; num < index; ++num) {
    			dp[index] += dp[index - num - 1]*dp[num];
    		}
    	}
        
    	return dp[n];
    }
    
    // 递归
    public int numTrees_1(int n) {
    	if(n == 1 || n == 0) {
    		return 1;
    	}
    	
    	int num = 0;
    	for(int index = 0; index < n; ++index) {
    		num += numTrees_1(index)*numTrees_1(n-index-1);
    	}
    	return num;
    }
    
    // 记忆性递归
    
    Map<Integer,Integer> num_map = new HashMap<>();
    
    public int numTrees_2(int n) {
    	if(n == 1 || n == 0) {
    		return 1;
    	}
    	
    	if(num_map.containsKey(n)) {
    		return num_map.get(n);
    	}
    	
    	int num = 0;
    	for(int index = 0; index < n; ++index) {
    		num += numTrees_2(index)*numTrees_2(n-index-1);
    	}
    	num_map.put(n, num);
    	return num;
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
