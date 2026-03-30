import java.io.*;
import java.util.*;

public class Main {
	static int N; // 과목의 수
	static int M; // 선수 조건의 수

	static List<Integer> [] graph;
	static int[] indegree;

	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 위상정렬용 인접리스트 생성
		graph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		indegree = new int[N + 1];
		dist = new int[N + 1];


		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// a과목을 수강해야, b 과목을 들을 수 있다
			graph[a].add(b);

			// 진입차수 배열도 업데이트
			indegree[b]++;
		}


		// 위상정렬용 큐 선언
		Queue<Integer> q = new ArrayDeque<>();

		// 진입차수가 0인 과목에 대해 큐에 삽입
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				dist[i] = 1;
			}
		}


		// 위상정렬시작

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int next : graph[cur]) {

				dist[next] = Math.max(dist[next], dist[cur] + 1);

				// 진입차수 1 빼주기
				indegree[next]--;

				if(indegree[next] == 0) {
					q.offer(next);
				}
			}

		}

		StringBuilder sb = new StringBuilder();

		for(int i = 1; i <= N; i++) {
			sb.append(dist[i]).append(" ");
		}

		System.out.println(sb);
	}
}
