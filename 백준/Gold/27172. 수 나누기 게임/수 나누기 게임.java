import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        final int SIZE = 1000001;

        br.readLine();
        int[] players = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[] cards = new boolean[SIZE];
        for(int p : players)
            cards[p] = true;

        int[] score = new int[SIZE];
        for(int p : players) {
            for(int i = 2 * p; i < SIZE; i += p) {
                if(cards[i]) {
                    score[p]++;
                    score[i]--;
                }
            }
        }

        for(int p : players)
            sb.append(score[p] + " ");
        bw.write(sb + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}