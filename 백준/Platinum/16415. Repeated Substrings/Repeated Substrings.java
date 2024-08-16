import java.io.*;
import java.util.*;

public class Main {
    static char[] line;
    static int[] sa, lcp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        line = br.readLine().toCharArray();

        N = line.length;
        getSA();
        getlcp();

        List<String> res = new ArrayList<>();
        int[] cpy = Arrays.copyOf(lcp, N);
        Arrays.sort(cpy);

        for(int i = 0; i < N; i++) {
            if(lcp[i] != cpy[N - 1])
                continue;

            sb.setLength(0);
            for(int j = 0; j < lcp[i]; j++)
                sb.append(line[sa[i] + j]);
            res.add(sb.toString());
        }
        Collections.sort(res);
        System.out.println(res.get(0));
    }

    static void getSA() {
        int M = Math.max(257, N + 1);
        sa = new int[N];
        int[] r = new int[N * 2];
        int[] nr = new int[N * 2];
        int[] cnt = new int[M];
        int[] idx = new int[N];
        for(int i = 0; i < N; i++) {
            sa[i] = i;
            r[i] = line[i];
        }

        for(int d = 1; d < N; d <<= 1) {
            Arrays.fill(cnt, 0);
            for(int i = 0; i < N; i++)
                cnt[r[i + d]]++;
            for(int i = 1; i < M; i++)
                cnt[i] += cnt[i - 1];
            for(int i = N - 1; i >= 0; i--)
                idx[--cnt[r[i + d]]] = i;

            Arrays.fill(cnt, 0);
            for(int i = 0;i < N; i++)
                cnt[r[i]]++;
            for(int i = 1;i < M; i++)
                cnt[i] += cnt[i - 1];
            for(int i = N - 1; i >= 0; i--)
                sa[--cnt[r[idx[i]]]] = idx[i];

            nr[sa[0]] = 1;
            for(int i = 1; i < N; i++)
                nr[sa[i]] = nr[sa[i-1]] + (r[sa[i - 1]] < r[sa[i]] || (r[sa[i - 1]] == r[sa[i]] && r[sa[i - 1] + d] < r[sa[i] + d]) ? 1 : 0);
            for(int i = 0; i < N; i++)
                r[i] = nr[i];
            if(r[sa[N - 1]] == N)
                break;
        }
    }

    static void getlcp() {
        lcp = new int[N];
        int[] isa = new int[N];
        for(int i = 0; i < N; i++)
            isa[sa[i]] = i;
        for(int i = 0, k = 0; i < N; i++) {
            if(isa[i] > 0) {
                for(int j = sa[isa[i] - 1]; Math.max(i + k, j + k) < N && line[i + k] == line[j + k]; k++);
                lcp[isa[i]] = (k !=0 ? k-- : 0);
            }
        }
    }
}