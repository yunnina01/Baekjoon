import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000;
	static int[][] route;
    static int[] nums;
    static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		route = new int[n][n];
		for(int i = 0; i < n; i++)
			Arrays.fill(route[i], INF);
		
		while(r-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			
			route[a][b] = route[b][a] = l;
		}

		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(route[i][j] > route[i][k] + route[k][j])
						route[i][j] = route[i][k] + route[k][j];
				}
			}
		}
		
		int res = 0;
		for(int i = 0; i < n; i++) {
			int sum = nums[i] + search(i);
			if(sum > res)
                res = sum;
		}
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}

	static int search(int x) {
		int res = 0;
		for(int i = 0; i < n; i++) {
			if(x == i)
                continue;
			if(route[x][i] <= m)
                res += nums[i];
		}
		return res;
	}
}