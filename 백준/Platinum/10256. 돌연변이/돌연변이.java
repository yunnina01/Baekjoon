import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            Trie trie = new Trie();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            String dnaStructure = br.readLine();

            StringBuilder marker = new StringBuilder(br.readLine());
            StringBuilder reverse = new StringBuilder(marker);
            reverse.reverse();

            for(int s = 0; s <= m; s++) {
                for(int e = s; e <= m; e++) {
                    StringBuilder mutate = new StringBuilder(marker);
                    mutate.replace(s, e, reverse.substring(m - e, m - s));
                    trie.addWord(mutate.toString());
                }
            }

            trie.getFail();
            sb.append(trie.ahoCorasick(dnaStructure) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}

class Node {
    Node[] child;
    char value;
    Node fail;
    boolean isEnd;

    Node(char value) {
        this.child = new Node[4];
        this.value = value;
        this.isEnd = false;
    }
}

class Trie {
    HashSet<String> dup = new HashSet<>();
    Node root = new Node(' ');

    Trie() {
        root.fail = root;
    }

    void addWord(String word) {
        if(dup.contains(word))
            return;
        dup.add(word);
        Node now = root;
        for(char c : word.toCharArray()) {
            if(now.child[toNum(c)] != null)
                now = now.child[toNum(c)];
            else {
                Node next = new Node(c);
                now.child[toNum(c)] = next;
                now = next;
            }
        }
        now.isEnd = true;
    }

    void getFail() {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);
        while(!deque.isEmpty()) {
            Node now = deque.poll();
            for(Node next : now.child) {
                if(next == null)
                    continue;
                Node prev  = now.fail;
                if(now == root)
                    next.fail = root;
                else {
                    while(prev != root && prev.child[toNum(next.value)] == null)
                        prev = prev.fail;
                    next.fail = prev = prev.child[toNum(next.value)] == null ? prev : prev.child[toNum(next.value)];
                }
                next.isEnd |= prev.isEnd;
                deque.offerLast(next);
            }
        }
    }

    int ahoCorasick(String keyword) {
        Node now = root;
        int ret = 0;
        for(char c : keyword.toCharArray()) {
            while(now != root && now.child[toNum(c)] == null)
                now = now.fail;
            now = now.child[toNum(c)] == null ? now : now.child[toNum(c)];
            if(now.isEnd)
                ret++;
        }
        return ret;
    }

    int toNum(char ch) {
        switch(ch) {
            case 'A':
                return 0;
            case 'T':
                return 1;
            case 'G':
                return 2;
            default:
                return 3;
        }
    }
}