import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            long sum = 0;
            int plus = 0;
            int minus = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                int A = Integer.parseInt(st.nextToken());
                if(A == 0)
                    continue;

                if(A < 0) {
                    if(sum <= 0)
                        sum += A;
                    else {
                        if(A + sum > 0)
                            sum += A;
                        else {
                            plus++;
                            sum = A;
                        }
                    }
                } else {
                    if(sum > 0) {
                        plus++;
                        sum = A;
                    } else {
                        if(A + sum > 0)
                            sum += A;
                        else {
                            minus++;
                            sum = A;
                        }
                    }
                }
            }

            if(sum > 0)
                plus++;
            else if(sum < 0)
                minus++;
            sb.append(plus > minus ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }
}