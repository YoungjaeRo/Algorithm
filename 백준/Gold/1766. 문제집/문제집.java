import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1 ~ N 번까지의 문제가 있고, 1이 가장 쉽고, N이 가장 어려움(난이도)
	 *
	 * 먼저 풀면 좋은 문제라는것이 존재한다
	 *
	 * 조건 3
	 * 1. N개의 문제를 모두 풀어야한다
	 * 2. 먼저 푸는 것이 좋은 문제가 있으면, 먼저 푸는 문제를 무조건 먼저 풀어야한다
	 * 3. 가능하면, 쉬운 문제부터 풀어야한다
	 *
	 * // 경로(방향) 이 주어지는데 그럼 이건 그래프 문제인가?
	 */

	static int N; // 문제 수
	static int M; // 간선 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 위상정렬(문제의 순서)는 꼭 인접리스트로 문제를 해결한다
		List<Integer>[] graph = new ArrayList[N + 1];

		for(int i = 1; i<= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 진입차수(indegree)에 대한 배열도 선언해준다

		int[] indegree = new int[N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b); // a -- > b
			indegree[b]++; // b 시점에서 a가 들어오는 것이므로 진입차수 +1
		}

		// PQ 선언(난이도가 낮은 문제를 기준으로 먼저 풀어야 하기 때문에)
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		/**
		 * 매우 중요 진입차수가 0인 것들부터 해당 큐에 넣는다
		 * 즉, 먼저 풀어야할 선행 문제가 없는 문제부터 큐에 삽입
		 * 난이도가 낮은것이(최소-힙) 기준으로 출력됨
		 */
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
			}
		}

		// 위상정렬 시행(바로 경로를 출력해야하기 때문에, 출력 시작)
		StringBuilder sb = new StringBuilder();

		while(!pq.isEmpty()) {
			// (1) 현재 풀 수 있는 문제 중 번호가 가장 작은 것 선택
			int cur = pq.poll();

			sb.append(cur).append(" ");

			// (2) cur을 풀었으니, cur 뒤에 따라오는 문제들의 선행 조건을 제거
			for(int next : graph[cur]) {
				// cur 하나 해결했으니 next의 선행조건 1 감소
				indegree[next]--;

				// (3) 선행 조건이 전부 해결되면 이제 풀 수 있음 -> pq에 넣기
				if(indegree[next] == 0) {
					pq.offer(next);
				}
			}

		}

		System.out.println(sb.toString());

	}
}
