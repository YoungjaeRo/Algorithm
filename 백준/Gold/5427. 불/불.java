import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 빈 공간과 벽으로 이루어져 있음
	 * 불은 동서남북 방향으로 퍼짐
	 * 벽에는 불이 붙지 않음 ㅇㅇ
	 *
	 * 상근이는 벽을 통과할 수 없으며, 불이 옮겨진 칸, 또는 불이 붙으려는 칸으로 이동이 불가하다
	 *
	 * 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다
	 *
	 * 빌딩의 지도가 주어주고, 가장 빨리 탈출할 수 있는 탈출구 == 최단시간(BFS)
	 *
	 */

	static int T;
	static int w; // 빌딩의 너비 (열)
	static int h; // 높이(행)

	static char[][] map;
	static boolean[][] visited; // 상근이 방문 체크

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new char[h][w];
			visited = new boolean[h][w];

			// 불과 상근이의 위치를 각각의 큐에 따로 저장
			ArrayDeque<int[]> fire = new ArrayDeque<>();
			ArrayDeque<int[]> man = new ArrayDeque<>();


			// 지도 정보 주입하기
			for(int i = 0; i < h; i++) {
				String line = br.readLine();

				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					// 불이면 불 큐에 삽입
					if(map[i][j] == '*') { // 불이 퍼지기 시작하는 시작점
						fire.offer(new int[]{i, j});

					} else if(map[i][j] == '@') { // 상근이 시작점
						man.offer(new int[]{i, j});
						visited[i][j] = true;
						map[i][j] = '.'; // 상근이 칸도 그냥 빈칸으로 침
					}
				}
			}

			int ans = bfs(fire, man);

			if(ans == -1) {
				sb.append("IMPOSSIBLE").append("\n");
			} else {
				sb.append(ans).append("\n");
			}

		}
		
		// 꼭 테케 밖에서 출력
		System.out.println(sb.toString());

	}

	static int bfs(ArrayDeque<int[]> fire, ArrayDeque<int[]> man) {
		int time = 0; // 몇초가 지났는지,

		while(!man.isEmpty()) {
			time++; // 시간은 흐르기 시작

			/**
			 *  불 먼저 확산한다 (지금 초에 존재하던 불들만)
			 */

			int fireSize = fire.size();

			for(int i = 0; i < fireSize; i++) {

				int[] cur = fire.poll();

				int cx = cur[0];
				int cy = cur[1];

				for(int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					// 범위 검증
					if(nx < 0 || ny < 0 || nx >= h || ny >= w) {
						continue;
					}

					// 벽이거나, 이미 불이 붙은 불이라면 continue;
					if(map[nx][ny] == '#' || map[nx][ny] == '*') {
						continue;
					}

					// 불확산
					map[nx][ny] = '*';
					fire.offer(new int[]{nx, ny});
				}

			}

			/**
			 * 불 확산 (곧 붙을 예정인 곳도 이동 불가이기 때문에) 후, 상근이 이동
			 */

			int manSize = man.size();

			for(int i = 0; i < manSize; i++) {
				int[] cur = man.poll();
				int cx = cur[0];
				int cy = cur[1];

				for(int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					// 격자 밖으로 나가면 탈출 성공 (현재 time이 정답)
					if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
						return time;
					}

					if(visited[nx][ny]) {
						continue;
					}

					// 벽이거나 불이면 이동 불가
					// (불이 먼저 번졌기 때문에, 이번 초에 불이 될 칸도 이미 '*' 처리되어 있음)
					if (map[nx][ny] == '#' || map[nx][ny] == '*') {
						continue;
					}

					visited[nx][ny] = true;
					man.offer(new int[] {nx, ny});
				}
			}
		}

		//끝까지 못빠져나가면 -1
		return -1;
	}
}
