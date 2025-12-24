import java.io.*;
import java.util.*;


public class Main {
	static int V; // 정점의 개수
	static int E; // 간선의 수


	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int cost;

		Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		// 간선 비용 기준 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int[] parents;

	static int find(int x) {
		if(x == parents[x]) {
			return x;
		}
		// 경로 압축
		parents[x] = find(parents[x]);

		return parents[x];
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if(pa == pb) {
			// 둘이 같은 그룹이면 false 반환
			return false;
		}

		// 한쪽으로 부모노트 통일하기
		parents[pb] = pa;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// MST는 꼭 엣지리스트로 문제를 풀어야 한다
		List<Edge> edges = new ArrayList<>();

		// 간선 정보 입력
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, cost));
		}

		// 오름차순 정렬 (필수)
		Collections.sort(edges);

		// 유니온 파인드 초기화
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		
		int answer = 0;
		int cnt = 0;  // 딱 노드 -1까지만 허용
		
		for(Edge e : edges) {
			// 둘이 다른 그룹이라면 --> 이어주고 MTS에 추가
			if(union(e.a, e.b)) {
				answer = answer + e.cost;
				cnt++;
			}
			
			if(cnt == V -1) {
				break;
			}
		}

		System.out.println(answer);

	}
}
