package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * 721. Accounts Merge
 * 
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element 
 * accounts[i][0] is a name, and the rest of the elements are emails representing emails of the 
 * account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if 
there is some email that is common to both accounts. Note that even if two accounts have the same 
name, they may belong to different people as people could have the same name. A person can have any 
number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each 
account is the name, and the rest of the elements are emails in sorted order. The accounts 
themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], 
["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", 
"johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other 
accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], 
['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
 * 
 * 
 * 
 * @author yzzyq
 *
 */

// 账号是同一个人得话，名称相同，并且具有相同得邮箱

public class AccountsMerge {
	
	// 并查集
	Map<String, String> parent = new HashMap<String, String>();
	
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
    	List<List<String>> result = new ArrayList<List<String>>();
    	Map<String, String> owner = new HashMap<String, String>();
    	HashMap<String, List<String>> m = new HashMap<String, List<String>>();
    	
    	// 首先对并查集进行初始化
    	for(List<String> account : accounts) {
    		for(int index = 1; index < account.size(); ++index) {
    			parent.put(account.get(index), account.get(index));
    			owner.put(account.get(index), account.get(0)); 
    		}
    	}
    	
    	// 对之进行查找合并，其实就是将所有的串起来
    	for(List<String> account : accounts) {
    		String one_parent = getParent(account.get(1));
    		for(int index = 2; index < account.size(); ++index) {
    			parent.put(getParent(account.get(index)), one_parent);
    		}
    	}
    	
    	// 找出所有父类的串
    	for(List<String> account : accounts) {
    		for(int index = 1; index < account.size(); ++index) {
    			List<String> one = m.getOrDefault(getParent(account.get(index)), new ArrayList<String>());
    			if(!one.contains(account.get(index))){
    				one.add(account.get(index));
    			}
    			m.put(getParent(account.get(index)), one);
    		}
    	}
    	
    	// 将之合并
    	for(String key:m.keySet()) {
    		String one_owner = owner.get(key);
    		List<String> one = m.get(key);
    		Collections.sort(one);
    		one.add(0, one_owner);
    		result.add(one);
    	}  	
    	return result;
    }
    
    // 找出父类
    public String getParent(String s) {
    	if(!parent.get(s).equals(s)) {
    		parent.put(s, getParent(parent.get(s)));
    	}
    	
    	return parent.get(s);
    }
    
    
    
    
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> accounts = new ArrayList<List<String>>();

//		List<String> one_account = new ArrayList<String>();
//		one_account.add("John");
//		one_account.add("johnsmith@mail.com");
//		one_account.add("john00@mail.com");
//		accounts.add(one_account);
//		
//		List<String> two_account = new ArrayList<String>();
//		two_account.add("John");
//		two_account.add("johnnybravo@mail.com");
//		accounts.add(two_account);
//		
//		List<String> three_account = new ArrayList<String>();
//		three_account.add("John");
//		three_account.add("johnsmith@mail.com");
//		three_account.add("john_newyork@mail.com");
//		accounts.add(three_account);
//		
//		List<String> four_account = new ArrayList<String>();
//		four_account.add("Mary");
//		four_account.add("mary@mail.com");
//		accounts.add(four_account);
		
		
		List<String> one_account = new ArrayList<String>();
		one_account.add("John");
		one_account.add("john0@mail.com");
		one_account.add("john4@mail.com");
		one_account.add("john3@mail.com");
		accounts.add(one_account);
		
		List<String> two_account = new ArrayList<String>();
		two_account.add("John");
		two_account.add("john5@mail.com");
		two_account.add("john5@mail.com");
		two_account.add("john0@mail.com");
		accounts.add(two_account);
		
		AccountsMerge am = new AccountsMerge();
		List<List<String>> result = am.accountsMerge(accounts);
		for(int index = 0; index < result.size(); ++index) {
			List<String> one = result.get(index);
			
			for(int two_index = 0; two_index < one.size(); ++two_index) {
				System.out.print(one.get(two_index) + ",");
			}
			
			System.out.println();
		}
		
	}

}
