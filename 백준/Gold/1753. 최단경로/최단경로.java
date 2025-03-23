import java.io.*;
import java.util.*;

public class Main {
	/**
	 *
	 * 시작점과 다른 노드와 관련된 "최단 거리"를 구하는 문제이므로, 다익스트라 알고리즘을 사용하면 된다
	 */

	public static int V,E,K;
	// V = 노드의 개수 E = 에지의 개수 K = 출발노드

	public static int[] distance; // 거리 배열

	public static boolean[] visited; // 해당 노드의 방문 여부를 확인하기 위한 배열

	public static ArrayList<Edge> [] list; // 인접리스트 초기화

	public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();



	public static void main(String[] args) throws IOException {
		// 최단거리를 초기화 할때, 시작노드는 0, 나머지 배열은 무한대 (최대한 큰값)으로 지정해준다.

		/**
		 * 다익스트라 알고리즘 수행과정
		 * 1. 거리 배열에서 아직 방문하지 않은 노드 중 현재 값이 가장 작은 노드를 선택한다.
		 * 2. 선택한 가장 작은 노드(출발 노드)를 우선순위 큐에 삽입하면서 시작
		 * 3. 해당 노드와 연결된 노드들의 최단 거리값을 다음 공식을 통해 업데이트 한다.
		 * ** 해당 공식**
		 * [선택 노드의 거리배열 값 + 에지 가중치]의 값이 본래 거라배열에서의 값보다 작으면, 업데이트를 해준다.
		 *  업데이트가 되는 경우 연결노드를 우선순위 큐에 삽입 (즉, 최소값이 아니어서 업데이트가 되지 않으면, 우선슌위 큐에 삽입 불가
		 *  완성된 거리배열 값을 출력함
		 *  */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		//거리배열은 0번 인덱스를 사용하지 않을것이기에, V + 1만큼 초기화 (다른 배열들도 마찬가지임)
		distance = new int[V + 1];
		visited = new boolean[V + 1];

		list = new ArrayList[V + 1];

		// 인접리스트 초기화
		for(int i = 1; i <= V; i++){
			list[i] = new ArrayList<Edge>();
		}

		// 거리 배열 초기화
		for(int i = 0; i <= V; i++){
			distance[i] = Integer.MAX_VALUE;
		}

		// 가중치가 있는 인접리스트 초기화 하기
		for(int i = 0; i< E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list[u].add(new Edge(v,w));
		}

		// 다익스트라 알고리즘 시작 (K를 시작점으로 설정하기--> 시작 노드를 우선순위 큐에 삽입하고, 해당 거리배열의 값을 0으로 초기화 해준다)
		q.add(new Edge(K,0));
		distance[K] = 0;

		// 큐가 비어있을때까지
		while(!q.isEmpty()) {
			Edge current = q.poll();
			int c_v = current.vertex;

			if(visited[c_v]) {
				continue; // 이미 방문한 적이 있는 노드는 다시 큐에 넣지 않음
			}

			visited[c_v] = true;

			for(int i = 0; i< list[c_v].size(); i++) {
				Edge tmp = list[c_v].get(i);
				int next = tmp.vertex;
				int value = tmp.value;

				if(distance[next] > distance[c_v] + value) { // 거리 값을 최소값으로 업데이트 하기
					distance[next] = distance[c_v] + value;
					q.add(new Edge(next, distance[next]));

				}
			}
		}

		// 거리배열(답) 출력하기
		for(int i = 1; i<= V; i++) {
			if(visited[i]) {
				System.out.println(distance[i]);
			} else {
				System.out.println("INF");
			}
		}


	}

	static class Edge implements Comparable<Edge> {
		int vertex, value;

		Edge(int vertex, int value) {
			this.vertex = vertex;
			this.value = value;

		}

		@Override
		public int compareTo(Edge e) {
		if(this.value > e.value) {
			return 1;
		} else {
			return -1;
			}
		}
	}
}