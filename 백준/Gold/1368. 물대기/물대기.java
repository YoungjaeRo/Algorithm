import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 각 논 i에 우물파기 비용 : W[i]
	 * 논 i와 j를 연결하는데 드는 비용 : P[i][j]
	 *
	 * 모든 논에 물을 대는 최소 비용 , 전체를 하나의 공급망으로 연결하고 , 이에 따른 비용을 최소화 하는 문제
	 *
	 * 가상노드 0 :
	 * 노드 0을 물의 공급원이라고 생각한다.
	 * 0 - j 간선비용 : W[i] -- > i 번 논에 우물을 판다
	 * i - j 간선비용 : P[i][j] --> i와 j를 파이프로 연결
	 */
	static class Edge implements Comparable<Edge> {
		/**
		 * 간선 클래스, u와 v를 연결하는 비용 cost
		 */
		int u;
		int v;
		int cost;

		Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		// 가장 작은 비용의 간선부터 뽑기 위해 오름 차순 정렬
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	/**
	 * 유니온 파인드(부모 찾기), 쿠루스칼 알고리즘에서는 필수임
	 *
	 * parent[x] : x의 부모
	 * find(x) : x의 부모 찾기(대표 노드)
	 * union(a, b) : a와 b의 부모노드를 통일
	 */

	static int[] parent;
	/**
	 * find(x):
	 *  - x의 루트를 찾아서 반환
	 *  - 경로 압축(Path Compression) 적용:
	 *    find를 호출하는 과정에서 parent를 루트로 바로 붙여
	 *      이후 find가 매우 빨라짐
	 */

	static int find(int x) {
		if(parent[x] == x) {
			return x; // 자기 자신이 대표 루트
		}
		/**
		 * 제일 중요
		 */
		parent[x] = find(parent[x]); // 경로 압축

		return parent[x];
	}
	/**
	 * union(a, b):
	 *  - a와 b의 루트를 찾아서
	 *  - 서로 다른 집합이면 합치고 true
	 *  - 이미 같은 집합이면(=같은 루트면) 합치지 않고 false
	 *
	 * Kruskal에서 이 return 값 의미:
	 *  - true  => 이 간선은 MST에 추가해도 사이클이 안 생김(필요한 연결)
	 *  - false => 이 간선을 추가하면 사이클이 생김(버린다)
	 */

	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		// 같은 유니온이라면,
		if(ra == rb) {
			return false;
		}

		// 한쪽 루트로 부모 노드 통일
		parent[rb] = ra;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 모든 간선을 저장할 리스트

		List<Edge> edges = new ArrayList<>();

		// 1) 우물 비용 입력: (0-i) 간선으로 추가
		// i번 논에 우물을 파는 비용 W[i]는
		// "물 공급원(0)과 i를 직접 연결하는 비용
		for(int i = 1; i <= N; i++) {
			int w = Integer.parseInt(br.readLine());
			edges.add(new Edge(0, i, w)); // 0부터 해당지점 i까지 w 만큼
		}

		// 2) 파이프 비용 입력: (i-j) 간선으로 추가
		// 입력은 N x N 행렬이지만,
		// i<j 인 경우만 간선을 저장하면 중복을 피할 수 있다
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 1; j <= N; j++) {
				int c = Integer.parseInt(st.nextToken());
				if(i < j) {
					edges.add(new Edge(i, j, c));
				}
			}
		}

		// 3. 크루스칼은 꼭 오름차순 정렬을 실행해줘야 한다
		Collections.sort(edges);

		// 4. 유니온 파인드 초기화
		parent = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			// 처음엔 각자 자신이 대표노드
			parent[i] = i;
		}

		int answer = 0;

		// MTS에서 필요한 간선의 수 -- > 노드의 수 - 1
		int cnt = 0;

		for(Edge e : edges) {
			// 둘이 같은 집합이 아니면(사이클 X) MTS 간선에 추가
			if(union(e.u, e.v)) {
				answer = answer + e.cost;
				cnt++;


				if(cnt == N) {
					break;
				}
			}
		}

		System.out.println(answer);


	}

}
