import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int M;

	static int[][] map;

	// 동서남북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};


	static class Point {
		int r;
		int c;
		 Point (int r, int c) {
			 this.r = r;
			 this.c = c;
		 }
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());


		// 그래프 초기화 ㅇㅇ
		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			String numbers = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = numbers.charAt(j) - '0'; // 문자열로 들어온 숫자를 int 형으로 반환하게 해줌
			}
		}

		int answer = bfs(); // 0,0에서 마지막 칸 까지 최단 칸수
		System.out.println(answer);

	}

	static int bfs() {
		int[][] dist = new int[N][M]; // 0이면 미방문, 그 외는 거리수 더하기
		Deque <Point> q = new ArrayDeque<>();

		//시작
		dist[0][0] = 1;
		q.offer(new Point(0, 0));

		while(!q.isEmpty()) {
			Point cur = q.poll();


			// 도착지에 왔다면, 거리를 변환
			if(cur.r == N - 1 && cur.c == M - 1) {
				return dist[cur.r][cur.c];
			}

			// 4방향 탐색 시작
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dx[i];
				int nc = cur.c + dy[i];


				// 밤위, 벽, 방문 여부 검사 ㄱㄱ
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				//벽과 이미 방문한 좌표는 방문 안함
				if(map[nr][nc] == 0 || dist[nr][nc] != 0) {
					continue;
				}

				dist[nr][nc] = dist[cur.r][cur.c] + 1;
				q.offer(new Point(nr, nc));

			}
		}
		return 0; // 도착 불가시
	}
}
