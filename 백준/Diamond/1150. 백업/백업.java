import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Company[] companies = new Company[n + 2];
        PriorityQueue<Company> pq = new PriorityQueue<>();
        companies[0] = new Company(0, 0, 0);
        companies[n] = new Company(n - 1, n + 1, 0);
        companies[n + 1] = new Company(n, n + 1, 0);

        int s = Integer.parseInt(br.readLine());
        for(int i = 1; i < n; i++) {
            companies[i] = new Company(i - 1, i + 1, s);
            s = Integer.parseInt(br.readLine());
            companies[i].dist = s - companies[i].dist;
            pq.offer(new Company(i, i + 1, companies[i].dist));
        }

        long res = 0L;
        for(int i = 0;;) {
            Company now = pq.poll();
            int l = now.left;
            int r = now.right;
            if(l < 1 || r > n || companies[l].right != r || companies[r].left != l)
                continue;
            res += now.dist;
            if(++i == k)
                break;

            int ll = companies[l].left;
            int rr = companies[r].right;
            now.left = ll;
            now.right = rr;
            now.dist = companies[ll].dist = companies[ll].dist + companies[r].dist - now.dist;
            pq.offer(now);

            companies[ll].right = rr;
            companies[rr].left = ll;
        }
        System.out.println(res);
    }
}

class Company implements Comparable<Company> {
    int left, right, dist;

    Company(int left, int right, int dist) {
        this.left = left;
        this.right = right;
        this.dist = dist;
    }

    @Override
    public int compareTo(Company o) {
        return this.dist - o.dist;
    }
}