import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Pollution[] pollutions = new Pollution[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			long p = Long.parseLong(st.nextToken());
			pollutions[i] = new Pollution(x, p);
		}
		Arrays.sort(pollutions);

		long left = 0;
		long bef = pollutions[0].x;
		for(int i = 0; i < N; i++) {
			left += pollutions[i].p;
			left += (pollutions[i].x - bef) * i;
			bef = pollutions[i].x;
		}

		long right = 0;
		bef = pollutions[N - 1].x;
		for(int i = N - 1; i >= 0; i--) {
			right += pollutions[i].p;
			right += (bef - pollutions[i].x) * (N - 1 - i);
			bef = pollutions[i].x;
		}

		long low = left - pollutions[0].p - (pollutions[N - 1].x - pollutions[0].x);
		long high = right - pollutions[0].p - (pollutions[1].x - pollutions[0].x) * (N - 1);
		long min = Math.min(low, high);
		long res = Math.min(INF, min);

		for(int i = 1; i < N - 1; i++) {
			low = left - pollutions[i].p - (pollutions[N - 1].x - pollutions[i].x);
			high = right - pollutions[i].p - (pollutions[i].x - pollutions[0].x);

			min = Math.min(low, high);
			res = Math.min(res, min);
		}

		low = left - pollutions[N - 1].p - (pollutions[N - 1].x - pollutions[N - 2].x) * (N - 1);
		high = right - pollutions[N - 1].p - (pollutions[N - 1].x - pollutions[0].x);

		min = Math.min(low, high);
		System.out.println(Math.min(res, min));
	}
}

class Pollution implements Comparable<Pollution> {
    long x, p;

    Pollution(long x, long p) {
        this.x = x;
        this.p = p;
    }

    @Override
    public int compareTo(Pollution pollution) {
        return Long.compare(this.x, pollution.x);
    }
}