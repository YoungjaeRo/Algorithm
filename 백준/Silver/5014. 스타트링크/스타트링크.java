import java.io.*;
import java.util.*;

public class Main {
	static int F;
	static int S;
	static int G;
	static int U;
	static int D;

	static int[] dist;

	static int[] dx = {-1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken()); // 10
		S = Integer.parseInt(st.nextToken()); // 1
		G = Integer.parseInt(st.nextToken()); // 10
		U = Integer.parseInt(st.nextToken()); // 2
		D = Integer.parseInt(st.nextToken()); // 1

		if(S == G) { // 이미 같은 층에 있었다며느 0을 출력하고 메서드 종료
			System.out.println(0);
			return;
		}

		dist = new int[F + 1]; // 해당 층까지 도달하는데, 누른 버튼의 수

		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		dist[S] = 0; //  이미 있는 층은 0임

		while(!q.isEmpty()) {
			int cur = q.poll();

			// 올라갈때는 cur + U
			int up = cur + U;

			if(U > 0 && up <= F && dist[up] == 0) {
				dist[up] = dist[cur] + 1;

				if(up == G) {
					System.out.println(dist[up]);
					return;
				}
				q.offer(up);
			}

			int down = cur - D;
			if(D > 0 && down >= 1 && dist[down] == 0) {
				dist[down] = dist[cur] + 1;

				if(down == G) {
					System.out.println(dist[down]);
					return;
				}

				q.offer(down);
			}
		}

		// 이 마저도 도달하지 못했으면 계단을 이용
		System.out.println("use the stairs");
	}
}
