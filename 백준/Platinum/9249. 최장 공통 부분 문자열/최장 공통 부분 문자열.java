import java.io.*;
import java.util.*;

public class Main {
    static int[] sa, lcp;
    static char[] line;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String A = br.readLine();
        String B = br.readLine();

        line = (String.format("%s&%s", A, B)).toCharArray();
        N = line.length;
        getSA();
        getlcp();

        int l = 0, s = 0;
        for(int i = 1; i < N; i++) {
            if(sa[i - 1] > A.length() != sa[i] < A.length())
                continue;
            if(lcp[i] > l) {
                l = lcp[i];
                s = i;
            }
        }
        sb.append(l + "\n");
        for(int i = 0; i < l; i++)
            sb.append(line[sa[s] + i]);
        bw.write(sb.toString());
        bw.flush();
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

            for(int i = 0; i < N; i++)
                cnt[r[i]]++;
            for(int i = 1; i < M; i++)
                cnt[i] += cnt[i - 1];
            for(int i = N - 1; i >= 0; i--)
                sa[--cnt[r[idx[i]]]] = idx[i];
            nr[sa[0]] = 1;

            for(int i = 1; i < N; i++)
                nr[sa[i]] = nr[sa[i - 1]] + ((r[sa[i - 1]] < r[sa[i]] || (r[sa[i - 1]] == r[sa[i]] && r[sa[i - 1] + d] < r[sa[i] + d])) ? 1 : 0);
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
        for(int k = 0, i = 0; i < N; i++) {
            if(isa[i] > 0) {
                for(int j = sa[isa[i] - 1]; Math.max(i + k, j + k) < N && line[i + k] == line[j + k]; k++);
                lcp[isa[i]] = (k != 0 ? k-- : 0);
            }
        }
    }
}