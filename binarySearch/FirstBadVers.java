package binarySearch;
//You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
//
//Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
//
//You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
//
//Example:
//
//Given n = 5, and version = 4 is the first bad version.
//
//call isBadVersion(3) -> false
//call isBadVersion(5) -> true
//call isBadVersion(4) -> true
//
//Then 4 is the first bad version. 


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// �����������ø���ķ�����
public class FirstBadVers {
	
//    public int firstBadVersion(int n) {
//    	if(n == 1) return n;
//        int start = 1;
//        int end = n;
//        int bad_version = 1;
//        while(start <= end){
//        	// ����д�����ԣ�middle = (start + end) / 2,
//        	// ����д����С��������û��ϵ�������������󣬾�����Ӵ���
//            int middle = start + (end - start) / 2;
//            if(isBadVersion(middle)){
//                bad_version = middle;
//                end = middle - 1;
//            }else{
//                start = middle + 1;
//            }
//            
//        }
//        return bad_version;
//    }
    
    // ʹ�û��ݷ�
    public int firstBadVersion(int n) {
    	return helper(1,n);
    }

    public int helper(int start, int end) {
		if(start >= end) return start;
		int middle = start + (end - start) / 2;
		// ��������治ͬ�����ﲢû�м�¼����������middle���ܾ��ǵ�
		if(isBadVersion(middle)) return helper(start, middle);
		else return helper(middle + 1, end);
	}

	private boolean isBadVersion(int middle) {
		if(middle >= 2) {
			return true;
		}else {
			return false;
		}
	}

	public static void main(String[] args) {
		FirstBadVers fbv = new FirstBadVers();
		int n = 3;
		int isBad = fbv.firstBadVersion(n);
		System.out.println(isBad);

	}

}
