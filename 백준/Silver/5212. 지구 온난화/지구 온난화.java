import java.util.*;
import java.io.*;


public class Main {
	static int R;
	static int C;

	static char[][] map;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];


		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();

		}

		// 1. 50년 후 상태 계산
		char[][] next = new char[R][C];
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'X') {
					int sea = 0;


					// 사방 체크 : 격자 밖은 바다로 간주하기
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];


						if(!in(nr, nc) || map[nr][nc] == '.') {
							sea++;
						}
					}

					// 바다가 3칸 이상이면 섬은 가라앉게 됨
					next[r][c] = (sea >= 3) ? '.' : 'X';
				} else {
					// 원래 바다는 그대로 바다로 냅둠
					next[r][c] = '.';
				}
			}
		}


		// 2. 남은 육지의 최소 직사각형 범위 계산하기
		int minR = R;
		int maxR = -1;

		int minC = C;
		int maxC = -1;

		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(next[r][c] == 'X') {

					if(r < minR) {
						minR = r;
					}

					if(r > maxR) {
						maxR = r;
					}

					if(c < minC) {
						minC = c;
					}

					if(c > maxC) {
						maxC = c;
					}
				}
			}
		}

		// 3. 출력
		// 모든 땅이 가라앉아 없으면, 아무것도 출력하지 않음
		if(maxR == -1) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		for(int r = minR; r <= maxR; r++) {
			for(int c = minC; c<= maxC; c++) {
				sb.append(next[r][c]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
		

	}
	
	static boolean in(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
