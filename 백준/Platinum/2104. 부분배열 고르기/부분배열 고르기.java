import java.io.*;
import java.util.*;

public class Main {
    static long[] sumTree;
    static int[] A, minTree;
	static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());		
		A = new int[N + 1];
		A[0] = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) 
			A[i] = Integer.parseInt(st.nextToken());

		sumTree = new long[N * 4];
		minTree = new int[N * 4];
		updateSum(1, 1, N);
		updateMin(1, 1, N);
		System.out.println(query(1, N));
	}

	static long updateSum(int node, int start, int end) {
		if(start == end) 
			return sumTree[node] = A[start];

		int mid = (start + end) >> 1;
		return sumTree[node] = updateSum(node * 2, start, mid) + updateSum(node * 2 + 1, mid + 1, end);
	}
	
	static int updateMin(int node, int start, int end) {
		if(start == end) 
			return minTree[node] = start;
		
		int mid = (start + end) >> 1;
		int left = updateMin(node * 2, start, mid);
		int right = updateMin(node * 2 + 1, mid + 1, end);
		return minTree[node] = getIndex(left, right);
	}
	
	static int getIndex(int left, int right) {
		if(A[left] < A[right])
            return left;
		return right;
	}

	static long query(int start, int end) {
		int min = pMin(1, 1, N, start, end);
		long range = pSum(1, 1, N, start, end) * A[min];
		if(start == end)
            return range;
		if(min > start) {
			long tmp = query(start, min - 1);
			if(range < tmp)
                range = tmp;
		}
		if(min < end) {
			long tmp = query(min + 1, end);
			if(range < tmp)
                range = tmp;
		}
		return range;
	}

	static long pSum(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return sumTree[node];
		
		int mid = (start + end) >> 1;
		return pSum(node * 2, start, mid, left, right) + pSum(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int pMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return minTree[node];
		
		int mid = (start + end) >> 1;
		int minLeft = pMin(node * 2, start, mid, left, right);
		int minRight = pMin(node * 2 + 1, mid + 1, end, left, right);
		return getIndex(minLeft, minRight);
	}
}