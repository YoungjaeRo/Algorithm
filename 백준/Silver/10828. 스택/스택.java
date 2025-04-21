import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();

		while(N-- > 0) {
			String line = br.readLine();
			String[] cmds = line.split(" ");
			String cmd = cmds[0];

			switch(cmd) {
				case "push" :
					stack.push(Integer.parseInt(cmds[1]));
					break;

				case "pop" :
					if(stack.isEmpty()) {
						System.out.println("-1");
					} else {
						int num = stack.pop();
						System.out.println(num);
					}
					break;


				case "size" :
					System.out.println(stack.size());
					break;

				case "empty" :
					if(stack.isEmpty()) {
						System.out.println("1");
					} else {
						System.out.println("0");
					}
					break;

				case "top" :
					if(stack.isEmpty()) {
						System.out.println("-1");
					} else {
						System.out.println(stack.peek());
					}
					break;

				default :
					continue;
 			}
		}

	}
}
