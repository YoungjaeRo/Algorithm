import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 일단 가중치가 다 1이기 때문에, BFS로 해당 문제를 풀면 된다
	 * 그리고 해당 문제에선 층, 열, 행이 주어졌기 때문에 3차원 배열로 문제를 해결한다
	 *
	 * 방향도 동, 서, 남, 북 그리고 상, 하 총 6개의 옵션
	 *
	 * 벽 # 못가고,
	 * 빈칸 . 와 E 가능
	 *
	 */

	/**
	 * dist 가 있으면, 다 -1로 초기화 해서 방문 여부를 체크, 노드에도 따로 거리값을 저장하지 않음
	 * dist가 없으면, 무조건 visited 배열이 있어야 하고, 따로 거리값도 저장해놔야한다.
	 */

	static class Pos {
		int z;
		int x;
		int y;

		Pos(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}

	static final int[] dz = {0, 0, 0, 0, -1, 1};
	static final int[] dx = {0, 0, -1, 1, 0, 0};
	static final int[] dy = {-1, 1, 0, 0, 0, 0};

	static int L,R,C;
	static char[][][] map;
	static int[][][] dist; // visited 배열 대신 거리 배열을 사용 (-1은 아직 방문하지 않은 경우)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();


		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			// 종료 조건 필수
			if(L == 0 && R == 0 && C == 0) {
				break;
			}

			map = new char[L][R][C];
			dist = new int[L][R][C];

			// 거리 배열에 다 -1로 채우기 (방문 + 거리)
			for(int z = 0; z < L; z++) {
				for(int x = 0; x < R; x++) {
					Arrays.fill(dist[z][x], -1);
				}
			}

			int sz = 0, sx = 0, sy = 0;
			int ez = 0, ex = 0, ey = 0;

			// 그래프 정보 입력받기

			// 입력 : 층 L개
			for(int z = 0; z < L; z++) {

				// 각 층은 R줄
				for(int x = 0; x < R; x++) {
					String line = br.readLine();

					for(int y = 0; y < C; y++) {
						char ch = line.charAt(y);
						map[z][x][y] = ch;


						// 시작점 과 끝점
						if(ch == 'S') {
							sz = z;
							sx = x;
							sy = y;

						} else if(ch == 'E') {
							ez = z;
							ex = x;
							ey = y;
						}
					}
				}

				// 층과 층 사이 빈줄 1개 버림
				br.readLine();

			}

			int ans = bfs(sz, sx, sy, ez, ex, ey);

			if(ans == -1) {
				sb.append("Trapped!\n");
			} else {
				sb.append("Escaped in ").append(ans).append(" minute(s).\n");
			}
		}

		System.out.println(sb);
	}

	/**
	 * dist 배열이 visited 역할도 같이 함
	 */
	static int bfs(int sz, int sx, int sy, int ez, int ex, int ey) {
		ArrayDeque<Pos> q = new ArrayDeque<>();

		// 시작점 세팅하기
		q.offer(new Pos(sz, sx, sy));
		dist[sz][sx][sy] = 0;

		while(!q.isEmpty()) {
			Pos cur = q.poll();

			// 도착했으면 그대로 출력
			if(cur.z == ez && cur.x == ex && cur.y == ey) {
				return dist[cur.z][cur.x][cur.y];
			}

			// 6방향으로 이동
			for(int d = 0; d < 6; d++) {
				int nz = cur.z + dz[d];
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				// 1.범위 체크
				if(nz < 0 || nz >= L || nx < 0 || nx >= R || ny < 0 || ny >= C) {
					continue;
				}

				// 2. 벽이지 여부 확인
				if(map[nz][nx][ny] == '#') {
					continue;
				}

				// 3. 이미 방문했으면 패스
				if(dist[nz][nx][ny] != -1) {
					continue;
				}

				dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
				q.offer(new Pos(nz, nx, ny));

			}
		}

		// 큐를 다 뽑고나서도 아무런 결과가 없으면 -1을 반환
		return -1;
	}
}
