import java.io.*;
import java.util.*;

public class Main {
	/**
	 * N명의 학생이 각각 마을에 한명씩 살고 있다
	 * N 명의 학생이, X 번 마으레 모여, 파티를 벌이기로 했다.
	 * 총 M 개의 단방향 도로들이 있고, i 번째 길을 지나는데, T의 시간을 소비한다
	 *
	 * 최단기간에 오고가기를 원한다(최단거리)
	 * 왕복 최단거리 : i --> X, X -- > i
	 *
	 * 아이디어 : X -- > i 원래 그래프에서 X로 다익스타라 1번
	 * i -- > X : 간선을 뒤집은 그래프(reverse)에서 X로 다익스트라 1번
	 */

	static class Node implements Comparable<Node> {
		int idx; // 정점 번호
		int cost; // 인접리스트에선, 다음 정점   PQ 에서는 현재 정점과 누적거리

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int N;
	static int M;
	static int X;

	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		X = Integer.parseInt(st.nextToken()); // 파티장소

		// 정방향, 역방향 (다익스트라는 꼭 인접리스트로 간선 정보를 저장해야함)

		List<Node> [] graph = new ArrayList[N + 1];
		List<Node> [] reverseGraph = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}

		// 정방형 & 역방향으로 간선 정보 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 정방향
			graph[from].add(new Node(to, cost));

			// 역방향
			reverseGraph[to].add(new Node(from, cost));
		}

		/**
		 * (정방향)
		 * X -- > i 까지 최단거리 배열
		 */

		int[] distFromX = dijkstra(graph, X);

		/**
		 * (역방향)
		 * i -- > X까지 최단거리 배열
		 */

		int[] distToX = dijkstra(reverseGraph, X);

		/**
		 * 각 i에 대해 왕복거리 계산 후 최댓값
		 */

		int answer = 0;
		for(int i = 1; i <= N; i++) {
			// 왕복 = (i->X) + (X->i)

			int roundTrip = distFromX[i] + distToX[i];
			answer = Math.max(answer, roundTrip);
		}

		System.out.println(answer);
	}

	/**
	 * 다익스타라 함수 start에서부터 모든 정점까지 최단거리 dist[]를 반환
	 * @param graph
	 * @param start
	 * @return
	 */
	static int[] dijkstra(List<Node>[] graph, int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// PQ각 꼭 필요(다익스트라)
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			int curCity = cur.idx;
			int curCost = cur.cost;

			// 최적화
			if(curCost > dist[curCity]) {
				continue;
			}

			for(Node next : graph[curCity]) {
				int nextCity = next.idx;
				int nextCost = curCost + next.cost;

				if(nextCost < dist[nextCity]) {
					// 최저값으로 갱신해주기
					dist[nextCity] = nextCost;
					pq.offer(new Node(nextCity, nextCost));
				}
			}
		}

		return dist;
	}
}
