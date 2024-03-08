import java.io.*;
import java.util.*;

public class Main {
    static int[] first, second, a, b, c;
    static int N, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int res = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            first = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            second = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            
            a = new int[N];
            b = new int[N];
            c = new int[N + 1];
            a[0] = b[0] = 1;
            c[0] = 0;
            getMinCount(0);
            res = Math.min(res, c[N]);

            if(N > 1) {
                if(first[0] + first[N - 1] <= W && second[0] + second[N - 1] <= W) {
                    a[1] = b[1] = 1;
                    c[1] = 0;
                    getMinCount(1);
                    res = Math.min(res, c[N - 1] + 2);
                }

                if(first[0] + first[N - 1] <= W) {
                    a[1] = 2;
                    b[1] = second[0] + second[1] > W ? 2 : 1;
                    c[1] = 1;
                    getMinCount(1);
                    res = Math.min(res, b[N - 1] + 1);
                }

                if(second[0] + second[N - 1] <= W) {
                    a[1] = first[0] + first[1] > W ? 2 : 1;
                    b[1] = 2;
                    c[1] = 1;
                    getMinCount(1);
                    res = Math.min(res, a[N - 1] + 1);
                }
            }
            bw.write(res + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void getMinCount(int idx) {
        for(int i = idx; i < N; i++) {
            c[i + 1] = Math.min(a[i] + 1, b[i] + 1);
            if(first[i] + second[i] <= W)
                c[i+1] = Math.min(c[i + 1], c[i] + 1);
            if(i > 0 && first[i - 1] + first[i] <= W && second[i - 1] + second[i] <= W)
                c[i + 1] = Math.min(c[i + 1], c[i - 1] + 2);
            if(i < N - 1) {
                a[i + 1] = c[i + 1] + 1;
                b[i + 1] = c[i + 1] + 1;
                if(first[i] + first[i + 1] <= W)
                    a[i + 1] = Math.min(a[i + 1], b[i] + 1);
                if(second[i] + second[i + 1] <= W)
                    b[i + 1] = Math.min(b[i + 1], a[i] + 1);
           }
       }
    }
}