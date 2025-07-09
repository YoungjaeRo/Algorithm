import java.util.*;

public class Main {
	static int N;
	static int count = 0;

	static boolean[] col;          // 열 체크
	static boolean[] diag1;        // ↙ 대각선 체크 (row + col)
	static boolean[] diag2;        // ↘ 대각선 체크 (row - col + N - 1)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		col = new boolean[N];
		diag1 = new boolean[2 * N - 1];
		diag2 = new boolean[2 * N - 1];

		backtrack(0);
		System.out.println(count);
	}

	static void backtrack(int row) {
		if (row == N) {
			count++;  // 모든 행에 퀸을 다 놓은 경우
			return;
		}

		for (int c = 0; c < N; c++) {
			// 퀸을 놓을 수 없는 위치면 continue
			if (col[c] || diag1[row + c] || diag2[row - c + N - 1]) {
				continue;
			}

			// 퀸을 놓는다
			col[c] = true;
			diag1[row + c] = true;
			diag2[row - c + N - 1] = true;

			backtrack(row + 1); // 다음 행으로 진행

			// 백트래킹 (되돌리기)
			col[c] = false;
			diag1[row + c] = false;
			diag2[row - c + N - 1] = false;
		}
	}
}
