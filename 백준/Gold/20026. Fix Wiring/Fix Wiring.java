import java.io.*;
import java.util.*;

public class Main {
    static long[] tags;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int size = (N * (N - 1)) >> 1;
        tags = new long[size];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++)
            tags[i] = Long.parseLong(st.nextToken());
        System.out.println(MST(N));
    }

    static String MST(int N) {
        Arrays.sort(tags);

        long min = 0, max = 0;
        int index = 0;
        for(int i = 0; i < N - 1; i++) {
            min += tags[i];
            max += tags[index];
            index += i + 1;
        }
        return min + " " + max;
    }
}