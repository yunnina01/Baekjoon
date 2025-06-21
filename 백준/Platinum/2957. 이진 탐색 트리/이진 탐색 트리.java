import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            int low = map.lowerKey(num) == null ? -1 : map.get(map.lowerKey(num));
            int high = map.higherKey(num) == null ? -1 : map.get(map.higherKey(num));
            int dep = Math.max(low, high) + 1;
            map.put(num, dep);
            sum += dep;
            sb.append(sum + "\n");
        }
        System.out.print(sb);
    }
}