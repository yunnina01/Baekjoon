import java.io.*;
import java.util.*;

public class Main {
	static List<String> list;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Loop: while(true) {
			list = new ArrayList<>();
			while(true) {
				String op = br.readLine();
				if(op.equals("QUIT"))
					break Loop;
				else if(op.equals("END"))
                    break;
				else
					list.add(op);
			}

			int N = Integer.parseInt(br.readLine());

            stack = new Stack<>();
			for(int i = 0; i < N; i++) {
				stack.push(Integer.parseInt(br.readLine()));
				sb.append(operate() + "\n");
				stack.clear();
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static String operate() {
		for(int i = 0; i < list.size(); i++) {
			int num = 0;
			String input = "";
			if(list.get(i).length() > 3) {
				StringTokenizer st = new StringTokenizer(list.get(i));
				input = st.nextToken();
				num = Integer.parseInt(st.nextToken());
			} else
                input = list.get(i);

			switch(input) {
                case "NUM":
                    stack.push(num);
                    break;
                case "POP":
                    if(stack.isEmpty())
                        return "ERROR";
                    stack.pop();
                    break;
                case "INV":
                    if(stack.isEmpty())
                        return "ERROR";
                    stack.push(-1 * stack.pop());
                    break;
                case "DUP":
                    if(stack.isEmpty())
                        return "ERROR";
                    stack.push(stack.peek());
                    break;
                case "SWP":
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        stack.push(top1);
                        stack.push(top2);
                    } else
                        return "ERROR";
                    break;
                case "ADD" :
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        long sum = (long)top1 + top2;
                        if(Math.abs(sum) > 1_000_000_000)
                            return "ERROR";
                        else
                            stack.push((int)sum);
                    } else
                        return "ERROR";
                    break;
                case "SUB":
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        long sub = (long)top2 - top1;
                        if(Math.abs(sub) > 1000000000)
                            return "ERROR";
                        else
                            stack.push((int)sub);
                    } else
                        return "ERROR";
                    break;
                case "MUL":
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        long mul = (long)top1 * top2;
                        if(Math.abs(mul) > 1000000000)
                            return "ERROR";
                        else
                            stack.push((int)mul);
                    } else
                        return "ERROR";
                    break;
                case "DIV":
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        if(top1 == 0)
                            return "ERROR";

                        long mul = (long)top1 * top2;
                        boolean neg = false;
                        if(mul < 0)
                            neg = true;

                        int div = Math.abs(top2) / Math.abs(top1);
                        if(neg)
                            stack.push(-1 * div);
                        else
                            stack.push(div);
                    } else
                        return "ERROR";
                    break;
                case "MOD":
                    if(stack.size() >= 2) {
                        int top1 = stack.pop();
                        int top2 = stack.pop();
                        if(top1 == 0)
                            return "ERROR";

                        int mod = Math.abs(top2) % Math.abs(top1);
                        if(top2 < 0)
                            stack.push(-1 * mod);
                        else
                            stack.push(mod);
                    } else
                        return "ERROR";
			}
		}
		if(stack.size() == 1)
			return String.valueOf(stack.pop());
		return "ERROR";
	}
}