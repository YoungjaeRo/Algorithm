import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 한 행에 퀸을 하나씩만 놓아야 된다 즉 row가 depth가 됨
	 *
	 * 결국 같은 행, 열, 대각선에 놓으면 안된다
	 * 같은 대각선인지 파악하는 알고리즘은 Math.abs(열1 - 열2) == Math.abs(행1 - 행2)일때 이다.
	 */

	static int N;
	static int[] board;
	static int count = 0;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());

	// ex : board[0] = 1 의 뜻은 0행 1열에 퀸을 놓겠다라는 뜻
	board = new int[N];

	backtrack(0); // 0행부터 놓기 시작

		System.out.println(count);
	}

	static void backtrack(int row) {

		// N - 1까지 퀸을 다 놓았으면, 경우의 수  + 1 해주기
		if(row == N) {
			count++;
			return;
		}

		// 현재 row 레벨에서 어떤 col에 넣을 수 있는지 탐색
		for(int col = 0; col < N; col++) {
			if(isPossible(row, col)) {
				board[row] = col;
				backtrack(row + 1);
			}
		}
	}

	static boolean isPossible(int row, int col) {
		// 이전 행들에 놓인 퀸들에 대한 검사
		for(int r = 0; r < row; r++) {
			// 같은 열이면 안됨
			if(board[r] == col) {
				return false;

				// 같은 대각선이어도 안됨
			} else if(Math.abs(row - r) == Math.abs(col - board[r])) {
				return false;
			}
		}
		return true;
	}
}
