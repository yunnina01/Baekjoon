import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<Problem> problems = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            problems.add(new Problem(K, T));
        }
        Collections.sort(problems);

        long fatigue = 0;
        long time = 0;
        for(int i = 0; i < N; i++) {
            Problem now = problems.get(i);
            if((long)now.K * now.T + fatigue > L) {
                time += (long)now.K * now.T + fatigue - L;
                time += now.T;
                fatigue = L;
                fatigue -= now.healing;
            } else {
                time += now.T;
                fatigue += now.K * now.T;
                fatigue -= now.healing;
            }
        }
        System.out.println(time);
    }
}

class Problem implements Comparable<Problem> {
    int K, T;
    long healing;

    Problem(int K, int T) {
        this.K = K;
        this.T = T;
        this.healing = Math.min(K * T, 5 * K);
    }

    @Override
    public int compareTo(Problem o) {
        return Long.compare(o.healing, this.healing);
    }
}