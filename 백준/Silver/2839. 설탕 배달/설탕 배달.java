import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 설탕 봉지는 3 또는 5키로로 이루어져 있음
	 *
	 * N 킬로 그램을 배달할때, 3 또는 5를 조합해서 가장 적은 봉지를 사용해서
	 *
	 * dp[N] = dp[N - 3] + 1회 or dp[N - 5]  + 1회 중 최소 값
	 */

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] dp = new int[N  +1];

		Arrays.fill(dp, 5001);


		dp[0] = 0;

		// dp 점화식 적용
		for(int i = 1; i <= N; i++) {
			if(i >= 3) {
				dp[i] = Math.min(dp[i], dp[i - 3] + 1);
			}

			if(i >= 5) {
				dp[i] = Math.min(dp[i], dp[i - 5] + 1);
			}
		}
		
		if(dp[N] == 5001) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}
		


	}
}
