import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        Deque<Balloon> deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            deque.offer(new Balloon(Integer.parseInt(st.nextToken()), i));

        while(deque.size() > 1) {
            Balloon balloon =  deque.pollFirst();
            if(balloon.num > 0) {
                for(int j = 0; j < balloon.num - 1; j++)
                    deque.offerLast(deque.pollFirst());
            } else {
                for(int j = 0; j > balloon.num; j--)
                    deque.offerFirst(deque.pollLast());
            }
            bw.write(balloon.idx + " ");
        }
        bw.write(deque.poll().idx + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}

class Balloon {
    int num, idx;

    Balloon(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }
}