import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();       
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
            M %= 9;
            if(M == 0)
                M = 9;
            int x = 0, y = 0;
            int dig = 1, dir = 0;

            if(M % 3 != 0) {
                K %= 12;
                for(int i = 0; i < K; i++) {
                    x += dig * dx[dir];
                    y += dig * dy[dir];
                    dir = (dir + 1) % 4;
                    dig *= M;
                    if(dig >= 10)
                        dig %= 9;
                    if(dig == 0)
                        dig = 9;
                }
            } else {
                if(K >= 7)
                    K = K % 4 + 4;
                for(int i = 0; i < K; i++) {
                    x += dig * dx[dir];
                    y += dig * dy[dir];
                    dir = (dir + 1) % 4;
                    dig *= M;
                    if(dig >= 10)
                        dig %= 9;
                    if(dig == 0)
                        dig = 9;
                }
            }
            sb.append(x + " " + y + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
}