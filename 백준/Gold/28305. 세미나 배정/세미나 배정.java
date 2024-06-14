import java.io.*;
import java.util.*;

public class Main {
    static int[] seminars;
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        T = Integer.parseInt(st.nextToken());

        seminars = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int start = 1;
        int end = 200000;
        while(start < end) {
            int mid = (start + end) >> 1;
            if(possible(mid))
                end = mid;
            else
                start = mid + 1;
        }
        System.out.println(start);
    }

    static boolean possible(int room) {
        Queue<Integer> endDays = new LinkedList<>();
        for(int i = 0; i < Math.min(room, seminars.length); i++)
            endDays.offer(Math.max(T, seminars[i]));

        for(int i = room; i < seminars.length; i++) {
            while(!endDays.isEmpty() && endDays.peek() < seminars[i] - T + 1)
                endDays.poll();

            if(endDays.size() < room)
                endDays.offer(seminars[i]);
            else {
                while(endDays.size() > room)
                    endDays.poll();
                
                if(endDays.peek() >= seminars[i])
                    return false;
                endDays.offer(endDays.poll() + T);
            }
        }
        return true;
    }
}