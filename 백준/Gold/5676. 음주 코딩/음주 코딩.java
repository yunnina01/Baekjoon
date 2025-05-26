import java.io.*;
import java.util.*;

public class Main {
	static int[] arr;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			String input = br.readLine();
			if(input == null)
                break;

			st = new StringTokenizer(input);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
 
			size = 2;
			while(true) {
				if(size >= N) {
					size *= 2;
					break;
				}
				size *= 2;
			}

			arr = new int[size];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int X = Integer.parseInt(st.nextToken());
				if(X > 0)
                    arr[size / 2 + i] = 1;
				else if(X < 0)
                    arr[size / 2 + i] = -1;
				else
                    arr[size / 2 + i] = 0;
			}

			construct();

			while(K-- > 0) {
				st = new StringTokenizer(br.readLine());
				String C = st.nextToken();
				int i = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());

				if(C.equals("P")) {
					int num = findMul(1, 0, size / 2 - 1, i - 1, V - 1);
					if(num == 1)
						sb.append("+");
					else if(num == -1)
						sb.append("-");
					else
                        sb.append("0");
				} else {
					if(V > 0)
						update(i - 1, 1);
					else if(V < 0)
						update(i - 1, -1);
					else
    					update(i - 1, 0);
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--)
			arr[i] = arr[i * 2] * arr[i * 2 + 1];
	}
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = arr[i * 2] * arr[i * 2 + 1];
		}
	}
	
	static int findMul(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 1;
		if(left <= start && right >= end)
            return arr[node];
		int mid = (start + end) / 2;
		return findMul(node * 2, start, mid, left, right) * findMul(node * 2 + 1, mid + 1, end, left, right);
	}
}