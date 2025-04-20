import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int SK;
	static int CY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		boolean[] dp = new boolean[N + 1];

		/**
		 * dp[i] = true → 돌 i개 있을 때, 현재 내 차례인 사람이 이길 수 있음
		 * dp[i] = false → 아무리 해도 내가 지게 되는 상황
		 */
		// 초기값 설정
		dp[0] = false; // 돌의 개수가 0이면 일단 내 차례에선 무조건 짐

		if(N >= 1) {
			dp[1] = true;
		}

		if(N >= 2) {
			dp[2] = false;
		}

		if(N >= 3) {
			dp[3] = true;
		}

		for(int i = 4; i <= N; i++) {
			dp[i] = !dp[i - 1] || !dp[i - 3];
		}

		System.out.println(dp[N] ? "SK" : "CY");
	}
}