import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int P; // 플레이어

	static int[] S; // 각각의 이동 칸

	static char[][] map;

	static Queue<int[]> [] q; // 큐들로 이루어져 있는 배열

	static int[] owned;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		// 각 S의 이동 칸만큼 이동한다.
		S = new int[P + 1];

		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= P; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		map = new char[N][M];

		q = new ArrayDeque[P + 1];

		for(int i = 1; i <= P; i++) {
			q[i] = new ArrayDeque<>();
		}

		owned = new int[P + 1];

		// 그래프 정보 입력 받기
		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);

				// 시작점이라면,
				if(map[i][j] >= '1' && map[i][j] <= '9') {
					int player = map[i][j] - '0';
					q[player].offer(new int[]{i, j});
					owned[player]++; // 시작점도 영역에 해당함
				}
			}
		}

		boolean movedThisRound = true;
		// 모든 플레이어가 더 이상 확장할 수 없을때, 게임이 끝남
		while(movedThisRound) {

			movedThisRound = false;

			// 1 ~ P번째 플레이어부터 실행
			for(int p = 1; p <= P; p++) {

				for(int step = 0; step < S[p]; step++) {

					if(q[p].isEmpty()) {
						break; // 다른 플레이어 턴으로 넘어감
					}

					int size = q[p].size(); // 이번 step에서 처리할 개수

					// 턴 조기종료용
					boolean movedThisStep = false;

					for(int t = 0; t < size; t++) {
						int[] cur = q[p].poll();
						int x = cur[0];
						int y = cur[1];

						for(int d = 0; d < 4; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];

							// 범위 검증
							if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
								continue;
							}

							// 빈칸만 점령 가능
							if(map[nx][ny] == '.') {
								map[nx][ny] = (char) (p + '0');
								q[p].offer(new int[]{nx, ny});
								owned[p]++;
								movedThisStep = true;
							}
						}
					}

					if(!movedThisStep) {
						break;
					}
					movedThisRound = true;
				}
			}

		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= P; i++) {
			sb.append(owned[i]);
			if(i < P) {
				sb.append(' ');
			}
		}

		System.out.println(sb);

	}
}
