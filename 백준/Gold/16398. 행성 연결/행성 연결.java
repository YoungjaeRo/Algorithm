import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 모든 요소들을 연결하면서, 비용을 최소화하고 싶다 -- > 최소신장트리 ㅇㅇ
	 */

	static int N;

	// 유니온 파인드를 위한 배열(정점 수 만큼)
	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int cost;

		Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}


	//  유니온 파인드 메서드

	static int find(int x) {
		if(x == parents[x]) {
			return x;
		}

		// 경로 압축
		parents[x] = find(parents[x]);

		return parents[x];
	}

	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		if(ra == rb) {
			return false;
		}

		// 둘이 부모가 다르면, 한쪽으로 통일
		parents[rb] = ra;
		return true;
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());


		// 최소신장트리는 꼭 엣지 - 리스트로 푼다
		List<Edge> list = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());

				// i==j(자기자신), j<i(대칭 중복)은 버림
				
				if(j <= i) {
					continue;
				}
				
				list.add(new Edge(i, j, w));
			}
		}


		// 비용 오름차순으로 정렬(매우 중요)
		Collections.sort(list);

	   // 유니온 파인드용 대표 노드 배열 초기화
		parents = new int[N];

		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}


		long answer = 0; // 비용
		int cnt = 0; // 간선의 개수는 정점 - 1이 되어야한다.

		for(Edge e : list) {
			// 둘이 다른 그룹이라면 --> 이어주고 MTS에 추가
			if(union(e.a, e.b)) {
				answer = answer + e.cost;
				cnt++;
			}
			if(cnt == N - 1) {
				break;
			}
		}

		System.out.println(answer);

	}
}
