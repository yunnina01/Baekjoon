import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        long[] minSums = new long[N + 1];
        long[] maxSums = new long[N + 1];
        for(int i = 0; i < N; i++) {
            minSums[i + 1] = minSums[i] + costs[i];
            maxSums[i + 1] = maxSums[i] + costs[N - 1 - i];
        }

        int[][] records = new int[M][];
        for(int i = 0; i < records.length; i++)
            records[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(records, Comparator.comparingInt(o -> o[0]));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int calcedDay = 0;
        long min = 0;
        long max = 0;
        for(int[] record : records) {
            while(!pq.isEmpty() && pq.peek() < record[0]) {
                min += minSums[pq.size()] * (pq.peek() - calcedDay);
                max += maxSums[pq.size()] * (pq.peek() - calcedDay);
                calcedDay = pq.poll();

                while(!pq.isEmpty() && pq.peek() == calcedDay)
                    pq.poll();
            }

            min += minSums[pq.size()] * (record[0] - 1 - calcedDay);
            max += maxSums[pq.size()] * (record[0] - 1 - calcedDay);
            calcedDay = record[0] - 1;
            pq.offer(record[1]);
        }

        while(!pq.isEmpty()) {
            min += minSums[pq.size()] * (pq.peek() - calcedDay);
            max += maxSums[pq.size()] * (pq.peek() - calcedDay);
            calcedDay = pq.poll();
        }
        System.out.println(min + " " + max);
    }
}