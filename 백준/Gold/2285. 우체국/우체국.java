import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Village> villages = new ArrayList<>();
        long cnt = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long X = Integer.parseInt(st.nextToken());
            long A = Integer.parseInt(st.nextToken());

            villages.add(new Village(X, A));
            cnt += A;
        }
        Collections.sort(villages);

        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum += villages.get(i).a;
            if((double)sum >= (double)cnt / 2) {
                System.out.println(villages.get(i).x);
                return;
            }
        }
        System.out.println("ERROR");
    }
}

class Village implements Comparable<Village> {
    long x, a;

    Village(long x, long a) {
        this.x = x;
        this.a = a;
    }

    @Override
    public int compareTo(Village v) {
        return Long.compare(this.x, v.x);
    }
}