import java.io.*;
import java.util.*;

public class Main {
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		A = new int[N];
		for(int i = 0; i < N; i++)
			A[i] = Integer.parseInt(br.readLine());
		Arrays.sort(A);

		System.out.println(twoPointer(N, M));
	}
	
	static int twoPointer(int N, int M) {
		int left = 0, right = 0;
		int diff = A[right] - A[left];
		int min = Integer.MAX_VALUE;
		while(left < N - 1) {
			if(diff < M) {
				right++;
				if(right == N)
                    break;
				diff = A[right] - A[left];
			} else {
				left++;
				diff = A[right] - A[left];
			}

			if(diff >= M)
				min = Math.min(min, diff);
		}
		return min;
	}
}