import java.io.*;
import java.util.*;

public class Main {
    static List<Swap> swapList;
    static Set<Long> visit;
    static int[] A;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        swapList = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            swapList.add(new Swap(l - 1, r - 1, c));
        }

        visit = new HashSet<>();
        System.out.println(dijkstra(A));
    }

    static int dijkstra(int[] arr) {
        Queue<Pair> queue = new PriorityQueue<>();
        queue.offer(new Pair(arr, 0));
        while(!queue.isEmpty()) {
            Pair pair;
            long key;
            do {
                pair = queue.poll();
                key = convertToLong(pair.arr);
            } while(!queue.isEmpty() && visit.contains(key));
            
            if(visit.contains(key))
                break;

            visit.add(key);
            if(isDescendingOrder(pair.arr))
                return pair.cost;

            for(int i = 0; i < M; i++) {
                Swap swap = swapList.get(i);
                int from = swap.l, to = swap.r;
                int[] swapArr = swapArr(pair.arr, from, to);
                queue.offer(new Pair(swapArr, pair.cost + swap.c));
            }
        }
        return -1;
    }

    static long convertToLong(int[] arr) {
        long num = 0;
        for(int i : arr) {
            num *= 10;
            num += i;
        }
        return num;
    }

    static boolean isDescendingOrder(int[] arr) {
        int prev = 1;
        for(int i = 0; i < N; i++) {
            if(arr[i] < prev)
                return false;
            prev = arr[i];
        }
        return true;
    }

    static int[] swapArr(int[] arr, int l, int r) {
        int[] newArr = new int[N];
        System.arraycopy(arr, 0, newArr, 0, N);

        int temp = newArr[l];
        newArr[l] = newArr[r];
        newArr[r] = temp;
        return newArr;
    }
}

class Swap {
    int l, r, c;

    Swap(int l, int r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }
}

class Pair implements Comparable<Pair> {
    int[] arr;
    int cost;

    Pair(int[] arr, int cost) {
        this.arr = arr;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
        return this.cost - o.cost;
    }
}