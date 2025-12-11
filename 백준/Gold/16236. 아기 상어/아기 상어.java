import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int sharkR;
	static int sharkC;

	static int sharkSize = 2; // 상어 크기

	static int eatCount = 0; // 현재 상어가 먹은 물고기 수

	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		// 상어와 물고기 정보 입력함 상어는 9
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					sharkR = i;
					sharkC = j;
					map[i][j] = 0; // 상어가 있던 자리는 빈칸으로 처리

				}
			}
		}

		// 더이상 먹을 물고기가 없을때까지 반복함
		while (true) {
			// 현재 상어 위치에서 시작하는 BFS 거리배열 구하기
			int[][] dist = bfs();

			// 먹을 수 있는 물고기 중 가장 조건에 부합하는 애 찾기
			int fishR = -1;
			int fishC = -1;

			int minDist = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 도달 불가능한 칸이면 패스
					if (dist[i][j] == -1) {
						continue;
					}

					// 물고기가 있고 (1 ~ 6) , 상어보다 작아야 먹을 수 있음
					if (map[i][j] >= 1 && map[i][j] < sharkSize) {
						int d = dist[i][j];

						//  더 가까운 물고기면 갱신하기
						if (d < minDist) {
							minDist = d;
							fishR = i;
							fishC = j;
						}
						// 거리가 같으면, 더 위쪽, 더 왼쪽 우선
						else if (d == minDist) {
							// 더 위쪽이거나, 같은 행이어서, 더 왼쪽이라면,
							if (i < fishR || (i == fishR && j < fishC)) {
								fishR = i;
								fishC = j;
							}
						}
					}
				}
			}

			// 먹을 수 있는 물고기가 하나도 없으면, 종료
			if (fishR == -1) {
				break;
			}

			// 해당 물고기 칸으로 이동
			time = time + minDist; // 이동한 칸 수  = 걸린 시간
			sharkR = fishR;
			sharkC = fishC;

			//  물고기 먹기
			eatCount++;
			map[fishR][fishC] = 0; // 칸은 이제 빈칸

			// 상어 크기 증가 체그
			if (eatCount == sharkSize) {
				sharkSize++;
				eatCount = 0;
			}
		}

		System.out.println(time);
	}

	// (sharkR, sharkC)에서 시작하는 BFS로 각 칸까지의 최소 거리 구하기
	static int[][] bfs() {
		int[][] dist = new int[N][N];

		// -1로 초기화 --> 아직 방문 안한 칸이라는 뜻
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], -1);
		}

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sharkR, sharkC});
		dist[sharkR][sharkC] = 0;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for(int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];

				// 범위 밖이면 패스
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

				// 이미 방문한 칸이면, 패스
				if(dist[nr][nc] != -1) continue;

				if(map[nr][nc] > sharkSize) continue;

				dist[nr][nc] = dist[r][c] + 1;
				q.add(new int[] {nr, nc});
			}
		}

		return dist;
	}
}
