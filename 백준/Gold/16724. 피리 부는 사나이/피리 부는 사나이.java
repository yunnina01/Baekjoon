import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;
    static int[] parent;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];
		for(int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < M; j++)
				map[i][j] = line[j];
		}

        parent = new int[N * M];
        Arrays.fill(parent, -1);

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int now = i * M + j;
				int next = getArrowIdx(now, map[i][j]);
                union(now, next);
			}
		}
        
        int res = 0;
        for(int p : parent) {
            if(p < 0)
                res++;
        }
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}

	static int getArrowIdx(int idx, char dir) {
		switch(dir) {
            case 'L':
                return idx - 1;
            case 'D':
                return idx + M;
            case 'U':
                return idx - M;
            case 'R':
                return idx + 1;
            default:
                return -1;
        }
	}

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[a] += parent[b];
            parent[b] = a;
        }
    }

    static int find(int x) {
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }
}