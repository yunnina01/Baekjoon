import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[] a = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
            a[i] = Integer.parseInt(st.nextToken());

		List<Query> query = new ArrayList<>();	
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			query.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}
		
		int sqrtN = (int)Math.sqrt(n);
		query.sort((q1, q2) -> {
			if(q1.left / sqrtN == q2.left / sqrtN)
				return Integer.compare(q1.right, q2.right);
			return Integer.compare(q1.left / sqrtN, q2.left / sqrtN);
		});
		
        long[] cnt = new long[1000001];
		int start = query.get(0).left;
		int	end = query.get(0).right;
		long sum = 0;
		for(int i = start; i <= end; i++) 
			sum += (2 * cnt[a[i]]++ + 1) * a[i];

        long[] res = new long[t];
		res[query.get(0).idx] = sum;
		
		for(int i = 1; i < t; i++) {
			Query now = query.get(i);
			int left = now.left;
			int	right = now.right;
		
			while(start < left) {
				sum -= (2L * --cnt[a[start]] + 1) * a[start]; 
				start++;
			}
			while(start > left) {
				start--;
				sum += (2L * cnt[a[start]]++ + 1) * a[start];
			}
			while(end < right) {
				end++;
				sum += (2L * cnt[a[end]]++ + 1) * a[end];
			}
			while(end > right) {
				sum -= (2L * --cnt[a[end]] + 1) * a[end];
				end--;
			}
			res[now.idx]= sum;
		}
		
		for(long i : res)
            sb.append(i + "\n");
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
}

class Query {
	int left, right, idx;
	
    Query(int left, int right, int idx) {
		this.left = left;
		this.right = right;
		this.idx = idx;
	}
}