import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if(M + K > N + 1 || N > M * K) {
			System.out.println("-1");
			return;
		}

		for(int i = K; i > 0; i--)
            sb.append(i + " ");
		if(M != 1) {
            int size = (N - K) / (M - 1);
            int remain = (N - K) % (M - 1);
            int last = K;
            if(remain > 0)
                size++;

            for(int i = 0; i < M - 1; i++) {
                for(int cnt = size; cnt > 0; cnt--)
                    sb.append(last + cnt + " ");
                last += size;
                if(--remain == 0)
                    size--;
            }
        }
		bw.write(sb.toString());
		bw.flush();
	}
}