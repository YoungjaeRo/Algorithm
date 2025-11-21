import java.io.*;
import java.util.*;

import com.sun.security.jgss.GSSUtil;

public class Main {
	static int N;
	static String[] cmds = new String[2];
	static Deque<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			cmds[0] = st.nextToken();

			if(st.hasMoreTokens()) {
				cmds[1] = st.nextToken();
			}

			switch (cmds[0]) {
				case "push" :
					q.offer(Integer.parseInt(cmds[1]));
					break;

				case "pop" :
					if(q.isEmpty()) {
						System.out.println("-1");
					} else {
						int front = q.poll();
						System.out.println(front);
					}

					break;

				case "size" :
					System.out.println(q.size());
					break;

				case "empty" :
					if(q.isEmpty()) {
						System.out.println("1");
					} else {
						System.out.println("0");
					}
					break;

				case "front" :
					if(q.isEmpty()) {
						System.out.println("-1");
					} else {
						int mostfront = q.peekFirst();
						System.out.println(mostfront);
					}
					break;

				case "back"	:
					if(q.isEmpty()) {
						System.out.println("-1");
					} else {
						int mostback = q.peekLast();
						System.out.println(mostback);
					}

					break;

				default :
					break;
			}
			
		}
	}
}
