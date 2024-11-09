import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
        
		int[] arr = new int[N];
		int max = 0;
        st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		int[] prefixl = new int[N];
		int[] prefixr = new int[N];
		int minl = Integer.MAX_VALUE;
		int minr = Integer.MAX_VALUE;
		for(int i = 1; i < N; i++) {
			prefixl[N - 1 - i] = prefixl[N - i] + arr[N - 1 - i];
			minl = Math.min(minl, prefixl[N - 1 - i] + arr[N - 1 - i]);
			prefixr[i] = prefixr[i - 1] + arr[i];
			minr = Math.min(minr, prefixr[i] + arr[i]);
		}

		int res = 0;
		res = Math.max(res, prefixr[N - 1] * 2 - minr);
		res = Math.max(res, prefixl[0] * 2 - minl);
		res = Math.max(res, prefixr[N - 1] - arr[N - 1] + max);
		System.out.println(res);
	}

}