import java.io.*;
import java.util.*;

public class Main {
	static final int DIGIT = 10;
	static int[] permu;
	static boolean[] isBroken;
	static int N, M;
	static int channelLen;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		channelLen = String.valueOf(N).length();
        isBroken = new boolean[DIGIT];
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int button = Integer.parseInt(st.nextToken());
				isBroken[button] = true;
			}
		}

		permu = new int[channelLen + 1];
		res = Math.abs(N - 100);
		permutation(0);
		
		bw.write(res + "\n");
        bw.flush();
	}

	static void permutation(int cnt) {
		if(cnt != 0) {
			int num = 0;
			for (int i = 0; i < cnt; i++) {
				int n = permu[i];
				num *= 10;
				num += n;
			}
			
			String numCnt = String.valueOf(num);
			int buttonCnt = numCnt.length() + Math.abs(num - N);
			res = Math.min(res, buttonCnt);
		}
		
		if(cnt < channelLen + 1) {
			for(int i = 0; i < DIGIT; i++) {
				if(!isBroken[i]) {
					permu[cnt] = i;
					permutation(cnt + 1);
				}
			}
		}
	}
}