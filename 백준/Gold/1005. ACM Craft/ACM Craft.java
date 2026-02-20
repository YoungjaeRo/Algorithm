import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 선행조건이 완료되어야 그 다음 조건이 실행됨 -- > 위상정렬
	 *
	 * 여러 작업들중 뭐가 끝나야 시작이 가능함 ㅇㅇ 최대 완료 시간이 ㅇㅇ -- > DP
	 */

	static int T;

	static class Building {

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 건물의 개수
			int n = Integer.parseInt(st.nextToken());

			// 규칙의 수
			int k = Integer.parseInt(st.nextToken());

			// 건물당 걸리는 시간 입력받기 ㅇㅇ
			st = new StringTokenizer(br.readLine());

			int[] time = new int[n + 1];

			for(int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			// 위상정렬도 인접리스트를 통해서 푼다
			List<Integer>[] graph = new ArrayList[n + 1];

			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}

			// indegree 배열도 필수
			int[] indegree = new int[n + 1];

			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph[from].add(to);
				indegree[to]++;
			}


			// dp[i] = i번째까지 건축을 완료했을떄, 드는 최대 비용
			int[] dp = new int[n + 1];

			// Kahn 알고리즘을 해결하기 위해 PQ를 선언
			PriorityQueue<Integer> pq = new PriorityQueue<>();

			// indegree 수치가 0인것부터 pq에 삽입함 ㅇㅇ
			for(int i = 1; i <= n; i++) {
				if(indegree[i] == 0) {

					// dp초기값들도 잘 세팅해주고
					dp[i] = time[i];
					pq.offer(i);
				}
			}

			// 백준이가 건설해야할 건물으 번호 입력받기
			int w = Integer.parseInt(br.readLine());

			// 위상정렬 시작
			while(!pq.isEmpty()) {
				int cur = pq.poll();

				for(int next : graph[cur]) {
					dp[next] = Math.max(dp[next], dp[cur] + time[next]);

					// 이제 다음으로 넘어가기 때문에, indegree 하나 빼주고
					indegree[next]--;

					if(indegree[next] == 0) {
						pq.offer(next);
					}
				}
			}

			int ans = dp[w];
			System.out.println(ans);
		}
	}
}
