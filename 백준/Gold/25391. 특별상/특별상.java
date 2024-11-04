import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Score[] scores = new Score[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			scores[i] = new Score(a, b);
		}
		Arrays.sort(scores, (Comparator<Score>)(Score o1, Score o2) -> {return o2.ref - o1.ref;});

        HashSet<Integer> set = new HashSet<>();
        long res = 0;
		for(int i = 0; i < K; i++) {
			set.add(scores[i].ref);
			res += scores[i].org;
		}

		Arrays.sort(scores, (Comparator<Score>)(Score o1, Score o2) -> {return o2.org - o1.org;});
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(set.contains(scores[i].ref))
                continue;
			res += scores[i].org;
			cnt++;
			if(cnt == M)
                break;
		}
		System.out.println(res);
	}
}

class Score {
	int org, ref;

	Score(int org, int ref) {
		this.org = org;
		this.ref = ref;
	}
}