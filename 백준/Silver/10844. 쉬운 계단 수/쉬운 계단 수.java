import java.io.*;
import java.util.*;

public class Main {

	static final long MOD = 1000000000L;

	static int N; // 주어진 숫자

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		/**
		 * dp[len][d]
		 *
		 * 길이가 len인 계단 수 중
		 * 마지막 자리가 숫자 d인 개수
		 */

		// 길이가 0부터 N까지, 들어올 수 있는 숫자는 0 ~ 9
		long[][] dp = new long[N + 1][10];

		// 초기값 설정
		for(int d = 1; d <= 9; d++) {
			dp[1][d] = 1;
		}

		/**
		 * 마지막 숫자가 d가 되려면, 직전 숫자는?
		 * 계단 수 조건: |(직전) - (현재)| = 1
		 * => 직전 숫자는 d-1 또는 d+1 만 가능
		 */

		for(int len = 2; len <= N; len++) {
			// 마지막이 0인 경우: 직전은 1만 가능
			dp[len][0] = dp[len - 1][1] % MOD;

			// 마지막이 9인 경우 : 직전은 8만 가능
			dp[len][9] = dp[len - 1][8] % MOD;

			//마지막이 1 ~ 8인 경우
			for(int i = 1; i <= 8; i++) {
				dp[len][i] = (dp[len - 1][i - 1] + dp[len - 1][i + 1]) % MOD;
			}

		}

		// 답 출력
		long ans  = 0;

		for(int d = 0; d <= 9; d++) {
			ans = (ans + dp[N][d]) % MOD;
		}

		System.out.println(ans);
	}
}
