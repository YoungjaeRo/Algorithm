import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;

	static int[][] map;
	static int[][] dist;

	static boolean[][] visited;

	// 상, 하, 좌, 우
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;

		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];
		visited = new boolean[N][M];


		//  공백 없이 붙은 문자열을 입력 받음 ㅇㅇ
		for(int i = 0; i < N; i++) {
			String numbers = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = numbers.charAt(j) - '0';
			}
		}

		System.out.println(bfs());

	}

	static int bfs() {
		ArrayDeque<Point> q = new ArrayDeque<>();
		visited[0][0] = true;
		dist[0][0] = 1; // 시작 칸 포함함

		q.offer(new Point(0, 0));

		while(!q.isEmpty()) {

			Point cur = q.poll();
			
			if(cur.r == N - 1 && cur.c == M - 1) {
				return dist[cur.r][cur.c];
			}

			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				if(map[nr][nc] == 0 || visited[nr][nc]) {
					continue;
				}

				visited[nr][nc] = true;
				dist[nr][nc] = dist[cur.r][cur.c] + 1; // 한칸을 이동했기 때문에

				q.offer(new Point(nr, nc));
			}

		}

		return 0;
	}
}
