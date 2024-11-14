import java.io.*;
import java.util.*;

public class Main {
    static int[][] gears = new int[4][8];
    static int[] direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 4; i++) {
            String gear = br.readLine();
            for(int j = 0; j < 8; j++)
                gears[i][j] = gear.charAt(j) - '0';
        }
        
        int K = Integer.parseInt(br.readLine());
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            
            direction = new int[4];
            direction[idx] = dir;
            checkDir(idx);
            gearTurn();
        }

        int res = 0;
        for(int i = 0; i < 4; i++) {
            if(gears[i][0] == 1)
                res += Math.pow(2, i);
        }
        System.out.println(res);
    }

    static void checkDir(int idx) {
        for(int i = idx - 1; i >= 0; i--) {
            if(gears[i][2] != gears[i + 1][6])
                direction[i] = -direction[i + 1];
            else
                break;
        }
        for(int i = idx + 1; i < 4; i++) {
            if(gears[i][6] != gears[i - 1][2])
                direction[i] = -direction[i - 1];
            else
                break;
        }
    }

    static void gearTurn() {
        int tmp = 0;
        for(int i = 0; i < 4; i++) {
            if(direction[i] == 1) {
                tmp = gears[i][7];
                for(int j = 7; j > 0; j--)
                    gears[i][j] = gears[i][j - 1];
                gears[i][0] = tmp;
            } else if(direction[i] == -1) {
                tmp = gears[i][0];
                for(int j = 0; j < 7; j++)
                    gears[i][j] = gears[i][j + 1];
                gears[i][7] = tmp;
            }
        }
    }
}