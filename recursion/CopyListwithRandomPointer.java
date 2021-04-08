package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
 
Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.
 

Constraints:

-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
The number of nodes will not exceed 1000.
 * 
 * 
 * 
 * @author yzzyq
 *
 */

// 对链表进行深拷贝

public class CopyListwithRandomPointer {
	
	Map<Node, Node> node_map = new HashMap<Node, Node>();
	
	// 递归
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        
        if(node_map.containsKey(head)) {
        	return node_map.get(head);
        }
        
        Node new_node = new Node(head.val);
        
        node_map.put(head, new_node);
        
        new_node.next = copyRandomList(head.next);
        new_node.random = copyRandomList(head.random);
        return new_node;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        
        first.next = second;
        second.next = third;
        third.next = fourth;
        
        second.random = first;
        fourth.random = third;
        
        var crwrp = new CopyListwithRandomPointer();
        Node copy_node = crwrp.copyRandomList(first);
        while(copy_node != null) {
        	System.out.print(copy_node.val);
        	System.out.print(" , ");
        	System.out.print(copy_node.next.val);
        	System.out.print(" , ");
        	System.out.print(copy_node.random.val);
        }
        
	}

}
