import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		int last = arr[0];
		int idx = 0;
		for(int i = 1; i < N; i++) {
			idx = (idx + last) % N;
			while(arr[idx] != 0)
				idx = (idx + 1) % N;

			arr[idx] = Integer.parseInt(st.nextToken());
			last = arr[idx];
		}

		sb.append(N + "\n");
		for(int i = 0; i < N; i++)
			sb.append(arr[i] + " ");
		System.out.println(sb);
	}
}