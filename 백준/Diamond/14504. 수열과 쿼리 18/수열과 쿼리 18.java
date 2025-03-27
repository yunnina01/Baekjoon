import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		int block = (int)Math.sqrt(N);
		int[][] bucket = new int[(N / block) + 1][block];
		int[] A = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			bucket[i / block][i % block] = A[i];
		}

		for(int i = 0; i < bucket.length; i++)
			Arrays.sort(bucket[i]);

		int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken()) - 1;
			if(q == 1) {
				int j = Integer.parseInt(st.nextToken()) - 1;
				int k = Integer.parseInt(st.nextToken());
				sb.append(sum(bucket, A, i, j, k, block) + "\n");
			} else {
				int k = Integer.parseInt(st.nextToken());
				int idx = find(bucket[i / block], block, A[i]);
				bucket[i / block][idx] = k;
				A[i] = k;
				Arrays.sort(bucket[i / block]);
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int sum(int[][] bucket, int[] arr, int left, int right, int k, int block) {
		int val = 0;
		if(left / block == right / block) {
			for(int i = left; i <= right; i++) {
				if(arr[i] > k)
					val++;
			}
			return val;
		}

		while(left % block != 0) {
			if(arr[left++] > k)
				val++;
		}
		while(right % block != block - 1) {
			if(arr[right--] > k)
				val++;
		}
		for(int i = left / block; i <= right / block; i++)
			val += count(bucket[i], block, k);
		return val;
	}
	
	static int count(int[] bucket, int n, int k) {
		int left = 0;
		int right = n;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(bucket[mid] <= k)
				left = mid + 1;
			else
				right = mid;
		}
		return n - right;
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
}