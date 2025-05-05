import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		Queue<int[]> q = new LinkedList<>();

		// 입력 처리 + 익은 토마토 위치(1)을 큐에 넣기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}

		// BFS 실행
		BFS(q);

		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++){
				if(map[i][j] == 0) {
					System.out.println(-1);
					return; // 악익은 토마토(0)이 하나라도 남아있으면 -1을 앞에서 출력하고, 더 이상 아레 코드를 실행하지 않게 즉지 프로그램을 끝낸다.
				}
				result = Math.max(result, map[i][j]);
			}
		}

		System.out.println(result - 1);

	}

	public static void BFS(Queue<int[]> q) {
		while(!q.isEmpty()) {
			int now[] = q.poll();
			int x = now[0];
			int y = now[1];

			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 범위 체크 : 각 좌표가 0이상인지와 가로세로 보다 작은지
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(map[nx][ny] == 0) {
						map[nx][ny] = map[x][y] + 1;
						q.offer(new int[]{nx, ny});
					}

				}
			}
		}

	}



}
