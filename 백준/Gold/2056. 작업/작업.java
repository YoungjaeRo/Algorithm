import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 선행관계 -- > 위상정렬을 떠올려보자
	 *
	 * K번 작업에 대해 선행관계에 있는 작업들은 모두 번호가 1이상 k -1  이하이다
	 *  첫째 줄에는 N이 주어짐
	 *  둘째 줄부터  N + 1 줄까지, N개의 줄이 주어진다
	 *
	 *  2번째 줄에는 1번작업, 3번째 줄에는 2번작업등 N번의 작업을 각각 나타낸다
	 *
	 *  각 줄의 처음에는 해당 작업에 걸리는 시간(가중치),
	 *  그 다음에는 그 작업에 대해 선행관계에 있는 작업들의 개수
	 *  그 선행 작업들의 번호 ㅇㅇ(인덱스)
	 *
	 *  위상정렬은 indegree 배열 (진입차수)을 꼭 선언하고, 아무런 선행관계가 없는(indegree가 0)을 PQ에 넣으면서 최신화
	 *
	 */

	static int N;
	static int[] indegree;
	static int[] times; // 각 작업 소요 시간
	static int[] dp; // i 작업이 끝나는 가장 빠른 시간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());


		// 진입차수 배열 선언해주기
		indegree = new int[N + 1];

		// 각 작업의 소요 시간
		times = new int[N + 1];

		// i 작업이 끝나는 가장 빠른 시간
		dp = new int[N + 1];


		// 위상정렬은 인접리스트가 필요함
		List<Integer>[] graph = new ArrayList[N + 1];


		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;

		// 작업들의 선행정보에 대한 입력받기

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[i] = time; // 각 작업에 대한 소요 시간 입력

			// i 번째에 대한 선행 작업 개수
			int k = Integer.parseInt(st.nextToken());

			for(int j = 0; j < k; j++) {
				int prev = Integer.parseInt(st.nextToken());

				// 작업의 선행 관계는 prev -- > i
				graph[prev].add(i);
				indegree[i]++;
			}
		}

		// 위상정렬을 위한 PQ 선언
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 선행 할것이 없는, indegree가 0인 작업들을 pq에 삽입
		// dp[i] = time[i]로 시작

		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
				dp[i] = times[i];
			}
		}

		// 위상 정렬 + dp 계산하기
		while(!pq.isEmpty()) {
			int cur = pq.poll();

			for(int next : graph[cur]) {
				// cur 작업이 끝난뒤 next를 할 수 있기 때문에
				// next 완료 시간후보 cur + time[next]
				int cand = dp[cur] + times[next];

				// next는 선행 작업이 여러 개일 수 있으니 가장 늦게 끝나는 선행 기준(max)
				if(cand > dp[next]) {
					dp[next] = cand;
				}

				// 진입차수 감수 시켜주고, 0이라면  pq에 삽입해주기
				indegree[next]--;

				if(indegree[next] == 0) {
					pq.offer(next);
				}

			}
		}

		// 전체 완료 시간 dp[i]중 가장 큰것

		int answer = Integer.MIN_VALUE;

		for(int i = 1; i <= N; i++) {
			if(dp[i] > answer) {
				answer = dp[i];
			}
		}
		System.out.println(answer);

	}
}
