import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] map;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new char[N][N];


		// 그래프 값 입력 (R, B, G)
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		// 적록색약인(빨강, 초록) & 일반인이 보는 구역
		visited = new boolean[N][N];
		int normal = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					normal++;
				}

			}
		}


		// 새롭게 시작하기 때문에, visited 배열 초기화 해주기
		visited = new boolean[N][N];
		int weak = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					Sbfs(i, j);
					weak++;
				}

			}
		}

		System.out.println(normal + " " + weak);

	}

	// 정상인 시점 bfs
	static void bfs(int i, int j) {

		ArrayDeque<int[]> q = new ArrayDeque<>();

		visited[i][j] = true;

		q.offer(new int[]{i, j});

		while(!q.isEmpty()) {

			int[] cur = q.poll();

			int x = cur[0];
			int y = cur[1];

			char curColor = map[x][y];

			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];


				// 범위 밖이면,
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				// 이미 방문한 경로이면
				if(visited[nx][ny]) {
					continue;
				}

				char newColor = map[nx][ny];

				// 같은 색깔의 구역이라면,
				if(curColor == newColor) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}

			}
		}
	}


	static void Sbfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		visited[i][j] = true;

		q.offer(new int[]{i, j});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int x = cur[0];
			int y = cur[1];

			char curColor = map[x][y];

			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				// 범위 밖이면,
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				// 이미 방문한 경로이면
				if(visited[nx][ny]) {
					continue;
				}

				char newColor = map[nx][ny];

				// 빨간색과 초록색을 하나로 묶기
				if((curColor == 'R' && newColor == 'G') || (curColor == 'G' && newColor == 'R') || (curColor == 'R' && newColor == 'R') || (curColor == 'G' && newColor == 'G')) {
					visited[nx][ny] = true;
					q.offer(new int[]{nx, ny});

				} else if(curColor == newColor) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}

	}
}
