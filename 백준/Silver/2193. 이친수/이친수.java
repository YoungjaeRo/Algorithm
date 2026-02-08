import java.io.*;
import java.util.*;

public class Main {

	static int N;

	/**
	 * 이친수 조건
	 * 1. 0으로 시작하지 않는다
	 *
	 * 2. 1이 두번 연속으로 나타나지 않는다
	 *
	 *
	 * dp[len][0] == len 자리수, 마지막 자리가 0인 것
	 *
	 * dp[len][1] == len 자리수, 마지막 자리가 1인 것
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N +1][2];

		// 기저값
		dp[1][0] = 0;
		dp[1][1] = 1;

		for(int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
			dp[i][1] = dp[i - 1][0];
		}

		long ans = dp[N][0] + dp[N][1];

		System.out.println(ans);

	}
}
