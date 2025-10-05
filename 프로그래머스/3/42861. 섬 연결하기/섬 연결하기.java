import java.io.*;
import java.util.*;


class Solution {
  static class DSU {
		int[] parent; // parent[x] = x의 부모 (루트면 자기자신)

		DSU(int n) {
			parent = new int[n];

			for(int i = 0; i < n; i++) {
				//처음엔 모두 자기 자신이 부모임
				parent[i] = i;
			}
		}

		int find(int x) {
			if(parent[x] !=  x) {
				parent[x] = find(parent[x]);
			}
			
			return parent[x];
		}

		/**
		 * a가 속한 집합과 b가 속한 집합을 합침.
		 * 이미 같은 집합이면(루트 같음) false, 합쳐졌으면 true 반환.
		 */

		boolean union(int a, int b) {
			int ra = find(a);
			int rb = find(b);

			if(ra == rb) {
				return false;
			}

			parent[rb] = ra; // 한쪽의 대표 노드값을 다른 한쪽에 넣어줌
			return true;
		}

		}
	public int solution(int n, int[][] costs) {
		/**
		 * 최소 신장 트리 MST 문제인다. 그래서 크루스칼 알고리즘(정렬, 유니온파인드)로 문제를 해결한다
		 */
	// 1. 간선을 비용 기준으로 오름차순으로 정렬
		Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

		DSU dsu = new DSU(n);
		int picked = 0;  // 선택된 간선의 수 MST는 정확히 n-1 개

		int total = 0; // 총 비용

		// 2. 낮은 비옹부터 간선 채택 시도
		for(int[] e : costs) {
			int u = e[0];
			int v = e[1];
			int w = e[2];

			// u,v 가 서로 다른 집합일때만 채택(같으면 싸이클이기 때문임)

			if(dsu.union(u, v)) { // 둘의 대표 노드가 다르면 -- > 사이클이 아니라면
				total = total + w; // 해당 간선 값을 더해준다
				picked++; // 간석의 수도 + 1 증가

				// 3. 간선 n-1게 체우면 종료 (MST는 간선이 무조건 n-1개가 최대임 ㅇㅇ)
				if(picked == n-1) {
					break;
				}
			}

		}

		return total;
	}
}