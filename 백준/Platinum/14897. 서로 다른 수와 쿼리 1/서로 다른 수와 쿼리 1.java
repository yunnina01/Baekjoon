import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Query.sqrtN = (int)Math.sqrt(N);

        HashMap<Integer, Integer> compression = new HashMap<>();
        int[] arr = new int[N + 1];
        int compNum = 0;
		st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(!compression.containsKey(a))
                compression.put(a, compNum++);
            arr[i] = compression.get(a);
        }
        compression = null;

        int Q = Integer.parseInt(br.readLine());

        List<Query> queries = new ArrayList<>(Q);
        for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
            queries.add(new Query(l, r, i));
		}
        Collections.sort(queries);

        int[] resArr = new int[Q];
        int[] cnt = new int[compNum];
        int res = 0;
        for(int i = queries.get(0).s; i <= queries.get(0).e; i++) {
            if(++cnt[arr[i]] == 1)
				res++;
		}
        resArr[queries.get(0).order] = res;

        for(int idx = 1; idx < queries.size(); idx++) {
            int cs = queries.get(idx).s;
            int ce = queries.get(idx).e;
            int bs = queries.get(idx-1).s;
            int be = queries.get(idx-1).e;

            for(int i = bs; i < cs; i++) {
				if(--cnt[arr[i]] == 0)
					res--;
			}
            for(int i = cs; i < bs; i++) {
				if(++cnt[arr[i]] == 1)
					res++;
			}
            for(int i = be + 1; i <= ce; i++) {
				if(++cnt[arr[i]] == 1)
					res++;
			}
            for(int i = ce + 1; i <= be; i++) {
				if(--cnt[arr[i]] == 0)
					res--;
			}
            resArr[queries.get(idx).order] = res;
        }

        for(int i = 0; i < Q; i++)
            sb.append(resArr[i] + "\n");
        bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
    }
}

class Query implements Comparable<Query> {
    static int sqrtN;
    int s, e, order;

    public Query(int s, int e, int order) {
        this.s = s;
        this.e = e;
        this.order = order;
    }

    @Override
    public int compareTo(Query o) {
        int thisS = this.s / sqrtN;
        int oS = o.s / sqrtN;
        if(thisS == oS)
			return this.e - o.e;
        return thisS - oS;
    }
}