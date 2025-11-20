import java.util.*;
import java.io.*;

public class Main {
	static int N;

	static int M;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int [][] map;
	static boolean[][] visited;
	static int[][] dist;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int [N][M];

		visited = new boolean[N][M];


		dist = new int[N][M];

		dist[0][0] = 1;

		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0;  j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		bfs(0, 0);

		System.out.println(dist[N -1][M-1]);

	}

	static void bfs(int r, int c) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});

		visited[r][c] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int cr = cur[0];
			int cc = cur[1];

			for(int i = 0; i < 4; i++) {
				int nr = cr + dx[i];
				int nc = cc + dy[i];

				if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {

					dist[nr][nc] = dist[cr][cc] + 1;
					visited[nr][nc] = true;

					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}
