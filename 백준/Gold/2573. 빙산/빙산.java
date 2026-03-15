import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 빙상은 2차원 배열에 양수로 저장됨
	 * 바다에 해당하는 칸은 0이 저장된다
	 *
	 * 바닷물에 많이 접해있는 부분에서 더 빨리 줄어듦
	 *
	 * 일년마다, 동서남북 방향으로 붙이었는 0이 저장된 칸의 개수만큼 줄어든다
	 *
	 */

	static int N;
	static int M;

	static int[][] map;

	// 각 주변에 0(바다)가 얼마나 있는지 저장해놓을 배열
	static int[][] surround;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}

		int year = 0;

		while(true) {
			int count = countIcebergs();

			if(count >= 2) {
				System.out.println(year);
				return;
			}

			if (count == 0) {
				System.out.println(0);
				return;
			}
			
			// 그게 아니라면 한번 더 녹이기
			melt();
			year++;
			
		}
	}

	static int countIcebergs() {
		visited = new boolean[N][M];

		int count = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		return count;
	}

	static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;
		
		q.offer(new int[]{x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if(!visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new int[]{nx, ny});
				}
			}
		}

	}
	
	
	static void melt() {
		surround = new int[N][M];
		
		// 바다가 아닌 지역의 주변 바다 개수 세기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 바다가 아닐때, 주변 바다의 개수를 카운팅 함
				if(map[i][j] > 0) {
					int cnt = 0;
					
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
							continue;
						}
						
						if(map[nx][ny] == 0) {
							cnt++;
						}
					}
					
					surround[i][j] = cnt;
				}
			}
		}
		
		// 이제 녹이기 시작
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0) {
					map[i][j] = map[i][j] - surround[i][j];
				}
				
				// 음수가 되면 그냥 0으로 처리
				if(map[i][j] < 0) {
					map[i][j] = 0;
				}
			}
		}
	}
}
