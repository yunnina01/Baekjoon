import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();
        
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        int res = 0;
        while(true) {
            String chat = br.readLine();
            if(chat == null)
                break;

            if(S.compareTo(chat.split(" ")[0]) >= 0)
                start.add(chat.split(" ")[1]);
            else if(E.compareTo(chat.split(" ")[0]) <= 0 && Q.compareTo(chat.split(" ")[0]) >= 0)
                end.add(chat.split(" ")[1]);
        }

        for(String lec : end) {
            if(start.contains(lec))
                res++;
        }
        System.out.println(res);
    }
}