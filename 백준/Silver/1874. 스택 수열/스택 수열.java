import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		Stack<Integer> stack = new Stack<>();

		int nextPush = 1;

		for(int i = 0; i < N; i++) { // [3, 1, 2]
			int target = Integer.parseInt(br.readLine());


			while(nextPush <= target) {

				stack.push(nextPush);
				sb.append("+\n");
				nextPush++; // 3
			}

			if(!stack.isEmpty() && stack.peek() == target) {
				stack.pop();
				sb.append("-\n");
			} else {
				System.out.println("NO");
				return;
			}
		}
		System.out.println(sb);
	}

}
