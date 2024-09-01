import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int idx = -1;
        int count = 0;
        for(int i = 0; i < N; i++) {
            if(str.charAt(i) == 'O')
                idx = i;
            else if(str.charAt(i) == '.')
                count++;
        }

        int left = 0;
        for(int i = idx - 1; i >= 0; i--) {
            if(str.charAt(i) == 'X')
                break;
            left++;
        }

        int right = 0;
        for(int i = idx + 1; i < N; i++) {
            if(str.charAt(i) == 'X')
                break;
            right++;
        }

        if(left > right) {
            int tmp = left;
            left = right;
            right = tmp;
        }

        int blank = count - (left + right);
        int res;
        if(left == 0)
            res = 0;
        else if(left == 1) {
            if(right == 1) {
                if(blank % 2 == 0)
                    res = 1;
                else
                    res = 0;
            } else {
                if((right + blank) % 2 == 0)
                    res = 0;
                else
                    res = 2;
            }
        } else
            res = 2;

        if(res == 0)
            System.out.println("mingyu");
        else if(res == 1)
            System.out.println("yunsu");
        else
            System.out.println("draw");
    }
}