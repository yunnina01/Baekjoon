import java.io.*;
import java.util.*;

public class Main {
    static int[] zrr;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String S = br.readLine();

        zrr = new int[100000];
        len = S.length();
        Z(S);

        int res = K >= N ? N : 0;
        for(int i = 1; i < N; i++) {
            if(i + zrr[i] == N) {
                int need = (i - zrr[i] % i) % i;
                if(need <= K)
                    res = Math.max(res, i);
            }
        }
        System.out.println(res);
    }

    static void Z(String s) {
        int left = 0, right = 0;
        zrr[0] = len;
        for(int i = 1; i < len; i++) {
            if(right < i) {
                zrr[i] = 0;
                while(i + zrr[i] < len && s.charAt(zrr[i]) == s.charAt(i + zrr[i]))
                    zrr[i]++;

                if(zrr[i] > 0) {
                    left = i;
                    right = i + zrr[i] - 1;
                }
            } else {
                if(i + zrr[i - left] - 1 < right)
                    zrr[i] = zrr[i-left];
                else {
                    zrr[i] = right - i + 1;
                    while(i + zrr[i] < len && s.charAt(zrr[i]) == s.charAt(i + zrr[i]))
                        zrr[i]++;

                    if(zrr[i] > 0) {
                        left = i;
                        right = i + zrr[i] - 1;
                    }
                }
            }
        }
    }
}