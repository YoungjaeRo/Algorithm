import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static int[][] map;

	static int[][][] dist;
	static boolean[][][] visited;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		dist = new int [2][N][M];
		visited = new boolean [2][N][M];

		// 그래프 정보 입력
		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();

		// 시작점의 거리와 방문 여부 처리
		dist[0][0][0] = 1;
		visited[0][0][0] = true;

		q.offer(new int[]{0, 0, 0});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int b = cur[0];
			int cx = cur[1];
			int cy = cur[2];

			// 도착하면 바로 거리를 리턴
			if(cx == N - 1 && cy == M - 1) {
				return dist[b][cx][cy];
			}


				for(int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					// 범위 검증
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}

					// 빈칸이면 그대로 이동
					if(map[nx][ny] == 0) {
						if(!visited[b][nx][ny]) {

							visited[b][nx][ny] = true;

							dist[b][nx][ny] = dist[b][cx][cy] + 1;

							q.offer(new int[]{b, nx, ny});
						}

						// 빈칸이 아니어서 부숴야함 & 부순 횟수가 아직 1회 미만
					} else {
						if(b < 1 && !visited[b + 1][nx][ny]) {
							visited[b + 1][nx][ny] = true;

							dist[b + 1][nx][ny] = dist[b][cx][cy] + 1;

							q.offer(new int[]{b+ 1, nx, ny});
						}
					}

				}
		}
		return -1;
	}
}
