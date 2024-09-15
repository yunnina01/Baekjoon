import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
    static Trie root = new Trie();
    static char[][] map;
    static Set<String> res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int w = Integer.parseInt(br.readLine());

        while(w-- > 0) {
            String word = br.readLine();
            insert(word);
        }

        br.readLine();

        int b = Integer.parseInt(br.readLine());

        while(b-- > 0) {
            map = new char[4][4];
            for(int i = 0; i < 4; i++) {
                String boggle = br.readLine();
                for(int j = 0; j < 4; j++)
                    map[i][j] = boggle.charAt(j);
            }

            br.readLine();

            res = new HashSet<>();
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++)
                    DFS(i, j, "", new boolean[4][4]);
            }

            int maxScore = 0;
            String maxWord = "";
            int cnt = res.size();
            for(String s : res) {
                if(maxWord.equals(""))
                    maxWord = s;
                else
                    maxWord = getString(maxWord, s);
                maxScore += getScore(s.length());
            }
            System.out.print(maxScore + " " + maxWord + " " + cnt + "\n");
        }
    }

    static void insert(String str) {
        Trie trie = root;
        for(int i = 0; i < str.length(); i++) {
            int target = str.charAt(i) - 'A';
            if(trie.children[target] == null)
                trie.children[target] = new Trie();
            trie = trie.children[target];
        }
        trie.isLast = true;
    }

    static void DFS(int x, int y, String str, boolean[][] visit) {
        String now = str + map[x][y];
        int isWord = available(now);
        visit[x][y] = true;

        if(isWord == 2) {
            res.add(now);
            if(now.length() > 8)
                return;
        }

        if(isWord > 0) {
            for(int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !visit[nx][ny])
                    DFS(nx, ny, now, visit);
            }
        }
        visit[x][y] = false;
    }

    static int available(String str) {
        Trie trie = root;
        for(int i = 0; i < str.length(); i++) {
            int target = str.charAt(i) - 'A';
            if(trie.children[target] == null)
                return 0;
            trie = trie.children[target];
        }

        if(trie.isLast && !trie.isHit)
            return 2;
        else if(trie.isLast && trie.isHit)
            return 0;
        return 1;
    }

    static String getString(String s1, String s2) {
        if(s1.length() < s2.length())
            return s2;
        else if(s1.length() > s2.length())
            return s1;
        else {
            if(s1.compareTo(s2) <= 0)
                return s1;
            return s2;
        }
    }

    static int getScore(int n) {
        switch(n) {
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            case 8:
                return 11;
            default:
                return 0;
        }
    }
}

class Trie {
    Trie[] children;
    boolean isLast, isHit;

    Trie() {
        this.children = new Trie[26];
    }
}