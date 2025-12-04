import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 벽은 한개까지 부시기 가능함
	 * (1,1)에서 N,M까지 이동하는데, 최단 거리를 구해야한다.
	 * 시작하는 칸과 끝나는 칸까지 포한해서 최단 거리의 길이를 구해야한다
	 * 벽은 상, 하, 좌 우
	 * 0 은 이동가능한 칸, 1은 불가능
	 */

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N; // 세로 (행)
	static int M; // 가로  (열)

	static int[][] map;

	static boolean[][][] visited; // 마지막 배열에서 벽을 부쉈는지, 아닌지 여부 확인
	static int[][][] dist; // 마지막 배열에서 벽을 부쉈는지, 아닌지 여부 확인


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		visited = new boolean [2][N][M];

		dist = new int [2][N][M];

		// 그래프에 값을 다 넣어줌

		for(int i = 0; i < N; i++) {
			String line = br.readLine(); // 문자열로 읽어오기 때문에, - '0' 필요함

			for(int j = 0; j < M; j++) {
				int num = line.charAt(j) - '0';
				map[i][j] = num;
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0}); // broken = 0, x = 0, y = 0

		visited[0][0][0] = true;
		dist[0][0][0] = 1;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int broken = cur[0];
			int x = cur[1];
			int y = cur[2];

			if(x == N - 1 && y == M - 1) {
				return dist[broken][x][y];
			}

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				// 빈칸일때
				if(map[nx][ny] == 0) {
					if(!visited[broken][nx][ny]) {
						visited[broken][nx][ny] = true;
						dist[broken][nx][ny] = dist[broken][x][y] + 1;
						q.offer(new int[]{broken, nx, ny}); // broken 그대로 유지
					}
				}

				// 벽일때
				else {
					if(broken == 0 && !visited[1][nx][ny]) {
						visited[1][nx][ny] = true;
						dist[1][nx][ny] = dist[broken][x][y] + 1;
						q.offer(new int[]{1, nx, ny});
					}
				}
			}
		}
    return -1;
	}
}
