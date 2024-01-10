import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<int[]> deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());

            if(!deque.isEmpty() && deque.peekFirst()[1] + L == i)
                deque.pollFirst();
            while(!deque.isEmpty() && deque.peekLast()[0] > a)
                deque.pollLast();
            deque.offerLast(new int[]{a, i});

            bw.write(deque.peekFirst()[0] + " ");
        }
        bw.newLine();
        br.close();
        bw.flush();
        bw.close();
    }
}