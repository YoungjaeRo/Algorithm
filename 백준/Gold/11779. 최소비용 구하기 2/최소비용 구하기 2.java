import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 전형적인 다익스트라 문제인데 이제... 경로 출력을 곁들인
	 */

	static int n;
	static int m;

	static final int INF = Integer.MAX_VALUE;


	// 다익스트라는 Node 클래스를 선언해서 푸는게 훨씬 수월하다
	static class Node implements Comparable<Node> {
		int idx;
		int cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine()); // 정점개수
		m = Integer.parseInt(br.readLine());

		// 다익스트라는 인접리스트
		List<Node> [] graph = new ArrayList[n + 1];

		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();

		}

		// 출발, 도착, 비용에 대한 값 입력받기
		StringTokenizer st;

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[start].add(new Node(end, cost));
		}

		// 출발지와 도착지 정보 입력받기
		st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// 간 구간에 대한 비용 배열(dist) 선언
		int[] dist = new int[n + 1];

		// 경로 추적용 배열 선언
		int[] prev = new int[n + 1];

		Arrays.fill(dist, INF);
		dist[S] = 0;

		// 다익스트라를 위한 PQ
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			int curIdx = cur.idx;
			int curCost = cur.cost;

			if(curCost > dist[curIdx]) {
				continue;
			}

			for(Node next : graph[curIdx]) {
				int nextIdx = next.idx;
				int nextCost = next.cost + curCost;

				if(dist[nextIdx] > nextCost) {
					dist[nextIdx] = nextCost;
					prev[nextIdx] = curIdx;
					pq.offer(new Node(nextIdx, nextCost));
				}
			}
		}


		// 경로 복원 (끝에서부터 시작해서 나중에 reverse)
		List<Integer> path = new ArrayList<>();
		
		int cur = E;
		while(cur != 0) {
			path.add(cur);
			
			if(cur == S) {
				break;
			}
			
			cur = prev[cur];
		}
		
		// 정방향으로 재정렬
		Collections.reverse(path);
		
		StringBuilder sb = new StringBuilder();
		sb.append(dist[E]).append("\n");
		sb.append(path.size()).append("\n");
		for(int v : path) {
			sb.append(v).append(" ");
		}

		System.out.println(sb);
		

	}
}
