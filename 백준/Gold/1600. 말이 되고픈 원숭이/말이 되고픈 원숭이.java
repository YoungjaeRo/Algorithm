import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 동물원에서 막 탈출한 원숭이 한마리
	 *
	 * 말은 체스에서 나이트와 같은 이동을 한다
	 *
	 * 원숭이는 딱 K번까지만 말처럼 이동이 가능한다
	 * 그 외는 인접한 칸(동, 서, 남, 북)으로 이동이 가능함
	 * 원숭이는 여행을 떠나는데, 격자판의 맨 왼쪽위에서 시작해서
	 * 맨 오른쪽 아래까지 가야한디
	 *
	 * 맵 상으로는 0, 0 -- > (H - 1, W - 1)까지 이동함
	 *
	 * 최소한의 동작 -- > BFS
	 *
	 * 0은 평지, 1은 장애물을 뜻한다
	 */

	static class Node {
		int r;
		int c;
		int kUsed;
		int time;

		Node(int r, int c, int kUsed, int dist) {
			this.r = r;
			this.c = c;
			this.kUsed = kUsed;
			this.time = dist;
		}
	}

	// 말의 이동
	static int[] xh = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] yh = {-1, 1, -2, 2, -2, 2, -1, 1};

	// 원숭이(상하좌우) 이동
	static final int[] dr4 = {-1, 1, 0, 0};
	static final int[] dc4 = {0, 0, -1, 1};

	static int K;
	static int W;
	static int H;

	static int[][] map;

	// kUsed, x, y
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];

		// 그래프 정보 주입 (0은 평지, 1은 장애물)
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
		visited = new boolean[K + 1][H][W];

		visited[0][0][0] = true;
		q.offer(new Node(0, 0, 0, 0));

		while(!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.r == H - 1 && cur.c == W - 1) {
				return cur.time;
			}

			// 원숭이처럼 인접 이동
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr4[d];
				int nc = cur.c + dc4[d];

				//  범위 검증하가
				if(!inRange(nr, nc)) {
					continue;
				}

				// 이미 방문한 것인지 검증
				if(visited[cur.kUsed][nr][nc]) {
					continue;
				}

				// 벽이면 이동 못함
				if(map[nr][nc] == 1) {
					continue;
				}

				visited[cur.kUsed][nr][nc] = true;
				q.offer(new Node(nr, nc, cur.kUsed, cur.time + 1));
			}


			// 말처럼 이동
			if(cur.kUsed < K) {

				int nextK = cur.kUsed+ 1;

				for(int d = 0; d < 8; d++) {
					int nr = cur.r + xh[d];
					int nc = cur.c + yh[d];

					// 범위확인부터
					if(!inRange(nr, nc)) {
						continue;
					}

					if(visited[nextK][nr][nc]) {
						continue;
					}


					if(map[nr][nc] == 1) {
						continue;
					}

					visited[nextK][nr][nc] = true;
					q.offer(new Node(nr, nc, nextK, cur.time + 1));
				}
			}
		}

		// BFS가 끝났는데도, 변화가 없다면 -1을 출력
		return -1;


	}

	static boolean inRange(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	}
}
