import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();
        char[] wordArr = word.toCharArray();

        Map<String, Integer> map = new HashMap<>();
        map.put(word, 1);
        int cnt = 1;
        while(true) {
            cnt += 1;
            wordArr = operation(wordArr);

            String res = String.valueOf(wordArr);
            if(cnt == X + 1) {
                System.out.println(res);
                return;
            }

            if(map.containsKey(res))
                break;
            map.put(res, cnt);
        }

        X -= (map.get(String.valueOf(wordArr)) - 1);
        X %= (cnt - map.get(String.valueOf(wordArr)));

        for(int i = 0; i < X; i++)
            wordArr = operation(wordArr);
        System.out.println(String.valueOf(wordArr));
    }

    static char[] operation(char[] seq) {
        char[] newSeq = new char[seq.length];
        int idx = 0;
        for(int i = 0; i < seq.length; i += 2)
            newSeq[idx++] = seq[i];

        int back = seq.length % 2 == 0 ? seq.length - 1 : seq.length - 2;
        for(int i = 1; i < seq.length; i += 2, back -= 2)
            newSeq[idx++] = seq[back];
        return newSeq;
    }
}