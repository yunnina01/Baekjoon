import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> command;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        command = new ArrayList<>();
        while(true) {
            int n = Integer.parseInt(st.nextToken());
            if(n == 0)
                break;
            command.add(n);
        }

        dp = new int[5][5][command.size() + 1];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++)
                Arrays.fill(dp[i][j], -1);
        }

        bw.write(DFS(0, 0, 0) + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static int DFS(int left, int right, int idx) {
        if(idx == command.size())
            return 0;
        if(dp[left][right][idx] != -1)
            return dp[left][right][idx];

        int moveLeft = DFS(command.get(idx), right, idx + 1) + calPower(left, command.get(idx));
        int moveRight = DFS(left, command.get(idx), idx + 1) + calPower(right, command.get(idx));
        return dp[left][right][idx] = Math.min(moveLeft, moveRight);
    }

    static int calPower(int pre, int next) {
        if(pre == next)
            return 1;
        if(pre == 0)
            return 2;
        if(Math.abs(pre - next) == 2)
            return 4;
        return 3;
    }
}