import java.util.*;
import java.io.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// 최소 힙(우선순위 큐) 선언
		PriorityQueue<Integer> q = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if(num == 0) {
				if(q.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					int cur = q.poll();
					sb.append(cur).append("\n");
				}
			} else {
				q.offer(num);
			}

			
		}

		System.out.println(sb.toString());
	}
}
