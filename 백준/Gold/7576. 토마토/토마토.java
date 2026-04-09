import java.io.*;
import java.util.*;

public class Main {

	static int M;
	static int N;

	static int[][] map;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// BFS 를 위한 큐 선언
		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}

			// 토마토 익히기 시작
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int cx = cur[0];
				int cy = cur[1];

				for (int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}

					// 익지 않은 토마토만 익힘
					if (map[nx][ny] == 0) {
						map[nx][ny] = map[cx][cy] + 1;
						q.offer(new int[] {nx, ny});
					}
				}
			}

			// 한칸이라도 익히지 못한 토마토가 있으면 -1을 출력, 이미 다 익어있으면 0을 출력
			int max = 1;

			for(int a = 0; a < N; a++) {
				for(int b = 0; b < M; b++) {

					if(map[a][b] == 0) {
						System.out.println(-1);
						return;

					} else if (map[a][b] > max){
						max = map[a][b];
					}
				}
			}

			System.out.println(max == 1 ? 0 : max - 1);

		}
	}
