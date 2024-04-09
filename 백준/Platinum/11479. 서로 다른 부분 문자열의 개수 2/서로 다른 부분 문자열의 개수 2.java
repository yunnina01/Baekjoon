import java.io.*;
import java.util.*;

public class Main {
    static char[] S;
    static int[] sa, lcp;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        S = br.readLine().toCharArray();

        len = S.length;
        getSA();

        long res = 0;
        for(int i : sa)
            res += i + 1;

        getlcp();
        for(int i : lcp)
            res -= i;
        System.out.println(res);
    }

    static void getSA() {
        sa = new int[len];
        int[] r = new int[len * 2];
        for(int i = 0; i < len; i++) {
            sa[i] = i;
            r[i] = S[i];
        }

        int M = Math.max(257, len + 1);
        int[] nr = new int[len * 2];
        int[] cnt = new int[M];
        int[] idx = new int[len];
        for(int d = 1; d < len; d <<= 1) {
            Arrays.fill(cnt, 0);
            for(int i = 0; i < len; i++)
                cnt[r[i + d]]++;
            for(int i = 1; i < M; i++)
                cnt[i] += cnt[i - 1];
            for(int i = len - 1; i >= 0; i--)
                idx[--cnt[r[i + d]]] = i;

            Arrays.fill(cnt, 0);
            for(int i = 0; i < len; i++)
                cnt[r[i]]++;
            for(int i = 1; i < M; i++)
                cnt[i] += cnt[i - 1];
            for(int i = len - 1; i >= 0; i--)
                sa[--cnt[r[idx[i]]]] = idx[i];
            nr[sa[0]] = 1;

            for(int i = 1; i < len; i++)
                nr[sa[i]] = nr[sa[i - 1]] + ((r[sa[i - 1]] < r[sa[i]] || (r[sa[i - 1]] == r[sa[i]] && r[sa[i - 1] + d] < r[sa[i] + d])) ? 1 : 0);
            for(int i = 0; i < len; i++)
                r[i] = nr[i];
            if(r[sa[len - 1]] == len)
                break;
        }
    }

    static void getlcp() {
        lcp = new int[len];
        int[] isa = new int[len];
        for(int i = 0; i < len; i++)
            isa[sa[i]] = i;
        for(int k = 0, i = 0; i < len; i++) {
            if(isa[i] > 0) {
                for(int j = sa[isa[i] - 1]; Math.max(i + k, j + k) < len && S[i + k] == S[j + k]; k++);
                lcp[isa[i]] = (k != 0 ? k-- : 0);
            }
        }
    }
}