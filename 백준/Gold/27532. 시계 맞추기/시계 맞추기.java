import java.io.*;

public class Main {
    static int[] times;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        times = new int[M];
        for(int i = 0; i < M; i++) {
            String str = br.readLine();
            times[i] = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
        }
        
        int min =  730;
        for(int i = 1; i <= 720; i++) {
            int cnt = FindTime(i);
            min = Math.min(min, cnt);
        }
        System.out.println(min);
    }

    static int FindTime(int time) {
        boolean[] clock = new boolean[720];
        int cnt = 0;
        for(int i = 0; i < M; i++) {
            int now = ((times[i] - i * time) % 720 + 720) % 720;
            if(!clock[now]) {
                clock[now] = true;
                cnt++;
            }
        }
        if(cnt >= 720)
            return 720;
        return cnt;
    }
}