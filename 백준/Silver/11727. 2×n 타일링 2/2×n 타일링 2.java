import java.io.*;

public class Main {
	/**
	 * 전에 풀어본 유형과는 조금 비슷하지만, 체크해야할점이 있다
	 * 1 * 2, 2 * 1, 2 * 타일을 각각 마지막칸 에 붙이면 해당 경우의수도 타일을 채우는 방법의 수에 포함된다
	 *
	 *
	 */

	// dp[i] = 2 X i를 채우는 방법의 수
	// dp[i] = dp[i-1] +  2 * dp[i-2]

	static final int MOD = 10007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		/**
		 * dp[i] = 2 * i를 채울 수 있는 경우의 수
		 */

		int[] dp = new int[n + 1];

		// 초기값 설정하기
		dp[1] = 1;

		if(n >= 2) {
			dp[2] = 3;
		}

		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % MOD;
		}

		System.out.println(dp[n]);
	}
}
