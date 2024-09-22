import java.io.*;

public class Main {
    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            sb = new StringBuilder();
            DFS(1, 1, 1, 0, "1");
            bw.write(sb + "\n");
        }
        bw.flush();
    }

    static void DFS(int cnt, int num, int op, int sum, String express) {
        if(cnt == N) {
            sum += num * op;
            if(sum == 0)
                sb.append(express + "\n");
            return;
        }
        DFS(cnt + 1, num * 10 + (cnt + 1), op, sum, express + " " + (cnt + 1));
        DFS(cnt + 1, cnt + 1, 1, sum + num * op, express + "+" + (cnt + 1));
        DFS(cnt + 1, cnt + 1, -1, sum + num * op, express + "-" + (cnt + 1));
    }
}