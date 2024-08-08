import java.io.*;
import java.util.*;

public class Main {
    static List<Deque<Integer>> ballots;
    static String[] candidates;
    static int[] voteCount;
    static boolean[] eliminated;
    static int totalVotes, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        candidates = new String[n];
        for(int i = 0; i < n; i++)
            candidates[i] = br.readLine();
        
        ballots = new ArrayList<>();
        voteCount = new int[n];
        String line;
        while((line = br.readLine()) != null && !line.trim().isEmpty()) {
            Deque<Integer> ballot = new ArrayDeque<>();
            st = new StringTokenizer(line);
            while(st.hasMoreTokens())
                ballot.add(Integer.parseInt(st.nextToken()) - 1);

            ballots.add(ballot);
            voteCount[ballot.peek()]++;
            totalVotes++;
        }

        eliminated = new boolean[n];
        while(true) {
            int maxVotes = 0;
            int minVotes = totalVotes;
            for(int i = 0; i < n; i++) {
                if(!eliminated[i]) {
                    if(voteCount[i] > maxVotes)
                        maxVotes = voteCount[i];
                    if(voteCount[i] < minVotes)
                        minVotes = voteCount[i];
                }
            }

            if(maxVotes > totalVotes / 2) {
                for(int i = 0; i < n; i++) {
                    if(voteCount[i] == maxVotes) {
                        System.out.println(candidates[i]);
                        return;
                    }
                }
            }

            if(maxVotes == minVotes) {
                for(int i = 0; i < n; i++) {
                    if(!eliminated[i])
                        System.out.println(candidates[i]);
                }
                return;
            }

            for(int i = 0; i < n; i++) {
                if(voteCount[i] == minVotes)
                    eliminated[i] = true;
            }

            Arrays.fill(voteCount, 0);
            for(Deque<Integer> ballot : ballots) {
                while(!ballot.isEmpty() && eliminated[ballot.peek()])
                    ballot.poll();
                if(!ballot.isEmpty())
                    voteCount[ballot.peek()]++;
            }
        }
    }
}