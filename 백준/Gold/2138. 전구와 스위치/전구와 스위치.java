import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] start = new int[N];
        String state = br.readLine();
        for(int i = 0; i < N; i++)
            start[i] = (int)state.charAt(i) - '0';

        int[] end = new int[N];
        state = br.readLine();
        for(int i = 0; i < N; i++)
            end[i] = (int)state.charAt(i) - '0';
        
        int[] swit = new int[N];
        System.arraycopy(start, 0, swit, 0, N);

        swit[0] = convert(swit[0]);
        swit[1] = convert(swit[1]);
        int cnt = 1;
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++) {
            if(swit[i - 1] != end[i - 1]) {
                cnt++;
                swit[i - 1] = convert(swit[i - 1]);
                swit[i] = convert(swit[i]);
                if(i != N - 1)
                    swit[i + 1] = convert(swit[i + 1]);
            }
        }
        if(ch(swit, end, N))
            res = Math.min(res, cnt);

        System.arraycopy(start, 0, swit, 0, N);
        cnt = 0;
        for(int i = 1; i < N; i++) {
            if(swit[i - 1] != end[i - 1]) {
                cnt++;
                swit[i - 1] = convert(swit[i - 1]);
                swit[i] = convert(swit[i]);
                if(i != N - 1)
                    swit[i + 1] = convert(swit[i + 1]);
            }
        }

        if(ch(swit, end, N)) {
            System.out.println(Math.min(res, cnt));
            return;
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    static int convert(int i) {
        return 1 - i;
    }

    static boolean ch(int[] swit, int[] end, int N) {
        for(int i = 0; i < N; i++) {
            if(swit[i] != end[i])
                return false;
        }
        return true;
    }
}
