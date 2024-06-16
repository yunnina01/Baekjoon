import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        List<Node> A = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A.add(new Node(i, Integer.parseInt(st.nextToken())));

        int M = Integer.parseInt(br.readLine());

        List<Node> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++)
            B.add(new Node(i, Integer.parseInt(st.nextToken())));

        Collections.sort(A);
        Collections.sort(B);

        List<Integer> res = new ArrayList<>();
        int idx1 = -1, idx2 = -1;
        int pos1 = 0, pos2 = 0;
        while(pos1 < N && pos2 < M) {
            Node a = A.get(pos1);
            Node b = B.get(pos2);
            if(a.idx < idx1) {
                pos1++;
                continue;
            }
            if(b.idx < idx2) {
                pos2++;
                continue;
            }
            
            if(a.num == b.num) {
                res.add(a.num);
                idx1 = a.idx;
                idx2 = b.idx;
                pos1++;
                pos2++;
            } else if(a.num > b.num)
                pos1++;
            else
                pos2++;
        }

        sb.append(res.size() + "\n");
        for(int i = 0; i < res.size(); i++)
            sb.append(res.get(i) + " ");
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

class Node implements Comparable<Node> {
    int idx, num;

    Node(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }

    @Override
    public int compareTo(Node o) {
        if(o.num == this.num)
            return this.idx - o.idx;
        return o.num - this.num;
    }
}