import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 2500;
    static int[] len;
    
    static class Node {
        Node[] child;
        char value;
        Node fail;
        int isEnd;

        Node(char value) {
            this.child = new Node[26];
            this.value = value;
            this.isEnd = 0;
        }
    }

    static class Trie {
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
                if(now.child[c - 'a'] != null)
                    now = now.child[c - 'a'];
                else {
                    Node next = new Node(c);
                    now.child[c - 'a'] = next;
                    now = next;
                }
            }
            now.isEnd = Math.max(now.isEnd, word.length());
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
                    next.isEnd = Math.max(next.isEnd, prev.isEnd);
                    deque.offerLast(next);
                }
            }
        }

        void ahoCorasick(String keyword) {
            Node now = root;
            for(int i = 0; i < keyword.length(); i++) {
                char c = keyword.charAt(i);
                while(now != root && now.child[c - 'a'] == null)
                    now = now.fail;
                now = now.child[c - 'a'] == null ? now : now.child[c - 'a'];
                len[i] = Math.max(len[i], now.isEnd);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String road = br.readLine();
        int M = Integer.parseInt(br.readLine());

        len = new int[N];
        for(int idx = 0; idx < M;) {
            Trie trie = new Trie();
            for(int i = 0; i < SIZE && idx < M; i++, idx++) {
                String tile = br.readLine();
                trie.addWord(tile);
            }
            trie.getFail();
            trie.ahoCorasick(road);
        }

        int res = 0;
        for(int i = N - 1; i > 0; i--) {
            if(len[i] == 0)
                res++;
            len[i - 1] = Math.max(len[i - 1], len[i] - 1);
        }
        if(len[0] == 0)
            res++;
        System.out.println(res);
    }
}