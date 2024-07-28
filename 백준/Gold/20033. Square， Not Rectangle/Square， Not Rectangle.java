import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int[] H = new int[N];
		int left = 1;
		int right = 0;
		for(int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, H[i]);
		}

		int res = 0;
		while(left <= right) {
			int mid = (left + right) / 2;
			int count = findSquare(H, mid);
			if(mid == count) {
				res = Math.max(res, mid);
				break;
			} else if (mid < count) {
				res = Math.max(res, mid);
				left = mid + 1;
			} else
				right = mid - 1;
		}
        System.out.println(res);
	}

	static int findSquare(int[] arr, int size) {
		int ret = 0;
		int cnt = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] >= size) {
				cnt++;
				ret = Math.max(ret, cnt);
			} else
				cnt = 0;
		}
		return ret;
	}
}