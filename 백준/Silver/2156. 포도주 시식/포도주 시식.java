import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1. 포도주를 선택하면, 그 잔에 있는 포도주는 다 마셔야하고, 마신 후에는 원래 위치에 다시 놓아야 한다
	 *
	 * 2. 연솟으로 놓여있는 3잔을 모두 마실 수는 없다
	 *
	 * 될수 있는대로 가장 많은 양의 포도주를 맛보려고 한다.
	 *
	 * dp[i] = i 번째 잔까지 마셨을때, 얻을 수 있는 최대양
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] wines = new int[n + 1];

		// 포도주 용량 값 입력
		for(int i = 1; i <= n; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}

		// dp용 배열 선언
		long[] dp = new long[n + 1];

		// N이 그냥 1이면 그대로 출력
		if(n == 1) {
			System.out.println(wines[1]);
			return;
		}

		// 기저값 세팅
		dp[1] = wines[1];
		dp[2] = (long) wines[1] + wines[2];

		for(int i = 3; i <= n; i++) {

			// 1) i번째 잔을 안 마심 -> dp[i-1]
			long case1 = dp[i - 1];

			// 2) i번째만 마심 (i-1은 안 마셔야 함) -> dp[i-2] + wine[i]
			long case2 = dp[i - 2] + wines[i];

			// 3) i-1, i를 연속으로 마심 (i-2는 안 마셔야 함) -> dp[i-3] + wine[i-1] + wine[i]
			long case3 = dp[i - 3] + wines[i - 1] + wines[i];
			
			dp[i] = Math.max(case1, Math.max(case2, case3));
		}

		System.out.println(dp[n]);
	}
}
