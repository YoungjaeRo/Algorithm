import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	static int S;
	static int E;
	static List<Integer>[] graph;

	static int[] parents;


	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

	N = Integer.parseInt(st.nextToken());
	M = Integer.parseInt(st.nextToken());

	graph = new ArrayList[N + 1];

	// 인접리스트 생성
	for(int i = 1; i <= N; i++) {
		graph[i] = new ArrayList<>();
	}

	// 관계 주입
	for(int i = 0; i < M; i++) {
		st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		graph[a].add(b);
		graph[b].add(a);

	}

	// S, E 지점 입력받기
		st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());


		// 더 작은 숫자를 기준으로 정렬먼저
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}

		// 추후에 역방향으로 BFS를 할때, 추적이 용이하게끔 parent 배열 선언
		parents = new int[N + 1];

		Arrays.fill(parents, -1);


		// S -- > E에 대한 BFS 시작
		int goDist = BfsWithP(S, E, parents);

		// 해당 경로에 대한 BFS 실행 후, 사용된 중간 지점들 막기
		boolean[] blocked = new boolean[N + 1];
		blockPath(S, E, blocked);

		// E-- > S에 대한 BFS 시작
		int comeDist = BfsWithBL(E, S, blocked);

		System.out.println(goDist + comeDist);

	}

	static int BfsWithP(int start, int end, int[] parents) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];

		visited[start] = true;

		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur == end) {
				return dist[cur];
			}

			for(int next : graph[cur]) {
				if(!visited[next]) {
					visited[next] = true;

					// 해당 next 지점은 cur을 통해서 오게됨(경로 기록)
					parents[next] = cur;
					dist[next] = dist[cur] + 1;
					q.offer(next);
				}
			}
		}

		return -1;
	}

	static void blockPath(int start, int end, boolean[] blocked) {
		int cur = end;

		while(cur != start) {
			cur = parents[cur];

			if(cur != start) {
				blocked[cur] = true;
			}
		}
	}

	static int BfsWithBL(int start, int end, boolean[] blocked) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];

		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur == end) {
				return dist[cur];
			}

			for(int next : graph[cur]) {
				// 이미 방문한 노드면 스킵(BFS 용)
				if(visited[next]) {
					continue;
				}

				// 선행된 BFS에서 갔다온 경로면 스킵
				if(blocked[next]) {
					continue;
				}

				visited[next] = true;
				dist[next] = dist[cur] + 1;
				q.offer(next);
			}
		}

		return -1;
	}
}
