import java.io.*;
import java.util.*;

public class Main {
	static int N; // 도시의 개수 (정점)
	static int M; // 버스의 개수 (간선)

	/**
	 * 한도시에서 출발해서, 다른 도시에 도착, 최소비용 구하기 (비용 있음, 단 음수는 아님)
	 * 무조건 다익스트라 알고리즘을 통해서 문제를 해결하면 된다.
	 */

	static class Node implements Comparable<Node> {
		int idx; // 도시 번호
		int cost; // 해당 도시까지의 비용

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		// PQ 전용 짧은 거리를 기준으로 우선순위 부여
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 다익스트라 알고리즘은 무조건 인접리스트를 사용한다
		List<Node> [] graph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 간선에 대한 정보 입력 받기 (출발, 도착, 비용)
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, cost));
		}

		// 시작과 도착 지점 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());

		// 다익스트라 알고리즘을 위한 PQ와 최소거리배열 선언
		int[] dist = new int[N + 1];
		int[] prev = new int[N + 1]; // 해당 지점으로 오기 직전에 있던 도시

		// 충분히 큰 값으로 초기화를 해준다
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);

		// -1로 다 초기화
		Arrays.fill(prev, -1);

		// 다익스트라 시작
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[from] = 0;

		// 시작 노드 부터 우선순위 큐에 삽입, 이때 누적 거리는 당연히 0
		pq.offer(new Node(from, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			int curCity = cur.idx;
			int curCost = cur.cost;

			// 1차 필터링
			if(curCost > dist[curCity]) {
				continue;
			}


			for(Node next : graph[curCity]) {
				int nextCity = next.idx;
				int nextCost = curCost + next.cost;

				// 최단 거리 갱신
				if(nextCost < dist[nextCity]) {
					dist[nextCity] = nextCost;
					prev[nextCity] = curCity; // 직전의 경로를 저장
					pq.offer(new Node(nextCity, nextCost));
				}
			}
		}

		// 경로 복원
		ArrayList<Integer> path = new ArrayList<>();

		int cur = to;
		while(cur != -1) {
			path.add(cur);
			cur = prev[cur];
		}

		 // 이건 내림차순 Collections.sort(path, Collections.reverseOrder());

		// 실제로 리스트 값을 뒤집기 (반전)
		Collections.reverse(path);

		StringBuilder sb = new StringBuilder();
		sb.append(dist[to]).append("\n");
		sb.append(path.size()).append("\n");

		for(int city : path) {
			sb.append(city).append(" ");
		}

		System.out.println(sb.toString());

	}
}
