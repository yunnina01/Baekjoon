import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) + 1;
            int m1 = Integer.parseInt(st.nextToken()) + 1;
            int n2 = Integer.parseInt(st.nextToken()) + 1;
            int m2 = Integer.parseInt(st.nextToken());

            double res = 1.0, mul;
            for(int i = 0; i < m1; i++)
                res = res * (n1 - i) / (n1 + n2 - i);
            mul = res;
            for(int i = 0; i < m2; i++)
                res += mul = mul * (m1 + i) / (i + 1) * (n2 - i) / (n1 + n2 - m1 - i);
            sb.append(res + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}