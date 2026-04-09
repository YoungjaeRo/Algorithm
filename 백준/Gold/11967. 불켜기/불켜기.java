import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;

	// 각 방에서 켤 수 있는 방들
	static List<int[]>[][] switches;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static boolean[][] visited; // 방문 여부
	static boolean[][] light; // 불 켜짐 여부


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		switches = new ArrayList[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		light = new boolean[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				switches[i][j] = new ArrayList<>();
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			switches[x][y].add(new int[]{a, b});
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited[1][1] = true;
		light[1][1] = true;
		q.offer(new int[]{1, 1});

		// 시작점은 불이 켜져 있음
		int count = 1;

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int x = cur[0];
			int y = cur[1];


			// 1. 현재 방에서 켤 수 있는 모든 방의 불 켜기
			for(int[] next : switches[x][y]) {
				int a = next[0];
				int b = next[1];

				// 아직 불이 켜져 있지 않다면
				if(!light[a][b]) {
					 light[a][b] = true;
					 // 켤수 있는 곳 + 1
					 count++;

					 // 새로 켜진 방이, 이미 방문한 방과 인접해있다면,
					for(int d = 0; d < 4; d++) {
						int nx = a + dx[d];
						int ny = b + dy[d];

						if(nx < 1 || ny < 1 || nx > N || ny > N) {
							continue;
						}

						// 이미 방문한적이 있다면, 해당 스위치가 켜진 곳으로도 이동이 가능
						if(visited[nx][ny]) {
							visited[a][b] = true;
							q.offer(new int[]{a, b});
							break;
						}
					}
				}
			}


			// 2. 현재 방에서 인접한 켜진 방으로 이동
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if(nx < 1 || ny < 1 || nx > N || ny > N) {
					continue;
				}

				// 불이 꺼져 있으면 이동이 불가
				if(!light[nx][ny]) {
					continue;
				}

				if(!visited[nx][ny]) {
					visited[nx][ny] = true;

					q.offer(new int[]{nx, ny});
				}
			}
		}
		return count;

	}
}
