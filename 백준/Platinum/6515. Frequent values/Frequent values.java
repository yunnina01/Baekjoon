import java.io.*;
import java.util.*;

public class Main {
	static List<Query> queries;
	static int[] cnt, arr, cntArr;
	static int n, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0)
                break;
            int q = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken()) + 100000;

            queries = new ArrayList<>();
            for(int k = 0; k < q; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                queries.add(new Query(i, j, k));
            }

            int sqrtN = (int)Math.sqrt(n);
            Collections.sort(queries, new Comparator<Query>() {
                @Override
                public int compare(Query q1, Query q2) {
                    if(q1.start / sqrtN != q2.start / sqrtN)
                        return q1.start / sqrtN - q2.start / sqrtN;
                    return q1.end - q2.end;
                }
            });
            
            cnt = new int[202020];
            cntArr = new int[n + 1];
            max = Integer.MIN_VALUE;
            int s = queries.get(0).start;
            int e = queries.get(0).end;
            for(int i = s; i <= e; i++)
                add(arr[i]);

            int[] res = new int[q];
            res[queries.get(0).num] = max;            
            for(int i = 1; i < queries.size(); i++) {
                Query query = queries.get(i);
                while(s > query.start)
                    add(arr[--s]);
                while(e < query.end)
                    add(arr[++e]);
                while(s < query.start)
                    delete(arr[s++]);
                while(e > query.end)
                    delete(arr[e--]);
                res[query.num] = max;
            }
            
            for(int x : res)
                sb.append(x + "\n");
		}
		System.out.print(sb);
	}

	static void add(int x) {
		if(cnt[x] > 0)
            cntArr[cnt[x]]--;
		cnt[x]++;
		cntArr[cnt[x]]++;
		max = Math.max(max, cnt[x]);
	}
	
	static void delete(int x) {
		if(cnt[x] == 0)
            return;
		cntArr[cnt[x]]--;
		if(cntArr[cnt[x]] == 0 && max == cnt[x])
            max--;
		cnt[x]--;
		cntArr[cnt[x]]++;
	}
}

class Query {
	int start, end, num;

	Query(int start, int end, int num) {
		this.start = start;
		this.end = end;
		this.num = num;
	}
}