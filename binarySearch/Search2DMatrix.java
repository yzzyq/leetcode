package binarySearch;
//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//Integers in each row are sorted from left to right.
//The first integer of each row is greater than the last integer of the previous row.
//Example 1:
//
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
//Example 2:
//
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false


public class Search2DMatrix {
	// 这里是用了俩次二分查找
	// 也可以将二维数组并成一维数组
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix.length == 0 || matrix[0].length == 0) return false;
        int start = 0,end = matrix.length - 1;
        int matrix_line = 0;
        while(start <= end) {
        	int middle = (start + end) / 2;
    		if(target == matrix[middle][0]) {
    			return true;
    		}else if(target > matrix[middle][0]) {
    			start = middle + 1;
    		}else if(target < matrix[middle][0]) {
    			end = middle - 1;
    		}	
        }
        // 注意查找的位置。这里不是插入，不应该用start
        matrix_line = Math.max(end, 0);
        start = 0;
        end = matrix[matrix_line].length - 1;
        while(start <= end) {
        	int middle = (start + end) / 2;
    		if(target == matrix[matrix_line][middle]) {
    			return true;
    		}else if(target > matrix[matrix_line][middle]) {
    			start = middle + 1;
    		}else if(target < matrix[matrix_line][middle]) {
    			end = middle - 1;
    		}	
        }
        return false;
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] matrix = {
//			  {1,   3,  5,  7},
//			  {10, 11, 16, 20},
//			  {23, 30, 34, 50}};
//		int target = 4;
		int[][] matrix = {{1}};
		int target = 2;
		Search2DMatrix sdm = new Search2DMatrix();
		boolean isExist = sdm.searchMatrix(matrix, target);
		System.out.println(isExist);

	}

}
