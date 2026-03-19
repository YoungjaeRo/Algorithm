import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 친구와 친구의 친구까지만 초대하기로 함
	 *
	 * 시작점이 정해져 있는 BFS를 하면 됨, 가중치가 1로 일정하기 때문에
	 * 다익스트라는 굳이이다.
	 */

	static int N; // 상근이 포함 동기 수 (상근이는 1번)

	static int M; // 리스트의 길이

	// 상근이와 떨어져 있는 정도 + 방문여부 확인용 배열
	static int[] dist;

	// 인접리스트
	static List<Integer>[] graph;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];

		// 인접리스트 선언해주기
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;

		// 서로 친구 관계 주입해주기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		dist = new int[N + 1];

		Arrays.fill(dist, -1);

		bfs(1);

		// 결혼식에 초대할 동기수
		int answer = 0;

		for(int i = 2; i<= N; i++) {
			if(dist[i] == 1 || dist[i] == 2) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		dist[start] = 0;
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int next : graph[cur]) {
				// 아직 방문하지 않았다면,
				if(dist[next] == -1) {
					dist[next] = dist[cur] + 1;

					q.offer(next);
				}
			}
		}
	}
}
