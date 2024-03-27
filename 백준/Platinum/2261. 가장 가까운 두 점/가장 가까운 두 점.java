import java.io.*;
import java.util.*;

public class Main {
    static List<Point> pointList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        pointList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pointList.add(new Point(x, y));
        }

        pointList.sort((o1, o2) -> o1.x - o2.x);

        bw.write(getMinDistance(0, n - 1) + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static int getMinDistance(int start, int end) {
        if(end - start < 3) {
            int ret = Integer.MAX_VALUE;
            for(int i = start; i < end; i++) {
                for(int j = i + 1; j <= end; j++)
                    ret = Math.min(ret, getDist(pointList.get(i), pointList.get(j)));
            }
            return ret;
        }

        int mid = (start + end) >> 1;
        int leftMin = getMinDistance(start, mid);
        int rightMin = getMinDistance(mid + 1, end);

        int minDist = Math.min(leftMin, rightMin);
        int band = middleRange(start, mid, end, minDist);
        return Math.min(minDist, band);
    }

    static int getDist(Point p1, Point p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    static int middleRange(int start, int mid, int end, int minDist) {
        List<Point> list = new ArrayList<>();
        int midX = pointList.get(mid).x;
        
        for(int i = start; i <= end; i++) {
            int xDist = pointList.get(i).x - midX;
            if (xDist * xDist < minDist)
                list.add(pointList.get(i));
        }
        list.sort((o1, o2) -> o1.y - o2.y);

        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = i + 1; j < list.size(); j++) {
                int yDist = list.get(i).y - list.get(j).y;
                if(yDist * yDist < minDist)
                    minDist = Math.min(minDist, getDist(list.get(i), list.get(j)));
                else
                    break;
            }
        }
        return minDist;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}