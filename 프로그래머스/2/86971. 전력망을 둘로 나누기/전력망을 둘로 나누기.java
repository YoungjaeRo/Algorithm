import java.util.*;
import java.io.*;

class Solution {
   public int solution(int n, int[][] wires) {
		// 인접리스트 (트리 그조를 표햔 할땐, 필수임)
		List<Integer>[] g = new ArrayList[n+ 1];

		for(int i = 1; i <= n; i++) {
			g[i] = new ArrayList<>();

		}

		for(int[] w : wires) {
			int a = w[0];
			int b = w[1];

			g[a].add(b);
			g[b].add(a);

		}

		int answer = Integer.MAX_VALUE;

		// 각 간선을 하나씩 끊는다
		for(int[] w : wires) {
			int a = w[0];
			int b = w[1];

			// a 쪽 컴포넌트 크기를 세기 (a-b) 간선은 무시 (a와 b의 간선을 끊었다고 가정함)
			int sizeA = bfsCount(n, g, a, a, b);


			// 끊어진 두 덩어리의 차이 = n - 2 * sizeA
			int diff = Math.abs(n - 2 * sizeA);

			answer = Math.min(answer, diff);
		}

		return answer;
	}


	// (banU - banV) 간선을 "건너지 않도록" 막고 start에서 연결된 정점 개수 세기
	public int bfsCount(int n, List<Integer>[] g, int start, int banU, int banV) {
		boolean[] visited = new boolean[n + 1];

		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;

		q.add(start);


		int cnt = 0;

		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt++;


			for(int nx : g[cur]) {
				// 이 한 줄이 포인트: 끊은 간선(양방향)을 건너지 않음
				if((cur == banU && nx == banV || cur == banV && nx == banU)) {
					continue;
				}
				
				if(!visited[nx]) {
					visited[nx] = true;
					q.add(nx);
				}
			}
		}
		
		return cnt;

	}
}