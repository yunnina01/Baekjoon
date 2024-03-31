import java.io.*;
import java.util.*;

public class Main {
	static int[] cnt;
	static int C, sqrtN;

    static class Query implements Comparable<Query> {
        int idx, s, e;
    
        Query(int idx, int s, int e) {
            this.idx = idx;
            this.s = s;
            this.e = e;
        }
    
        @Override
        public int compareTo(Query o) {
            int sn1 = this.s / sqrtN;
            int sn2 = o.s / sqrtN;
            
            if(sn1 != sn2)
                return sn1 - sn2;
            return this.e - o.e;
        }
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sqrtN = (int)Math.sqrt(N);
		
		int[] hat = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N;i++)
			hat[i] = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		
		List<Query> query = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
			query.add(new Query(i, A, B));
		}
        query.sort(null);
		
		cnt = new int[C + 1];
		int s = query.get(0).s;
		int e = query.get(0).e;
		for(int i = s; i <= e; i++)
			cnt[hat[i]]++;
		
        int[] res = new int[M];
		res[query.get(0).idx] = check(s, e);
		for(int i = 1; i < M; i++) {
			while(query.get(i).s < s)
                cnt[hat[--s]]++;
			while(query.get(i).s > s)
                cnt[hat[s++]]--;
			while(query.get(i).e < e)
                cnt[hat[e--]]--;
			while(query.get(i).e > e)
                cnt[hat[++e]]++;
			res[query.get(i).idx] = check(s, e);
		}
		
		for(int i : res) {
			if(i == 0)
				sb.append("no\n");
			else
				sb.append("yes " + i + "\n");
		}
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
	
	static int check(int s, int e) {
		int limit = (e - s + 1) / 2;
		for(int i = 1; i <= C; i++) {
			if(cnt[i] > limit)
				return i;
		}
		return 0;
	}
}