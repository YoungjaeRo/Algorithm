import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 0 / 1 BFS (가중치가 0 또는 1) 즉 2개 뿐임
	 * 비용이 0인 이동은 큐의 앞,
	 * 비용이 1인 이동은 큐의 뒤에 삽입한다
	 */

	// 100000까지 칸이 있기 때문에, MAX는 이거에서  + 1

	static final int MAX = 100001;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 해당 위치까지 도달하는데 걸리는 시간
		int[] time = new int[MAX];

		Arrays.fill(time, INF);

		ArrayDeque<Integer> dq = new ArrayDeque<>();

		time[N] = 0;

		dq.offer(N);

		while(!dq.isEmpty()) {
			int cur = dq.pollFirst();

			if(cur == K) {
				System.out.println(time[cur]);
				return;
			}

			// 1. 순간이동 --> 시간은 그대로
			int next = cur * 2;

			if(next < MAX && time[next] > time[cur]) {
				time[next] = time[cur];
				dq.offerFirst(next);
			}

			// 2. X + 1칸  시간은 + 1초
			next = cur + 1;
			if(next < MAX && time[next] > time[cur] + 1) {
				time[next] = time[cur] + 1;
				dq.offerLast(next);
			}

			// 3. X - 1칸 시간은  + 1초
			next = cur - 1;
			if(next >= 0 && time[next] > time[cur] + 1) {
				time[next] = time[cur] + 1;
				dq.offerLast(next);
			}
		}
	}
}
