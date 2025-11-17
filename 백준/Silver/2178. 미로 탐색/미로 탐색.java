import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	static int[][] map;
	static int[][] dist; // 해당 칸까지의 최단 거리

	static boolean[][] visited;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];

		visited = new boolean[N][M];

		// 그래프에 숫자 정보 입력함
		for(int i = 0; i < N; i++) {
			String numbers = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = numbers.charAt(j) - '0';
			}
		}

		bfs(0, 0);

		System.out.println(dist[N-1][M-1]);
	}

	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		visited[x][y] = true;
		dist[x][y] = 1;

		q.offer(new int[]{x, y});

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];

			for(int i = 0; i < 4; i++) {
				int nX = curX + dx[i];
				int nY = curY + dy[i];

				if(nX < 0 || nX >= N || nY < 0 || nY >= M) {
					continue;
				}

				if(visited[nX][nY] == false && map[nX][nY] != 0) {
					dist[nX][nY] = dist[curX][curY] + 1;
					visited[nX][nY] = true;
					q.offer(new int[] {nX, nY});
				}
			}
		}
	}
}