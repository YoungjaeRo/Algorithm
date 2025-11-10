import java.util.*;
import java.io.*;

public class Main {
	static int N; // 행
	static int M; // 열

	static int[][] map;
	static boolean[][] visited;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int count;

	static int max;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	StringTokenizer st = new StringTokenizer(br.readLine());

	N = Integer.parseInt(st.nextToken());
	M = Integer.parseInt(st.nextToken());

	map = new int[N][M];
	visited = new boolean[N][M];


	// 그래프 초기화
	for(int i = 0; i < N; i++) {
		st = new StringTokenizer(br.readLine());

		for(int j = 0; j < M; j++) {
			map[i][j] = Integer.parseInt(st.nextToken());

		}
	}

	// 탐색하면서, 시작점 찾기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					int area = bfs(i, j);
					count++;

					if(area > max) {
						max = area;
					}
				}
			}
		}

		System.out.println(count);
		System.out.println(max);

	}

	static int bfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		visited[i][j] = true;
		q.offer(new int[]{i, j});

		int area = 1;

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int r = cur[0];
			int c = cur[1];

			for(int d = 0; d < 4; d++) {
				int nx = r + dx[d];
				int ny = c + dy[d];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if(map[nx][ny] == 0 || visited[nx][ny]) {
					continue;
				}

				visited[nx][ny] = true;
				q.offer(new int[]{nx, ny});
				area++;
			}
		}

		return area;
	}
}
