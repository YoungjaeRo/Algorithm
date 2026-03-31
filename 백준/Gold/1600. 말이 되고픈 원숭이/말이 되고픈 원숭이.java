import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 말의 움직임으로 K 번까지 이동이 가능하고, 그 외의 이동은 인접한 곳으로만 이동이 가능함
	 *
	 * 2,2 기준점 기준
	 * 말이 이동 가능한 칸은
	 *
	 * 1 , 0
	 * 0, 1
	 *
	 * 0, 3
	 * 1, 4
	 *
	 * 3, 0
	 * 4, 1
	 *
	 * 3, 4
	 * 4, 3
	 */

	static int[] hx = {-1, -2, -2, -1, 1, 2, 1, 2};
	static int[] hy = {-2, -1, 1, 2, -2, -1, 2, 1};

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int K;
	static int W;
	static int H;

	static int[][] map;

	static boolean[][][] visited; // 행과 열 그리고 말로써의 이동 횟수까지
	static int[][][] dist;


	static class Node {
		int x;
		int y;
		int kTime;

		Node(int x, int y, int kTime) {
			this.x = x;
			this.y = y;
			this.kTime = kTime;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];

		// 0회도 따로 기록해야하기 때문에, K + 1칸으로 스케일링
		visited = new boolean[H][W][K + 1];
		dist = new int[H][W][K + 1];


		// 맵정보 입력 받기 0은 평지, 1은 장애물
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs();
		System.out.println(answer);
	}

	static int bfs() {
		Queue<Node> q = new ArrayDeque<>();

		visited[0][0][0] = true;
		q.offer(new Node(0, 0, 0));

		while(!q.isEmpty()) {
			Node n = q.poll();

			int cx = n.x;
			int cy = n.y;
			int curK = n.kTime;

			// 해당 지점에 도착했으면, 바로 리턴해주기
			if(cx == H - 1 && cy == W - 1) {
				return dist[cx][cy][curK];
			}

			// 원숭이 루트로 탐색 시작
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				// 범위 검증부터
				if(nx < 0 || ny < 0 || nx >= H || ny >= W) {
					continue;
				}

				// 이미 방문한적이 있다면
				if(visited[nx][ny][curK]) {
					continue;
				}

				// 벽으로 막혀있어도 이동 불가
				if(map[nx][ny] == 1) {
					continue;
				}

				visited[nx][ny][curK] = true;
				dist[nx][ny][curK] = dist[cx][cy][curK] + 1;
				q.offer(new Node(nx, ny, curK));
			}


			// 말로 이동하는 경로도 넣어주기
			if(curK < K) {

				for(int d = 0; d < 8; d++) {
					int nx = cx + hx[d];
					int ny = cy + hy[d];
					int nk = curK + 1;

					// 범위검증부터
					if(nx < 0 || ny < 0 || nx >= H || ny >= W) {
						continue;
					}

					// 이미방문한적이 있다면 스킵
					if(visited[nx][ny][nk]) {
						continue;
					}

					// 최종 도착지가 벽이라면 이동불가
					if(map[nx][ny] == 1) {
						continue;
					}

					visited[nx][ny][nk] = true;
					dist[nx][ny][nk] = dist[cx][cy][curK] + 1;
					q.offer(new Node(nx, ny, nk));
				}
			}

		}

		return -1;

	}
}
