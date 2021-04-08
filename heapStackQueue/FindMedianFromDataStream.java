package heapStackQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 295. Find Median from Data Stream
 * Median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * 
 * 
 * 
 * @author yzzyq
 *
 */

// 找数据流中的中位数

public class FindMedianFromDataStream {
	//---------------------这个方法消耗的时间和空间都很大，7%和5%------------------
//	public List<Integer> data_list; 
//	public int list_size;
//	
//	/** initialize your data structure here. */
//    public FindMedianFromDataStream() {
//    	data_list = new ArrayList<Integer>();
//    	list_size = 0;
//    }
//    
//    public void addNum(int num) {
//    	int pos = 0;
//    	if(data_list.size() != 0) {
//    		while(pos < data_list.size() && num > data_list.get(pos)) {
//    			pos++;
//    		}
//    	}
//        data_list.add(pos, num);
//        
//        list_size++;
//    }
//    
//    public double findMedian() {
//        if(list_size % 2 == 0) {
//        	return (data_list.get(list_size/2) + data_list.get(list_size/2 - 1))/2.0;
//        }else {
//        	return data_list.get((list_size - 1)/2);
//        }
//    }
    
    
    // 使用双堆，既然要找中位数，那么将整个数据分成俩部分按照最大堆和最小堆中，
	// 直接取出最大堆中的元素和最小堆的元素即可
	PriorityQueue<Integer> max_heap;
	PriorityQueue<Integer> min_heap;
	int count;
	
    public FindMedianFromDataStream() {
    	// 默认的话，就是最小堆
    	min_heap = new PriorityQueue<Integer>();
    	// 设置最大堆
        max_heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        	public int compare(Integer o1, Integer o2) {
        		return o2 - o1;
        	};
		});
        count = 0;
    }
    
    // 难点
    public void addNum(int num) {
    	++count;
    	// 要让值在最大堆和最小堆中都过一遍
    	
    	// 先进入最大堆,因为最大堆是保存较小数据的部分
    	max_heap.offer(num);
    	// 之后将最大堆的最大值放入到最小堆中，来保持有序
    	min_heap.offer(max_heap.poll());
    	// 因为奇数个数的话，中位数从最大堆中取，那么最大堆要保持比最小堆个数多一个
    	if((count & 1) != 0) {
    		max_heap.offer(min_heap.poll());
    	}

    }
    
    public double findMedian() {
    	if((count & 1) != 0) {
    		return max_heap.peek();
    	}else {
    		return (max_heap.peek() + min_heap.peek()) / 2.0;
    	}

    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var obj = new FindMedianFromDataStream();
		obj.addNum(1);
		obj.addNum(2);
		double param_1 = obj.findMedian();
		System.out.println(param_1);
        obj.addNum(3);
        double param_2 = obj.findMedian();
        System.out.println(param_2);
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
