import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Meat> meats = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meats.add(new Meat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(meats);

        int prevCost = -1;
        int costSum = 0;
        int weightSum = 0;
        long res = Long.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            Meat now = meats.get(i);
            if(prevCost != now.cost) {
                costSum = now.cost;
                prevCost = now.cost;
            } else
                costSum += now.cost;

            weightSum += now.weight;
            if(weightSum >= M)
                res = Math.min(res, costSum);
        }
        System.out.println(res == Long.MAX_VALUE ? -1 : res);
    }
}

class Meat implements Comparable<Meat> {
    int cost, weight;

    Meat(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public int compareTo(Meat o) {
        if(this.cost == o.cost)
            return o.weight - this.weight;
        return this.cost - o.cost;
    }
}