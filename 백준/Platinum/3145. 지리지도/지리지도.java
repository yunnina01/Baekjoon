import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static List<List<Integer>> edges;
    static int[] aArr, bArr;
    static boolean[] visit;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        List<String> nameList = new ArrayList<>();
        int[][] map = new int[R][C];
        nameList.add("");
        int wordCnt = 0;
        int landCnt = 0;
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                if(Character.isUpperCase(line.charAt(j))) {
                    wordCnt++;
                    String nowStr = "";
                    while(j < C && Character.isUpperCase(line.charAt(j))) {
                        nowStr += line.charAt(j);
                        map[i][j] = wordCnt;
                        j++;
                    }
                    nameList.add(nowStr);
                }
                if(j >= C)
                    break;

                if(line.charAt(j) == 'x')
                    map[i][j] = 10000 + landCnt++;
            }
        }

        edges = new ArrayList<>();
        for(int i = 0; i < landCnt; i++)
            edges.add(new ArrayList<>());

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] >= 10000){
                    for(int[] dir: DIRECTIONS) {
                        int ny = i + dir[0];
                        int nx = j + dir[1];
                        if(!isOuter(ny, nx) && map[ny][nx] != 0 && map[ny][nx] < 10000 && !edges.get(map[i][j] - 10000).contains(map[ny][nx]))
                            edges.get(map[i][j] - 10000).add(map[ny][nx]);
                    }
                }
            }
        }

        aArr = new int[landCnt];
        bArr = new int[wordCnt + 1];
        Arrays.fill(aArr, -1);
        Arrays.fill(bArr, - 1);
        for(int i = 0; i < landCnt; i++) {
            if(aArr[i] == -1) {
                visit = new boolean[landCnt];
                DFS(i);
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] >= 10000) {
                    int nowLandIdx = map[i][j] - 10000;
                    sb.append((i + 1) + " " + (j + 1) + " " + nameList.get(aArr[nowLandIdx]) + "\n");
                }
            }
        }
        System.out.print(sb);
    }

    static boolean isOuter(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }

    static boolean DFS(int now) {
        visit[now] = true;
        for(int next : edges.get(now)) {
            if(bArr[next] == -1 || !visit[bArr[next]] && DFS(bArr[next])) {
                aArr[now] = next;
                bArr[next] = now;
                return true;
            }
        }
        return false;
    }
}