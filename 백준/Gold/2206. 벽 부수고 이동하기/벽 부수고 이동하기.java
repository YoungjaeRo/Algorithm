import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	static int[][] map;

	// 벽을 부쉈는지 여부까지 파악하기 위해서 하나의 열을 더 둔다
	static int[][][] dist;
	static boolean[][][] visited;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M][2];

		visited = new boolean[N][M][2];

		// 그래프 정보 주입
		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				int num = line.charAt(j) - '0';
				map[i][j] = num;
			}
		}

		bfs(0, 0);
	}

	static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[x][y][0] = true;
		dist[x][y][0] = 1;

		q.offer(new int[]{x, y, 0});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int cx = cur[0];
			int cy = cur[1];
			int broke = cur[2];

			if(cx == N - 1 && cy == M - 1) {
				System.out.println(dist[cx][cy][broke]);
				return;
			}

			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				// 범위 체크부터
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				// 벽을 부술 필요가 없을때
				if(map[nx][ny] == 0) {

					// 그리고 방문한 적도 없고
					if(!visited[nx][ny][broke]) {
						visited[nx][ny][broke] = true;

						dist[nx][ny][broke] = dist[cx][cy][broke] + 1;

						q.offer(new int[] {nx, ny, broke});
					}

					// 벽을 부서야 한다면, broke의 값이 그래도 1 미만이어야 한다.
				} else {
					if(broke < 1 && !visited[nx][ny][broke + 1]) {
						visited[nx][ny][broke + 1] = true;

						dist[nx][ny][broke + 1] = dist[cx][cy][broke] + 1;

						q.offer(new int[]{nx, ny, broke + 1});
					}
				}

			}
		}

		System.out.println(-1);

	}
}
