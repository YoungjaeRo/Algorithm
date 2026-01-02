import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1 번 정점에서 N번 정점으로의 최단거리 --> 무조건 다익스트라
	 *
	 * 단 최단거리를 구할때, 두가지의 조건이 만족되어야한다.
	 *
	 * 임의로 주어진 두 정점은 반드시 통과
	 *
	 * 1 번 정점에서 N번 정점으로 이동할때, 주어진 두 정점을 반드시 거치면서 최단경로로 이동하는 프로그램을 구하시오
	 *
	 * 다익스트라는 계속해서 가장 작은 가중치의 노드를 꺼내야하므로, 따로 노드 클래스를 만들고,
	 * PQ안에 삽입해야 한다.
	 *
	 * 또한 최단거리에 대한 배열도 필요하다 (정점의 개수 만큼)
	 *
	 * 문제에서는 특정 정점인 v1과 v2를 지나야한다는 조건이 있다
	 * 그럼 정점 1에서 N까지의 최단거리는
	 *
	 * 1 -> v1 -> v2 -> N 또는
	 * 1 -> v2-> v1 -> N  이다.
	 *
	 * 그래서 필요한 최단거리를 따로 구해서 더하면 된다
	 *
	 * dist 1 [] 1에서 모든 정점까지
	 *
	 * dist v1[] v1에서 모든 정점까지
	 *
	 * dist v2[] v2에서 모든 정점까지
	 *
	 * 경로1 = dist1[v1] + distV1[v2] + distV2[N]
	 *
	 * 경로2 = dist1[v2] + distV2[v1] + distV1[N]
	 *
	 * 둘 중 최소가 답.
	 */

	static class Node implements Comparable<Node> {
		int idx;
		int cost;

		Node (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override // comparable을 오버라이딩할때는  compareTo, comparator는 compare
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	static int N; // 정점의 개수
	static int E; // 간선의 개수

	static List<Node> [] graph;

	static final int INF = 2000000;

	static int[] dijkstra(int start) {
		int[] dist = new int[N + 1];

		Arrays.fill(dist, INF);

		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			int curIdx = cur.idx;
			int curCost = cur.cost;

			// 이미 더 짧은 경로로 갱신된 적이 있다는 뜻이라서 버린다.
			if(curCost > dist[curIdx]) {
				continue;
			}

			for(Node next : graph[curIdx]) {
				int nextIdx = next.idx;
				int nextCost = curCost + next.cost;

				// 더 작은 비용이면, 갱신하고 큐에 넣기
				if(nextCost < dist[nextIdx]) {
					dist[nextIdx] = nextCost;
					pq.offer(new Node(nextIdx, nextCost));
				}
			}
		}

		return dist;

	}


	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

	N = Integer.parseInt(st.nextToken());
	E = Integer.parseInt(st.nextToken());

	// 다익스트라는 꼭 인접리스트로 문제를 해결한다

		graph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();

		}

		// 양방향이 존재한다고 했기 때문에 꼭 양방향으로 다 집어넣어주어야 한다

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));

		}

		// 반드시 거쳐야 하는 두 정점 입력받기
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// 다익스트라 3번
		// 1 -- >  모든 정점까지
		// v1 -- > 모든 정점까지
		// v2 --> 모든 정점까지

		int[] dist_1 = dijkstra(1);
		int[] dist_v1 = dijkstra(v1);
		int[] dist_v2 = dijkstra(v2);

		/**
		 * 경로의 경우의 수
		 * 1 -- > V1 -- > V2 -- > N 또는
		 * 1 -- > V2 -- > V1 -- > N
		 */

		int path1 = dist_1[v1] + dist_v1[v2] + dist_v2[N];
		int path2 = dist_1[v2] + dist_v2[v1] + dist_v1[N];

		int ans = Math.min(path1, path2);

		// 둘 중 하나라도 길이 없으면 INF가 섞여서 ans가 INF 이상이 됨

		if(ans >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}

	}
}
