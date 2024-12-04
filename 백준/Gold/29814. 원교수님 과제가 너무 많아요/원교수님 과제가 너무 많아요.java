import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Homework[] homeworks = new Homework[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            homeworks[i] = new Homework(d - t + 1, p);
        }
        Arrays.sort(homeworks, new Comparator<Homework>() {
            @Override
            public int compare(Homework o1, Homework o2) {
                return o2.d - o1.d;
            }
        });

        PriorityQueue<Integer> points = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> pointsSelected = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int cnt = 0;
        for(int i = N; i >= 1; i--) {
            while(cnt < N && homeworks[cnt].d == i) {
                points.offer(homeworks[cnt].p);
                cnt++;
            }

            if(!points.isEmpty())
                pointsSelected.offer(points.poll());
        }

        int sum = 0;
        int res = 0;
        while(!pointsSelected.isEmpty() && sum < C) {
            sum += pointsSelected.poll();
            res++;
        }

        if(sum < C)
            System.out.println("I'm sorry professor Won!");
        else
            System.out.println(res);
    }
}

class Homework {
    int d, p;

    Homework(int d, int p) {
        this.d = d;
        this.p = p;
    }
}