import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;

	static int r, c;

	static int d;

	// 북(0), 동(1), 남(2), 서(3)

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());


		// 청소가 되지 않은 칸 = 0, 벽 = 1, 청소 된 칸 = 2 (내가 임의로 정함)

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		int cleanCount = 0;

		while(true) {

			// 1. 현재 칸이 아직 청소되지 않은 경우, 칸을 청소한다.
			if(map[r][c] == 0) {
				map[r][c] = 2; // 청소함으로 처리
				cleanCount++;
			}

			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인
			boolean found = false;

			for(int i = 0; i < 4; i++) {
				// 반시계 방향으로 회전
				d = (d + 3) % 4;


				int nr = r + dx[d];
				int nc = c + dy[d];
				// 범위 확인
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				// 한칸 앞으로 전진한 칸이, 청소되지 않은 0이면, 해당 지점으로 이동
				if(map[nr][nc] == 0) {
					r = nr;
					c = nc;
					found = true;
					break; //
				}

			}

			// 4. 이제 4 방향 중 한 곳으로 이동했다면, 다시 while 문 (1번 규칙으로 이동)
			if(found) {
				continue;
			}

			// 네 방향 모두 청소할 곳이 없는 경우 --> 후진 시도
			int backDir = (d + 2) % 4;

			int backR = r + dx[backDir];
			int backC = c + dy[backDir];

			if (backR < 0 || backR >= N || backC < 0 || backC >= M || map[backR][backC] == 1) {
				break;
			}

			// 벽이 아니면 뒤로 한칸 후진 (방향은 유지함)
			r = backR;
			c = backC;
		}

		System.out.println(cleanCount);

	}
}
