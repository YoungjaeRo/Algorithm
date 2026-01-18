import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 면접관들은 서로 다른 N개의 도시에 거주한다.
	 *
	 * N개의 도시 중 K개의 도시에 면접장을 배치하였다.
	 *
	 * 단방향 연결, 거리(가중치)는 다름 -- > BFS는 아님
	 *
	 * 각 면접자들은 본인의 도시에서 출발해서 가장 가까운 면접장으로 찾아갈 예정
	 *
	 *
	 * 각 도시에서 면접장까지의 거리중, 그 거리가 가장 먼 도시에서 오는 면접자에게 교통비를 주려고 한다
	 *
	 * 면접장까지의 거리가 가장 먼 도시와, 그 거리를 구하시오
	 *
	 * 아이디어, 면접장이 K 개 이므로, 면접장들을 모두 시작점 0으로 두고,
	 * 한번의 다익스트라로 전체 최단거리를 구하는 멀티스코어 다익스트라를 사용한다.
	 *
	 * 본래 다익스트라는 시작점에서 각 노드의 거리만을 구한다
	 *
	 * 문제에서 원하는 것은, 각 도시 --> 면접장 거리이다.
	 *
	 * 그래서 결국 간선을 뒤집에서 저장하면 된다 ㅇㅇ
	 */

	// PQ 용 Cost 기준 오름차순 정렬 NODE 클래스 선언
	static class Node implements Comparable<Node> {
		int idx;
		long cost;

		Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	static final long INF = Long.MAX_VALUE;

	static int N; // 도시의 수 (노드 개수)
	static int M; // 도로의 수 (간선 개수)

	static int K; // 면접장의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 다익스트라는 인접리스트부터 시작 (노드 개수 만큼)
		List<Node> [] graph = new ArrayList[N + 1]; // 도시는 1번부터 N번까지이기 때문에

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 도시 연결 정보 주입 (간선의 정보를 뒤집어서 주입)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 간선 정보를 뒤집어서 저장함
			graph[to].add(new Node(from, cost));
		}

		// 면접장 도시들 입력
		st = new StringTokenizer(br.readLine());

		int[] centers = new int[K];

		for(int i = 0; i < K; i++) {
			centers[i] = Integer.parseInt(st.nextToken());
		}

		// dist[i] = (원래 그래프에서) i 도시 -> 가장 가까운 면접장 최단거리
		// (뒤집힌 그래프에서는) 면접장들 -> i 최단거리

		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);

		// 멀티 - 소스 다익스트라, 면접장 K개를 모두 거리 0으로  PQ에 삽입
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int c : centers) {
			dist[c] = 0;
			pq.offer(new Node(c, 0));
		}

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			// 이미 더 짧은 경로로 처리된적이 있으면, 스킵
			if(cur.cost > dist[cur.idx]) {
				continue;
			}

			for(Node next : graph[cur.idx]) {
				int nextIdx = next.idx;
				long nextCost = next.cost + cur.cost;

				if(dist[nextIdx] > nextCost) {
					dist[nextIdx] = nextCost;
					pq.offer(new Node(nextIdx, nextCost));
				}
			}
		}

		// dist 값 중 최댓값 찾기
		int answerCity = 1;
		long answerDist = dist[1];

		for(int i = 2; i <= N; i++) {
			if(dist[i] > answerDist) {
				answerDist = dist[i];
				answerCity = i;
			}
		}

		System.out.println(answerCity);
		System.out.println(answerDist);

	}
}
