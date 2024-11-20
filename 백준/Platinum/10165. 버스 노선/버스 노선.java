import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus> busList = new ArrayList<>();
        boolean[] res = new boolean[M + 1];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(s > e)
                busList.add(new Bus(s, e + N, i));
            else {
                busList.add(new Bus(s, e, i));
                busList.add(new Bus(s + N, e + N, i));
            }
        }
        busList.sort(null);

        int left = 0, right = 0;
        for(Bus bus : busList) {
            if(left <= bus.start && bus.end <= right) {
                res[bus.idx] = true;
                continue;
            }
            left = bus.start;
            right = bus.end;
        }

        for(int i = 1; i <= M; i++) {
            if(res[i])
                continue;
            sb.append(i + " ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}

class Bus implements Comparable<Bus> {
    int start, end, idx;

    Bus(int start, int end, int idx) {
        this.start = start;
        this.end = end;
        this.idx = idx;
    }

    @Override
    public int compareTo(Bus o) {
        return start == o.start ? o.end - this.end : this.start - o.start;
    }
}