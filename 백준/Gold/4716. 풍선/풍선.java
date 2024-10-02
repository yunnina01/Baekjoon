import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while(true) {
            st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(N == 0 && A == 0 && B == 0)
                break;

            PriorityQueue<Team> pq = new PriorityQueue<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int K = Integer.parseInt(st.nextToken());
				int Da = Integer.parseInt(st.nextToken());
				int Db = Integer.parseInt(st.nextToken());

                int len;
                boolean isA;
				if(Da < Db) {
					len = Db - Da;
					isA = true;
				} else {
					len = Da - Db;
					isA = false;
				}
				pq.offer(new Team(len, K, isA, Da));
			}
            
            int res = 0;
            while(!pq.isEmpty()) {
                Team now = pq.poll();
                if(now.isA) {
                    if(now.num > A) {
                        res += A * now.aLen;
                        now.num -= A;
                        A = 0;
                        res += now.num * (now.aLen + now.len);
                        B -= now.num;
                    } else {
                        res += now.num * now.aLen;
                        A -= now.num;
                    }
                } else {
                    if(now.num > B) {
                        res += B * (now.aLen - now.len);
                        now.num -= B;
                        B = 0;
                        res += now.num * now.aLen;
                        A -= now.num;
                    } else {
                        res += now.num * (now.aLen - now.len);
                        B -= now.num;
                    }
                }
            }
			sb.append(res + "\n");
		}
		System.out.print(sb);
	}
}

class Team implements Comparable<Team> {
	int len, num, aLen;
	boolean isA;

	Team(int len, int num, boolean isA, int aLen) {
		this.len = len;
		this.num = num;
		this.isA = isA;
		this.aLen = aLen;
	}

	@Override
	public int compareTo(Team o) {
		return o.len - this.len;
	}
}