import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 처음 익어있는 토마토 좌표를 큐에 넣고 BFS를 실행한다
	 * 각 BFS 단계마다, 안익은 토마토의 값을 + 1로 업데이트 해주고 큐에 집어 넣음
	 */
	static int N;
	static int M;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];

		// BFS 를 실행할 큐
		Queue<int[]> q = new ArrayDeque<>();


		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					// 모든 시작점들은 다 큐에 넣어주기
					q.offer(new int[]{i, j});
				}
			}
		}

		// BFS 실행하기
		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int cx = cur[0];
			int cy = cur[1];

			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				// 범위 검증부터
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}

				// 상태가 0인것에 대해서만 탐색함
				if(map[nx][ny] == 0) {
					map[nx][ny] = map[cx][cy] + 1;
					q.offer(new int[]{nx, ny});
				}
			}
		}

		int max = 1;

		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {

				// 토마토가 모두 익지 못한다면, -1을 출력한다.

				if(map[i][j] == 0) {
					System.out.println(-1);
					return;

				} else if(map[i][j] > max) {
					max = map[i][j];
				}
			}
		}

		System.out.println(max == 1 ? 0 : max - 1);
	}


}
