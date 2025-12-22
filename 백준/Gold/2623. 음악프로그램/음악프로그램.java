import java.io.*;
import java.util.*;

public class Main {
	static int N; // 가수의 수
	static int M; // 보조 PD의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 위상정렬은 꼭 인접리스트로 표현
		ArrayList<Integer> [] graph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 진입 차수 배열도 꼭 필요함
		int[] indegree = new int[N + 1];


		// -- >  기준으로 인접리스트 초기화 해주기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 가수 수

			int prev = Integer.parseInt(st.nextToken());

			for(int j = 1; j < num; j++) {
				int cur = Integer.parseInt(st.nextToken());
				graph[prev].add(cur);
				indegree[cur]++;

				prev = cur;
			}
		}

		// 위상정렬 스타트

		// 진입차수가 0인 것들을 넣어줄 큐 선언
		ArrayDeque<Integer> q = new ArrayDeque<>();

		// 경로 출력용 StringBuilder()
		StringBuilder sb = new StringBuilder();

		// 앞에 아무것도 없어서, 바로 출력가능한것들은 큐에 삽입
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}

		/**
		 * 실제로 몇 명을 순서에 넣었는지 세기 위한 변수, 이것으로 사이클을 판별한다
		 */

		int count = 0;


		while(!q.isEmpty()) {
			// 다 0인것들만 큐에 들어옴
			int cur = q.poll();

			// 해당 cur을 경로에 출력
			sb.append(cur).append("\n");
			count++;

			// 해당 노드와 인접한 노드들을 탐색하면서
			for(int next : graph[cur]) {

				// cur을 앞에 세웠으므로, 진입차수 -1
				indegree[next]--;

				if(indegree[next] == 0) {
					q.offer(next);
				}

			}
		}

		/**
		 * 사이클 판별하기
		 * N명을 전부 출력하지 못했다? 누군가는 서로 앞에 오라고 순서가 꼬여있음
		 * 순서대로 나열 불가능
		 */

		if(count != N) {
			System.out.println(0);
		} else {
			System.out.println(sb);
		}

	}
}
