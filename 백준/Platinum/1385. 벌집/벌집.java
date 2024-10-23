import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] ac = getCoord(a);
        int[] bc = getCoord(b);
        while(getCell(ac) != b) {
        	sb.append(getCell(ac) + " ");

        	int dir = getDir(ac, bc);
        	ac[0] += DIRECTIONS[dir][0];
        	ac[1] += DIRECTIONS[dir][1];
        }
        sb.append(b);
        bw.write(sb.toString());
        bw.flush();
    }

    static int[] getCoord(int cell) {
    	int radius = getRadius(cell);
    	int[] coord = {-1 * radius, 0};
    	if(radius == 0)
    		return coord;

    	int idx = cell - (3 * radius * radius - 3 * radius + 1);
        int div = idx / radius;
        int mod = idx % radius;
    	if(idx == 6 * radius)
    		return coord;

    	for(int i = 0; i < div; i++) {
    		coord[0] += DIRECTIONS[i][0] * radius;
    		coord[1] += DIRECTIONS[i][1] * radius;
    	}
    	coord[0] += DIRECTIONS[div][0] * mod;
    	coord[1] += DIRECTIONS[div][1] * mod;
    	return coord;
    }

    static int getRadius(int cell) {
        int r = 0;
    	for(int num = 1; num < cell; r++)
            num += 6 * r;
    	return cell == 1 ? 0 : r - 1;
    }

    static int getCell(int[] coord) {
    	int x = coord[0];
        int y = coord[1];
    	if(x == 0 && y == 0)
    		return 1;

    	int radius = coord[0] * coord[1] < 0 ? Math.abs(coord[0] - coord[1]) : Math.max(Math.abs(coord[0]), Math.abs(coord[1]));
    	if(x >= 0 && y >= 0)
    		return 3 * radius * radius - 1 * radius + 1 + x - y;
    	else if(x <= 0 && y <= 0)
    		return 3 * radius * radius + 2 * radius + 1 + y - x;
    	else if(x < 0)
    		return 3 * radius * radius - 2 * radius + 1 + x;
    	else
    		return 3 * radius * radius + radius + 1 - x;
    }

    static int getDir(int[] now, int[] goal) {
    	int ndiag = now[0] + now[1];
        int gdiag = goal[0] + goal[1];
    	if(Math.abs(ndiag - gdiag) > Math.max(Math.abs(now[0] - goal[0]), Math.abs(now[1] - goal[1])))
    		return ndiag > gdiag ? 3 : 0;
    	else if(Math.abs(now[0] - goal[0]) > Math.abs(now[1] - goal[1]))
    		return now[0] > goal[0] ? 4 : 1;
    	else
    		return now[1] > goal[1] ? 2 : 5;
    }
}