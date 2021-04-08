package noClassification;
import java.util.ArrayList;
import java.util.List;

//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in 
//spiral order.
//
//Example 1:
//
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//Output: [1,2,3,6,9,8,7,4,5]
//Example 2:
//
//Input:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]

// ÂÝÐýÊä³ö¾ØÕó


public class SpiralMatrix {
	
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> res = new ArrayList<Integer>();
    	
    	if(matrix.length == 0) return res;
    	
    	int row = matrix.length-1;
    	int col = matrix[0].length-1;
    	
    	int row_begin = 0;
    	int col_end = col;
    	int row_end = row;
    	int col_begin = 0;
    	
    	while(row_begin <= row_end && col_begin <= col_end) {
    		for(int col_index = col_begin; col_index <= col_end; col_index++) {
    			res.add(matrix[row_begin][col_index]);
    		}
    		row_begin++;
    		
    		for(int row_index = row_begin; row_index <= row_end; row_index++) {
    			res.add(matrix[row_index][col_end]);
    		}
    		col_end--;
    		
    		if(col_begin < col_end) {
    			for(int col_index = col_end; col_index >= col_begin; col_index--) {
        			res.add(matrix[row_end][col_index]);
        		}
        		row_end--;
    		}
    		
    		if(row_begin < row_end) {
    			for(int row_index = row_end; row_index >= row_begin; row_index--) {
        			res.add(matrix[row_index][col_begin]);
        		}
        		col_begin++;
    		}
    		
    	}
    
        return res;
    }
	

	public static void main(String[] args) {
		int[][] a = {{1,2},{3,4}};
		SpiralMatrix sm = new SpiralMatrix();
		List<Integer> res = sm.spiralOrder(a);
		System.out.println(res);
	}

}
