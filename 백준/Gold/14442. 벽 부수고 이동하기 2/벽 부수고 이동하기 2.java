import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int K;

	static int[][] map;

	static int[][][] dist;
	static boolean[][][] visited;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[K  + 1][N][M];
		visited = new boolean[K + 1][N][M];


		// 그래프 정보 주입해주기
		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				int num = line.charAt(j) - '0';
				map[i][j]= num;
			}
		}

		int answer = bfs(0, 0, 0);

		System.out.println(answer);
	}

	static int bfs(int broke, int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		// 시작점도 거리로 쳐주기 때문에
		dist[0][0][0] = 1;
		visited[0][0][0] = true;

		q.offer(new int[]{0, 0, 0});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int curBroke = cur[0];
			int curX = cur[1];
			int curY = cur[2];

			// 도착하면 종료
			if(curX == N - 1 && curY == M - 1) {
				return dist[curBroke][curX][curY];
			}

			for(int d = 0; d < 4; d++) {
				int nextX = curX + dx[d];
				int nextY = curY + dy[d];

				// 1. 무조건 범위부터 확인
				if(outRange(nextX, nextY)) {
					continue;
				}

				// 일단 벽을 부수지 않고 그냥 이동을 할 경우
				if(map[nextX][nextY] == 0) {

					// 방문 여부 확인
					if(visited[curBroke][nextX][nextY]) {
						continue;
					}

					//방문처리
					visited[curBroke][nextX][nextY] = true;

					//거리 업데이트
					dist[curBroke][nextX][nextY] = dist[curBroke][curX][curY] + 1;
					q.offer(new int[]{curBroke, nextX, nextY});
				} else {
					// 벽을 부수고 이동해야할 경우

					// 벽을 부순 횟수가 K번 이상이라면 스킵
					if(curBroke >= K) {
						continue;
					}

					if(visited[curBroke + 1][nextX][nextY]) {
						continue;
					}

					// 방문처리
					visited[curBroke + 1][nextX][nextY] = true;

					//거리 업데이트
					dist[curBroke + 1][nextX][nextY] = dist[curBroke][curX][curY] + 1;

					q.offer(new int[]{curBroke + 1, nextX, nextY});
				}

			}

		}
		return -1;
	}

	static boolean outRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
