import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> orders;
    static String[] cards;
    static int[] personalCards, res, commands;
    static TreeSet<String> set = new TreeSet<>();
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        orders = new ArrayList<>();
        orders.add(new ArrayList<>());
        personalCards = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            personalCards[i] = cnt;

            orders.add(new ArrayList<>());
            for(int j = 0; j < cnt; j++)
                orders.get(i).add(Integer.parseInt(st.nextToken()));
        }
        
        cards = new String[C + 1];
        for(int i = 1; i <= C; i++)
            cards[i] = br.readLine();
        res = new int[C + 1];
        commands = new int[C];
        Arrays.fill(res, -1);

        backtracking(1,0);

        for(String s : set)
            sb.append(s + "\n");
        System.out.print(sb);
    }

    static void backtracking(int personIdx, int cardCnt) {
        if(personIdx == N + 1) {
            int[] cardIdx = new int[N + 1];
            for(int i = 0; i < C; i++) {
                int person = res[i];
                commands[i] = orders.get(person).get(cardIdx[person]++);
            }
            operation();
            return;
        }

        if(personalCards[personIdx] == 0)
            backtracking(personIdx + 1, 0);
        else {
            personalCards[personIdx]--;
            for(int i = cardCnt; i < C; i++) {
                if(res[i] < 0) {
                    res[i] = personIdx;
                    backtracking(personIdx, i + 1);
                    res[i] = -1;
                }
            }
            personalCards[personIdx]++;
        }
    }

    static void operation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < C; i++) {
            int now = commands[i];
            String[] ops = cards[now].split(",");
            for(int j = 0; j < ops.length; j++) {
                StringTokenizer token = new StringTokenizer(ops[j]);
                if(token.nextToken().equals("ADD"))
                    sb.append(token.nextToken());
                else {
                    try {
                        sb.deleteCharAt(Integer.parseInt(token.nextToken()));
                    } catch (Exception e) {
                        set.add("ERROR");
                        return;
                    }
                }
            }
        }

        if(sb.length() == 0)
            set.add("EMPTY");
        else
            set.add(sb.toString());
    }
}