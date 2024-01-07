import java.io.*;
import java.util.*;

public class Main {
    static List<Point> pList;
    static List<Y> yList;
    static int[] cntTree;
    static long[] sumTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        pList = new ArrayList<>();
        yList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int a = i * 2, b = i * 2 + 1;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            pList.add(new Point(a, x1));
            pList.add(new Point(b, x2));
            yList.add(new Y(a, y1));
            yList.add(new Y(b, y2));
        }

        yList.sort((y1, y2) -> y1.val - y2.val);
        for(int i = 0; i < yList.size(); i++) {
            Y y = yList.get(i);
            int idx = y.idx / 2;
            int a = idx * 2, b = idx * 2 + 1;
            if(y.idx % 2 == 0) {
                pList.get(a).y1 = i;
                pList.get(b).y1 = i;
            } else {
                pList.get(a).y2 = i;
                pList.get(b).y2 = i;
            }
        }

        cntTree = new int[N * 8];
        sumTree = new long[N * 8];
        long res = 0;
        pList.sort((p1, p2) -> p1.val - p2.val);
        for(int i = 0; i < pList.size(); i++) {
            if(i > 0)
                res += sumTree[1] * (pList.get(i).val - pList.get(i - 1).val);

            if(pList.get(i).idx % 2 == 0)
                update(1, 0, N * 2 - 1, pList.get(i).y1, pList.get(i).y2 - 1, 1);
            else
                update(1, 0, N * 2 - 1, pList.get(i).y1, pList.get(i).y2 - 1, -1);
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void update(int node, int start, int end, int left, int right, int val) {
        int mid = (start + end) >> 1;
        if(start >= left && end <= right)
            cntTree[node] += val;
        else {
            if (left <= mid)
                update(node * 2, start, mid, left, right, val);
            if (mid < right)
                update(node * 2 + 1, mid + 1, end, left, right, val);
        }

        if(cntTree[node] > 0)
            sumTree[node] = yList.get(end + 1).val - yList.get(start).val;
        else {
            if(start == end)
                sumTree[node] = 0;
            else
                sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
        }
    }
}

class Point {
    int y1, y2, idx, val;

    Point(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}
   
class Y {
    int idx, val;

    Y(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}