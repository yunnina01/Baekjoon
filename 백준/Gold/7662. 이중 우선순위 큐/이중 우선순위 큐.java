import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> heap = new TreeMap<>();

            while(k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                long n = Long.parseLong(st.nextToken());

                if(op == 'D') {
                    if(heap.isEmpty())
                        continue;

                    if(n < 0) {
                        long key = heap.firstKey();
                        if(heap.get(key) == 1)
                            heap.pollFirstEntry();
                        else
                            heap.put(key, heap.get(key) - 1);
                    } else {
                        long key = heap.lastKey();
                        if(heap.get(key) == 1)
                            heap.pollLastEntry();
                        else
                            heap.put(key, heap.get(key) - 1);
                    }
                } else {
                    if(!heap.containsKey(n))
                        heap.put(n, 1);
                    else
                        heap.put(n, heap.get(n) + 1);
                }
            }

            if(heap.isEmpty())
                sb.append("EMPTY\n");
            else
                sb.append(heap.lastKey() + " " + heap.firstKey() + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}