import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 가중치가 1로 동일하기 때문에, BFS로 푸는게 더 효율적이다
	 */

	static int N;
	static int M;
	static List<Integer> [] graph;

	// 각 지점이 몇개와 연결되어 있는지를 저장하는 배열
	static int[] cnts;

	static boolean[] visited; // BFS를 위한 방문여부 배열

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cnts = new int[N + 1];

		graph = new ArrayList[N + 1];

		// 인접리스트 초기화
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();

		}


		// 연결 정보 주입
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[b].add(a);
		}

		for(int i = 1; i <= N; i++) {
			bfs(i);
		}

		int max = 0;


		for(int i = 1; i <= N; i++) {
			max = Math.max(max, cnts[i]);
		}
		
		for(int i = 1; i <= N; i++) {
			if(cnts[i] == max) {
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);

	}

	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N + 1];
		
		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int next : graph[cur]) {
				if(!visited[next]) {
					visited[next] = true;

					// 연결되어 있기 때문에 값 + 1
					cnts[start]++;

					q.offer(next);
				}
			}
		}
	}
}
