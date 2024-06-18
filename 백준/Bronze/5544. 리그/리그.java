import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] teams = new int[N + 1];
        for(int i = 0; i < N * (N - 1) / 2; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            if(C > D)
                teams[A] += 3;
            else if(C < D)
                teams[B] += 3;
            else {
                teams[A] += 1;
                teams[B] += 1;
            }
        }

        int[] score = new int[N + 1];
        for(int i = 1; i <= N; i++){
            int max = 0;
            int idx = 0;
            for(int j = 1; j <= N; j++) {
                if(score[j] == 0 && teams[j] > max) {
                    max = teams[j];
                    idx = j;
                }
            }
            score[idx] = i;

            for(int j = idx + 1; j <= N; j++) {
                if(teams[j] == teams[idx]) {
                    score[j] = score[idx];
                    i++;
                }
            }
        }
        for(int i = 1; i <= N; i++)
            System.out.println(score[i]);
    }
}