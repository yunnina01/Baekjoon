import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;
	static final int RADIX = 10;
	static int[] sum, fact, left, right, facts, trees;
	static int K, endIdx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		facts = new int[K + 1];
		facts[K] = 0;
		facts[K - 1] = 1;
		
		long j = 2;
		for(int i = K - 2; i > 0; i--, j++)
			facts[i] = (int)((long)facts[i + 1] * j % MOD);
		
		int power, height;
		for(power = 1, height = 1; power < K; power <<= 1, height++);

		int size = (power << 1) - 1 + K * height;
		sum = new int[size];
		fact = new int[size];
		left = new int[size];
		right = new int[size];

		trees = new int[K + 1];
		endIdx = 0;
		trees[0] = endIdx++;
		init(trees[0], 1, K);

		int[] arr = new int[K + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			trees[i] = update(trees[i - 1], 1, K, i, arr[i]);
		}

		int val = 1;
		for(int i = 1; i <= K; i++)
			val = (val + (int)((long)getSum(trees[i], trees[K], 1, K, 1, arr[i] - 1) * facts[i] % MOD)) % MOD;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int res = val;
			if(arr[A] > arr[B]) {
				res = (res - (int)((long)getSum(trees[A], trees[K], 1, K, arr[B], arr[A] - 1) * facts[A] % MOD)) % MOD;
				res = (res - getFact(trees[A], trees[B - 1], 1, K, arr[B], arr[A])) % MOD;
				res = (res + (int)((long) getSum(trees[B], trees[K], 1, K, arr[B], arr[A]) * facts[B] % MOD)) % MOD;
			} else {
				res = (res + (int)((long)getSum(trees[A], trees[K], 1, K, arr[A] + 1, arr[B]) * facts[A] % MOD)) % MOD;
				res = (res + getFact(trees[A], trees[B - 1], 1, K, arr[A], arr[B])) % MOD;
				res = (res - (int)((long)getSum(trees[B], trees[K], 1, K, arr[A], arr[B]) * facts[B] % MOD)) % MOD;
			}
			System.out.println((res + MOD) % MOD);
		}
	}
	
	static void init(int node, int start, int end) {
		if(start == end)			return;
		int mid = start + end >> 1;
		left[node] = endIdx++;
		right[node] = endIdx++;
		init(left[node], start, mid);
		init(right[node], mid + 1, end);
	}
	
	static int update(int node, int start, int end, int idx, int num) {
		int newNode = endIdx++;
		if(start == end) {
			sum[newNode] = 1;
			fact[newNode] = facts[idx];
			return newNode;
		}

		int mid = start + end >> 1;
		if(num <= mid) {
			left[newNode] = update(left[node], start, mid, idx, num);
			right[newNode] = right[node];
		} else {
			left[newNode] = left[node];
			right[newNode] = update(right[node], mid + 1, end, idx, num);
		}
		sum[newNode] = (sum[left[newNode]] + sum[right[newNode]]) % MOD;
		fact[newNode] = (fact[left[newNode]] + fact[right[newNode]]) % MOD;
		return newNode;
	}
	
	static int getSum(int l, int r, int start, int end, int min, int max) {
		if(min > end || max < start)
			return 0;
		if(min <= start && max >= end)
			return sum[r] - sum[l];
		int mid = start + end >> 1;
		return (getSum(left[l], left[r], start, mid, min, max) + getSum(right[l], right[r], mid + 1, end, min, max)) % MOD;
	}
	
	static int getFact(int l, int r, int start, int end, int min, int max) {
		if(min > end || max < start)
			return 0;
		if(min <= start && max >= end)
			return fact[r] - fact[l];
		int mid = start + end >> 1;
		return (getFact(left[l], left[r], start, mid, min, max) + getFact(right[l], right[r], mid + 1, end, min, max)) % MOD;
	}
}