import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node[] tree = new Node[N + 1];
        for(int i = 1; i <= N; i++)
            tree[i] = new Node();

        st = new StringTokenizer(br.readLine());
        for(int i = 2; i <= N; i++)
            tree[Integer.parseInt(st.nextToken())].child.add(tree[i]);

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            tree[i].score = Integer.parseInt(st.nextToken());

        tree[1].dp();
        System.out.println(Math.max(tree[1].on, tree[1].off));
    }
}

class Node {
    List<Node> child;
    int score, on, off;
    
    Node() {
        child = new ArrayList<>();
        score = on = off = 0;
    }

    void dp() {
        for(Node node : child) {
            node.dp();
            off += Math.max(node.on, node.off);
        }
        for(Node node : child) {
            on = Math.max(on, off - Math.max(node.on, node.off) + node.off + score * node.score);
        }
    }
}