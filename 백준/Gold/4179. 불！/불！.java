import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출이 가능하다
	 */

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int R; // 미로 행의 개수
	static int C; // 미로 열의 개수

	static char[][] map;

	// 불과 사람이 번지는 시간을 담아두는 배열
	static int[][] fireTime;
	static int[][] personTime;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

	R = Integer.parseInt(st.nextToken());
	C = Integer.parseInt(st.nextToken());

	map = new char [R][C];
	fireTime = new int [R][C];
	personTime = new int [R][C];

	// 방문 검증때문에 -1로 초기화
	for(int i = 0; i < R; i++) {
		Arrays.fill(fireTime[i], -1);
		Arrays.fill(personTime[i], -1);
	}


	Queue<Node> fireQ = new ArrayDeque<>();
	Queue<Node> personQ = new ArrayDeque<>();

	// 그래프 정보 입력
	for(int i = 0; i < R; i++) {
		String line = br.readLine();

		for(int j = 0; j < C; j++) {

			char cur = line.charAt(j);

			map[i][j] = cur;

			if(cur == 'F') {
				fireQ.offer(new Node(i,j));
				fireTime[i][j] = 0;

			} else if(cur == 'J') {
				personQ.offer(new Node(i, j));
				personTime[i][j] = 0;
			}
		}
	}


	int result = bfs(fireQ, personQ);

	if(result == -1) {
		System.out.println("IMPOSSIBLE");
	} else {
		System.out.println(result);
	}

	}

	static int bfs(Queue<Node> fireQ, Queue<Node> personQ) {

		// 불먼저
		while(!fireQ.isEmpty()) {
			Node cur = fireQ.poll();

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				// 범위 검증
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				// 벽에는 불이 날 수 없음 ㅇㅇ
				if(map[nx][ny] == '#') {
					continue;
				}

				// 이미 불이 붙은 곳이라면 스킵
				if(fireTime[nx][ny] != -1) {
					continue;
				}

				fireTime[nx][ny] = fireTime[cur.x][cur.y] + 1;
				fireQ.offer(new Node(nx, ny));
			}

		}



		// 이제 사람 이동시킴
		while(!personQ.isEmpty()) {

			Node cur = personQ.poll();
			int cx = cur.x;
			int cy = cur.y;
			int ct = personTime[cur.x][cur.y];

			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				int nt = ct + 1;

				// 외각이면 탈출
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					return nt;
				}

				// 이미 방문했으면 스킵
				if(personTime[nx][ny] != -1) {
					continue;
				}

				// 벽이면 스킵
				if(map[nx][ny] == '#') {
					continue;
				}

				// 불이 더 빨리 붙거나, 이미 붙은곳이면 스킵
				if(fireTime[nx][ny] != -1 && fireTime[nx][ny] <= nt) {
					continue;
				}

				personTime[nx][ny] = nt;
				personQ.offer(new Node(nx, ny));

			}
		}


		// 끝까지 못나가면 -1
		return -1;

	}
}
