import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		// 양 방향 큐
		ArrayDeque<Integer> q = new ArrayDeque<>();

		for(int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());

			String command = st.nextToken();

			switch(command) {
				case "push" :
				String num = st.nextToken(); // 1
					q.offer(Integer.parseInt(num));
				break;

				case "pop" :
					if(q.isEmpty()) {
						sb.append("-1").append("\n");
					} else {
						int first = q.pollFirst();
						sb.append(first).append("\n");
					}
					break;

				case "size" :
					sb.append(q.size()).append("\n");
					break;

				case "empty" :
					if(q.isEmpty()) {
						sb.append("1").append("\n");
					} else {
						sb.append("0").append("\n");
					}
					break;

				case "front" :
					if(q.isEmpty()) {
						sb.append("-1").append("\n");
					} else {
						sb.append(q.peekFirst()).append("\n");
					}
					break;

				case "back" :
					if(q.isEmpty()) {
						sb.append("-1").append("\n");
					} else {
						sb.append(q.peekLast()).append("\n");
					}
					break;

				default :
					break;
			}
		}
		System.out.println(sb.toString());
	}
}
