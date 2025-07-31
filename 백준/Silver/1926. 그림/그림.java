import java.util.*;
import java.io.*;

public class Main {

	static int n; // 세로 (행)
	static int m; // 가로 (열)

	static int[][] map;
	static boolean[][] visited;

	// 동, 서, 남, 북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	static int count = 0;
	static int maxArea = 0;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		// 입력 받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 전체 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					int area = bfs(i, j);
					count++;
					maxArea = Math.max(maxArea, area);
				}
			}
		}

		System.out.println(count);
		System.out.println(maxArea);
	}

	static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;

		int area = 1;  // 시작점 포함

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;

				if (map[nx][ny] == 1 && !visited[nx][ny]) {
					q.offer(new Point(nx, ny));
					visited[nx][ny] = true;
					area++;
				}
			}
		}

		return area;
	}
}
