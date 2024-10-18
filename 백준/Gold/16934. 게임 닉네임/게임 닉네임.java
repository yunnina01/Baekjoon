import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        Map<Character, Node> child = new TreeMap<>();
        int isEndOfWord = 0;
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        String insert(String str) {
            Node now = this.root;
            String ret = "";
            boolean flag = false;
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(!now.child.containsKey(c)) {
                    now.child.put(c, new Node());
                    if(i == 0)
                        ret += c;
                    if(flag) {
                        ret += c;
                        flag =  false;
                    }
                } else {
                    flag = true;
                    ret += c;
                }
                now = now.child.get(c);
            }
            if(now.isEndOfWord > 0)
                ret += String.valueOf(now.isEndOfWord + 1);
            now.isEndOfWord++;
            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for(int i = 0; i < N; i++) {
            String res = trie.insert(br.readLine());
            sb.append(res + "\n");
        }
        System.out.print(sb);
    }
}