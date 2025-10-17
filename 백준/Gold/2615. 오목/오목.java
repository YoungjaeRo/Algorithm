import java.io.*;
import java.util.*;
public class Main {
	static final int N = 19;
	static int[][] board = new int[N][N];

	/**
	 * 검사할 4개 방향 벡터
	 *  - {dr, dc} 순서로 정의
	 *  - 왼쪽/위쪽/↖/↙ 방향은 검사 안 해도 됨 (시작점 판정으로 중복 방지)
	 */
	// 오른 쪽, 아래 오른쪽 아래 대각선, 오른쪽 위
	static int[][] dirs = {
		{0, 1}, {1, 0}, {1, 1}, {-1, 1}
	};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 0; i < N; i++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int color = board[r][c];
				// 빈칸은 스킵함
				if(color == 0) {
					continue;
				}

				// 4개의 방향 각각에 대해서, 해당 칸이 그 방향의 시작점인지 확인후에 시작
				for(int d = 0; d < 4; d++) {
					int dr = dirs[d][0];
					int dc = dirs[d][1];


					// 시작점 판정 시작
					// 같은 줄을 반복해서 세지 않으려면, 현재칸의 반대방향에 같은 색이 있으면 안됨
					//  즉 (r, c)가 그 줄의 가장 앞이어야함
					int pr = r - dr; // previous r 반대 방향 한칸
					int pc = c- dc;

					if (in(pr, pc) && board[pr][pc] == color) {
						// 앞에 같은 색이 있으니 시작점이 아님 → 이 방향은 스킵
						continue;
					}

					// 앞으로 최대 4칸만 전진할 예정
					int cnt = 1; //  현재 칸을 포함
					int nr = r; // next r (현재 위치부터 시작
					int nc = c;

					for(int k = 1; k < 5; k++) { // 4번만 더 확인하면, 오목 완성 ㅇㅇ
						nr = nr + dr;
						nc = nc + dc;

						// 보드 밖이거나, 색깔이 다르면 중단함
						if(!in(nr, nc) || board[nr][nc] != color) {
							break;
						}

						// 그게 아니라면, 계속 진행하고 칸수 추가함
						cnt++;

					}

					// 정확히 오목이라면,
					if(cnt == 5) {
						int ar = nr + dr; // 다음칸
						int ac = nc + dc; // 다음칸

						if (in(ar, ac) && board[ar][ac] == color) {
							// 6개 이상 이어짐 → 정답 아님
							continue;
						}

						//  딱 5개만 이루어졌고, 시작잠도 맞다면,
						System.out.println(color);
						System.out.println((r + 1) + " " + (c + 1));
						return;//  다 출력하고 나면 종료하기
					}

				}
			}
		}
		
		 System.out.println(0); // 끝까지 아무것도 찾지 못했으면 0을 출력
	}

	// 주어진 r,c가 바둑판 안의 범위인지 확인함
	static boolean in(int r, int c) {
		return r < N && c < N && r >= 0 && c >= 0;
	}
}
