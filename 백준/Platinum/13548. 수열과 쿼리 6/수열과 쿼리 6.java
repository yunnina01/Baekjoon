import java.io.*;
import java.util.*;

public class Main {
    static int[] A, table, cnt;
    static int sqrtN;
    static int now;

    static class Query implements Comparable<Query> {
        int idx, start, end;

        public Query(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Query o) {
            if(start / sqrtN != o.start / sqrtN)
                return start / sqrtN - o.start / sqrtN;
            return end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for(int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());

        Query[] queries = new Query[M];
        for(int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            queries[k] = new Query(k, i, j);
        }

        sqrtN = (int) Math.sqrt(N);
        Arrays.sort(queries);

        table = new int[N + 1];
        cnt = new int[1000001];
        table[0] = N;
        for(int i = queries[0].start; i <= queries[0].end; i++) {
            table[cnt[A[i]]]--;
            cnt[A[i]]++;
            table[cnt[A[i]]]++;
            now = Math.max(now, cnt[A[i]]);
        }

        int[] res = new int[M + 1];
        res[queries[0].idx] = now;
        int left = queries[0].start;
        int right = queries[0].end;

        for(int i = 1; i < M; i++) {
            if(queries[i].start < left)
                plus(queries[i].start, left - 1);
            if(queries[i].end > right)
                plus(right + 1, queries[i].end);
            if(queries[i].start > left)
                minus(left, queries[i].start - 1);
            if(queries[i].end < right)
                minus(queries[i].end + 1, right);

            left = queries[i].start;
            right = queries[i].end;
            res[queries[i].idx] = now;
        }

        for(int i = 0; i < M; i++)
            sb.append(res[i] + "\n");
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void plus(int start, int end) {
        for(int i = start; i <= end; i++) {
            table[cnt[A[i]]]--;
            cnt[A[i]]++;
            table[cnt[A[i]]]++;
            now = Math.max(now, cnt[A[i]]);
        }
    }

    static void minus(int start, int end) {
        for(int i = start; i <= end; i++) {
            table[cnt[A[i]]]--;
            cnt[A[i]]--;
            table[cnt[A[i]]]++;
            if(table[now] == 0)
                now--;
        }
    }
}