import java.util.*;

class Solution {
   /**
	 * Programmers 1844 - 게임 맵 최단거리
	 * - 격자에서 (0,0) -> (n-1,m-1)까지의 최소 이동 칸 수를 구한다.
	 * - 1: 이동 가능, 0: 벽
	 * - 상/하/좌/우 네 방향으로만 이동
	 * - 최단거리는 BFS로 계산 (가중치가 모두 동일(=1)이기 때문)
	 */

	static class Point {
		int r; // 행
		int c; // 열

		int dist; // 시작점으로부터 현재 칸의 거리

		Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

	}

	public int solution(int[][] maps) {
		int n = maps.length; // 세로
		int m = maps[0].length; // 가로

		// 시작점이나 도착점이 벽인 경우
		if (maps[0][0] == 0 || maps[n - 1][m - 1] == 0) {
			return -1;
		}

		// 방문 여부
		boolean[][] visited = new boolean[n][m];

		// 네 방향 이동
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		// BFS 큐 선언
		Queue<Point> q = new LinkedList<>();

		q.offer(new Point(0, 0, 1)); // 0,0에서 시작하고 시작점도 움직인것이기 떄문에 1 추가
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			// 도착 지검에 도닥하면 즉지 거리 반환
			if(cur.r == n-1 && cur.c == m-1) {
				return cur.dist;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dx[i];
				int nc = cur.c + dy[i];

				// 범위 밖
				if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
					continue;
				}

				// 이미 방문한 이력이 있다면
				if (visited[nr][nc] || maps[nr][nc] == 0) {
					continue;
				}

				// 다음 경로로 방문 처리 및 큐에 추가
				visited[nr][nc] = true;
				q.offer(new Point(nr, nc, cur.dist + 1));
			}
		}
		// 도착 불가한 경우
		return -1;
	}
}