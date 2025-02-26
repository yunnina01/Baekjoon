import java.io.*;
import java.util.*;

public class Main {
    static int[][] buckets;
    static int[] A;
    static int N, sqrtN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		sqrtN = (int)Math.sqrt(N);
		A = new int[N];
		buckets = new int[(N / sqrtN) + 1][sqrtN];
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			buckets[i / sqrtN][i % sqrtN] = A[i];
		}
		for(int i = 0; i < buckets.length; i++)
			Arrays.sort(buckets[i]);
		int M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken()) - 1;
			if(q == 1) {
				int v = Integer.parseInt(st.nextToken());
				int idx = find(buckets[i / sqrtN], sqrtN, A[i]);
				buckets[i / sqrtN][idx] = v;
				A[i] = v;
				Arrays.sort(buckets[i / sqrtN]);
			} else {
				int j = Integer.parseInt(st.nextToken()) - 1;
				int k = Integer.parseInt(st.nextToken());
				sb.append(query(i, j, k) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
    
	static int find(int[] bucket, int n, int num) {
		int left = 0;
		int right = n;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(bucket[mid] < num)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
	
	static int query(int left, int right, int k) {
		int val = 0;
		if(left / sqrtN == right / sqrtN) {
			for(int i = left; i <= right; i++) {
				if(A[i] > k)
					val++;
			}
			return val;
		}
		while(left % sqrtN != 0) {
			if(A[left++] > k)
				val++;
		}
		while(right % sqrtN != sqrtN - 1) {
			if(A[right--] > k)
				val++;
		}
		for(int i = left / sqrtN; i <= right / sqrtN; i++)
			val += count(buckets[i], k);
		return val;
	}
	
	static int count(int[] bucket, int k) {
		int left = 0;
		int right = sqrtN;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(bucket[mid] <= k)
				left = mid + 1;
			else
				right = mid;
		}
		return sqrtN - right;
	}
}