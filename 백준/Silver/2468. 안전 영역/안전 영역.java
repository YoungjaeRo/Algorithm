
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];


		int maxH = 0; // 지도에서 최고 높이
		// 입력값 받아서 배열 초기화
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]);
			}

		}

		int answer = 0;

		// 비의 양을 0 ~ maxH까지 시뮬레이션
		for(int h = 0; h <= maxH; h++) {
			visited = new boolean[N][N];

			int safeCount = 0;    // 현재 h의 높이에서 안전영역의 개수

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > h && !visited[i][j]) {
						bfs(i, j, h);
						safeCount++;
					}
				}
			}

			answer = Math.max(answer,safeCount);
		}

		System.out.println(answer);

	}

	static void bfs(int i, int j, int h) {
		Queue<int[]> q = new LinkedList<>();
		visited[i][j] = true;
		
		q.offer(new int[]{i, j}); // 꼭 받아온 좌표를 큐에 넣고 시작하기 ㅇㅇ

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int x = cur[0];
			int y = cur[1];

			for(int a = 0; a < 4; a++) {
				int nx = x + dr[a];
				int ny = y + dc[a];

				// 범위 밖 검증
				if(nx < 0 || nx >= N || ny < 0 || ny >=N) {
					continue;
				}

				if(visited [nx][ny] || map[nx][ny] <= h) {
					continue;
				}

				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
}
