import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 0은 이동할 수 있는 칸
	 * 1은 벽
	 *
	 * 벽을 K개까지 부수고 이동할 수 있음
	 */
	static int N;
	static int M;
	static int K;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static int[][] map;
	static boolean[][][] visited;

	static int[][][] dist;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		visited = new boolean[N][M][K + 1];
		dist = new int[N][M][K + 1];


		for(int i = 0; i < N; i++) {

			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		bfs(0, 0, 0);


	}

	static void bfs(int x, int y, int broke) {
		Queue<int[]> q = new ArrayDeque<>();

		visited[x][y][broke] = true;
		dist[x][y][broke] = 1;

		q.offer(new int[]{x, y, broke});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			 int cx = cur[0];
			 int cy = cur[1];
			 int curBroke = cur[2];

			 if(cx == N - 1 && cy == M - 1) {
				 System.out.println(dist[cx][cy][curBroke]);
				 return;
			 }


			 for(int d = 0; d < 4; d++) {

				 int nx = cx + dx[d];
				 int ny = cy + dy[d];

				 // 범주 검증부터
				 if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					 continue;
				 }

				 // 벽을 부술 필요가 없을때
				 if(map[nx][ny] != 1) {

					 // 방문한적도 없음
					 if(!visited[nx][ny][curBroke]) {
						 visited[nx][ny][curBroke] = true;

						 dist[nx][ny][curBroke] = dist[cx][cy][curBroke] + 1;
						 q.offer(new int[]{nx, ny, curBroke});
					 }
				 } else {
					 // 벽을 부숴야 할때,


					 // 이미 벽을 부술 수 있는 횟수를 다 사용했다면, 스킵
					 if(curBroke == K) {
						 continue;
					 }

					 int nextBroke = curBroke + 1;

					 if(!visited[nx][ny][nextBroke]) {
						 visited[nx][ny][nextBroke] = true;

						 dist[nx][ny][nextBroke] = dist[cx][cy][curBroke] + 1;

						 q.offer(new int[]{nx, ny, nextBroke});

					 }

				 }
			 }

		}

		// 끝까지 못가면
		System.out.println(-1);
	}
}
