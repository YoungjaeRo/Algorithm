import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int K;

	static int[][] map;
	static int[][][][] dist;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		dist = new int[K + 1][N][M][2];


		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {

		Queue<int[]> q = new ArrayDeque<>();


		dist[0][0][0][0] = 1;

		q.offer(new int[]{0, 0, 0, 0});

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			int ck = cur[0];
			int cx = cur[1];
			int cy = cur[2];
			int ct = cur[3];

			// 도착했으면, 거리 리턴해주기
			if(cx == N - 1 && cy == M -1) {
				return dist[ck][cx][cy][ct];
			}

			// 낮과 밤 스위치
			int nt = 1 - ct;


			// 기다리기
			if(dist[ck][cx][cy][nt] == 0) {
				dist[ck][cx][cy][nt] = dist[ck][cx][cy][ct] + 1;
				q.offer(new int[] {ck, cx, cy, nt});
			}

			// 탐색 시작
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				// 범위 검증부터
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				// 벽을 부술 필요가 없을때
				if(map[nx][ny] == 0) {

					// 방문 여부 확인
					if(dist[ck][nx][ny][nt] == 0) {

						dist[ck][nx][ny][nt] = dist[ck][cx][cy][ct] + 1;

						q.offer(new int[]{ck, nx, ny, nt});
					}


				} else { // 벽을 부숴야 할때 (현재 시간이 낮일때만 가능)
					if(ct == 0 && ck < K && dist[ck + 1][nx][ny][nt] == 0) {

						dist[ck + 1][nx][ny][nt] = dist[ck][cx][cy][ct] + 1;

						q.offer(new int[]{ck + 1, nx, ny, nt});
					}
				}
			}
		}

		return -1;
	}

}
