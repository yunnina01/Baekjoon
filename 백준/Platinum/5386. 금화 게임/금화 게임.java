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
            int S = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            if(K % 2 == 1)
                sb.append((S % 2 == 0 ? 0 : 1) + "\n");
            else {
                S %= K + 1;
                sb.append((S == K ? K : (S % 2 == 0 ? 0 : 1)) + "\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}