import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<String> pq = new PriorityQueue<String>();
        for(int i = 0; i < N; i++)
            pq.offer(br.readLine() + "[");

        while(!pq.isEmpty()) {
            tmp.setLength(0);
            tmp.append(pq.poll());
            sb.append(tmp.charAt(0));
            if(tmp.length() == 2)
                continue;
            tmp.deleteCharAt(0);
            pq.add(tmp.toString());
        }
        bw.write(sb.toString());
        bw.flush();
    }
}