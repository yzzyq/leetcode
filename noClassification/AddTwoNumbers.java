package noClassification;

//You are given two non-empty linked lists representing two non-negative integers. 
//The digits are stored in reverse order and each of their nodes contain a single digit. 
//Add the two numbers and return it as a linked list.
//
//You may assume the two numbers do not contain any leading zero, 
//except the number 0 itself.
//
//Example:
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.


public class AddTwoNumbers {
	
	// 复习
	public ListNode addTwoNumbers_main_two(ListNode l1_first, ListNode l2_first) {
		ListNode sum_list = new ListNode(0);
        int temp = 0;
		ListNode temp_sum = sum_list;
		while(l1_first != null || l2_first != null) {
			int sum_temp = temp;
			if(l1_first != null) sum_temp += l1_first.val;
			if(l2_first != null) sum_temp += l2_first.val;
			temp_sum.next = new ListNode(sum_temp % 10);
			temp_sum = temp_sum.next;
			temp = sum_temp / 10;
			l1_first = l1_first.next;
			l2_first = l2_first.next;
		}
		if(temp > 0) temp_sum.next = new ListNode(temp);
		return sum_list.next;
	}
	
	public int max_add_numbers(ListNode l1, ListNode before_node, int carry_value) {
		while(l1 != null) {
			int add_sum = l1.val + carry_value;
			carry_value = 0;
			if(add_sum >= 10) {
				carry_value = 1;
				add_sum %= 10;
			}
			ListNode current_node = new ListNode(add_sum);
			before_node.next = current_node;
			before_node = current_node;
			l1 = l1.next;
		}
		if(carry_value > 0) {
			ListNode current_node = new ListNode(carry_value);
			before_node.next = current_node;
			carry_value = 0;
		}
        return carry_value;
	}
	
	// 不应该外加一个函数，并且整个函数得内部写的很复杂，可以写简单
	public ListNode addTwoNumbers_main(ListNode l1, ListNode l2) {
		ListNode result_first = null;
		ListNode before_node = new ListNode();
		int carry_value = 0;
		while(l1 != null && l2 != null) {
			int add_sum = l1.val + l2.val + carry_value;
			carry_value = 0;
			if(add_sum >= 10) {
				carry_value = 1;
				add_sum %= 10;
			}
			if(result_first == null) {
				result_first = new ListNode();
				result_first.val = add_sum;
				before_node = result_first;
			}else {
				ListNode current_node = new ListNode(add_sum);
				before_node.next = current_node;
				before_node = current_node;
			}
			l1 = l1.next;
			l2 = l2.next;
		}
		if(l1 != null) {
			carry_value = max_add_numbers(l1, before_node, carry_value);
		}
		if(l2 != null) {
			carry_value = max_add_numbers(l2, before_node, carry_value);
		}
		if(carry_value > 0) {
			ListNode current_node = new ListNode(carry_value);
			before_node.next = current_node;
			carry_value = 0;
		}
		return result_first;
		
	}
	
	// 将之上面得改善，简洁有效
	public ListNode addTwoNumbers_main_perfect(ListNode l1, ListNode l2) {
		ListNode dummey = new ListNode(0);
		int carry = 0;
		ListNode curr = dummey;
		// 只要使用或者，然后俩者分开相加即可，不必像上面一样繁琐
		while(l1 != null || l2 != null) {
			int sum = carry;
			if(l1 != null) sum += l1.val;
			if(l2 != null) sum += l2.val;
			curr.next = new ListNode(sum % 10);
			carry = sum / 10;
			curr = curr.next;
			if(l1 != null) l1 = l1.next; 
			if(l2 != null) l2 = l2.next;
		}
		if(carry > 0) curr.next = new ListNode(carry); 
		return dummey;
	}
	
	
	
	public static void main(String[] args) {
		ListNode l1_third = new ListNode(6);
		ListNode l1_second = new ListNode(4, l1_third);
		ListNode l1_first = new ListNode(6, l1_second);
		
		ListNode l2_third = new ListNode(7);
		ListNode l2_second = new ListNode(6, l2_third);
		ListNode l2_first = new ListNode(5, l2_second);
		
//		ListNode l1_second = new ListNode(9);
//		ListNode l1_first = new ListNode(9, l1_second);
//		
//		ListNode l2_first = new ListNode(1);
//		ListNode l2_first = new ListNode(5, l2_second);
		
//		ListNode l1_first = new ListNode(5);
//		
//		ListNode l2_first = new ListNode(5);
		
		AddTwoNumbers atn = new AddTwoNumbers();
		ListNode result_first = atn.addTwoNumbers_main_two(l1_first,l2_first);
		while(result_first != null) {
			System.out.print(result_first.val + " ");
			result_first = result_first.next;
			if (result_first != null) {
				System.out.print("->" + " ");
			}
		}
           
	}

}
