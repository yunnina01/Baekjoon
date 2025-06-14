import java.io.*;
import java.util.*;

public class Main {
	static int[] time;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		time = new int[N];
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			time[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, time[i]);
		}
		System.out.println(binarySearch(min));
	}
	
	static long binarySearch(int min) {
		long left = 0;
		long right = M * (long)min;
		while(left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			for(int i = 0; i < N; i++)
				cnt += mid / time[i];

			if(cnt >= M)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return left;
	}
}