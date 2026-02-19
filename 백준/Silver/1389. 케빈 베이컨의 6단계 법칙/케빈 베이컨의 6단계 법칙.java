import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 플로이드 워셜로 풀 수도 있지만, 일단 무엇보다 가중치가 1이기 때문에
	 * BFS로도 풀수 있음 ㅇㅇ
	 */

	static List<Integer> [] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];

		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		// 친구 관계 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		int answer = 1;
		int minSum = Integer.MAX_VALUE;

		// 모든 사람을 기준으로 그래프 탐색을 시작 & 가장 작은 베이컨은 누군지 구하기
		for(int i = 1; i <= n; i++) {
			int sum = bfs(i, n); // i에서 시작했을때, 거리의 합

			if(sum < minSum) {
				minSum = sum;
				answer = i;
			}
		}

		System.out.println(answer);

	}

	static int bfs(int start, int n) {
		int[] dist = new int[n + 1];
		Arrays.fill(dist, -1);
		dist[start] = 0;

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);

		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int c : graph[cur]) {
				if(dist[c] == -1) {
					dist[c] = dist[cur] + 1;
					q.offer(c);
				}
			}
		}

		int sum = 0;
		
		// 꼭 시작점이 포함되지 않게 조심
		for(int i = 1; i <= n; i++) {
			sum += dist[i];
		}

		return sum;

	}
}
