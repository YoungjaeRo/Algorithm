import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 일단 문제에서 주어진 가중치는 1이 아님
	 *
	 * 주어진 시작점에서 다른 모든 정점으로의 최단경로 -- > 다익스트라 알고리즘
	 *
	 * 1. 정점 V개와 간선 E가 주어진다
	 *
	 * 2. 시작정점 번호가 주어진다 K
	 *
	 * 3. E 개의 줄에 걸쳐 각 간선을 나태내는 세개의 정수 u,v,w가 주어진다
	 * u에서 v로 가는 가중치 w를 뜻한다
	 */

	static int V; // 정점 개수
	static int E; // 간선 개수

	static final int INF = Integer.MAX_VALUE;


	// 다익스트라는 개별 Node 클래스를 선언해주는게 풀이에 용이함
	static class Node implements Comparable<Node> {
		int idx; // 노드 인덱스
		int cost; // 가중치(비용)


		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		// 비용 기준 오름차순 정렬
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 다익스트라는 인접리스트!
		List<Node> [] graph = new ArrayList[V + 1]; // 정점은 1부터 V까지 넘버링이 되어있기 때문에

		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		int K = Integer.parseInt(br.readLine()); // 시작 정점 (거리 0)

		// 거리 배열 선언하기
		int[] dist = new int[V + 1];
		Arrays.fill(dist, INF);

		// 시작 정점은 0으로 초기화
		dist[K] = 0;

		// 각 시작점부터 끝점까지의 가중치 정보 입력받기
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, cost));
		}


		// 다익스트라를 위한 PQ
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(K, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			int curIdx = cur.idx;
			int curCost = cur.cost;

			// 가지치기
			if(curCost > dist[curIdx]) {
				continue;
			}

			for(Node next : graph[curIdx]) {
				int nextIdx = next.idx;
				int nextCost = next.cost + curCost;

				if(dist[nextIdx] > nextCost) {
					dist[nextIdx] = nextCost;
					pq.offer(new Node(nextIdx, nextCost));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) sb.append("INF\n");
			else sb.append(dist[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
