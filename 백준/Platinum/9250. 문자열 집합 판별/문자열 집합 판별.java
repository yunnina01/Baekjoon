import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Trie trie = new Trie();

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            String S = br.readLine();
            trie.addWord(S);
        }
        trie.getFail();

        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            String str = br.readLine();
            sb.append((trie.ahoCorasick(str) ? "YES" : "NO") + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

class Node {
    Node[] child;
    Node fail;
    char value;
    boolean isEnd;

    Node(char value) {
        this.child = new Node[26];
        this.value = value;
        this.isEnd = false;
    }
}

class Trie {
    Node root = new Node(' ');

    Trie() {
        root.fail = root;
    }

    void addWord(String word) {
        Node now = root;
        for(char c : word.toCharArray()) {
            if(now.child[c - 'a'] != null)
                now = now.child[c - 'a'];
            else {
                Node next = new Node(c);
                now.child[c - 'a'] = next;
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
                    while(prev != root && prev.child[next.value - 'a'] == null)
                        prev = prev.fail;
                    next.fail = prev = prev.child[next.value - 'a'] == null ? prev : prev.child[next.value - 'a'];
                }
                next.isEnd |= prev.isEnd;
                deque.offerLast(next);
            }
        }
    }

    boolean ahoCorasick(String keyword) {
        Node now = root;
        for(char c : keyword.toCharArray()) {
            while(now != root && now.child[c - 'a'] == null)
                now = now.fail;
            now = now.child[c - 'a'] == null ? now : now.child[c - 'a'];
            if(now.isEnd)
                return true;
        }
        return false;
    }
}