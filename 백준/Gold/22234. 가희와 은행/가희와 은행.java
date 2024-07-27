import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Client> pq = new PriorityQueue<>(Comparator.comparingDouble(value -> value.inputTime));
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.offer(new Client(P, t, Integer.MIN_VALUE + i));
        }

        int M = Integer.parseInt(br.readLine());
    
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Client(P, t, c));
        }
        
        int time = 0;
        while(!pq.isEmpty() && time < W) {
            Client now = pq.poll();
            int process = Math.min(Math.min(now.work, T), W - time);
            for(int i = 0; i < process; i++)
                sb.append(now.num + "\n");
            
            if(now.work - process > 0) {
                now.work -= process;
                now.inputTime = time + process + 0.5;
                pq.offer(now);
            }
            time += process;
        }
        System.out.print(sb);
    }
}

class Client {
    int num, work;
    double inputTime;

    Client(int num, int work, double inputTime) {
        this.num = num;
        this.work = work;
        this.inputTime = inputTime;
    }
}