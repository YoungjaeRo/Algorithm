import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 민규는 돈을 최대한 지불해서, 카드 N개를 구매하려고 한다
	 *
	 * 카드 i개가 포함된 카드팩의 가격은 Pi
	 *
	 * N개의 카드를 구매하기 위해서 민규가 지불애햐하는 금액의 최댓값
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] P = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];
		dp[0] = 0;

		// dp[i] : 카드 i개를 사는데, 필요한 최대금액
		for(int i = 1; i <= N; i++) {

			int best = 0;

			for(int k = 1; k <= i; k++) {
				best = Math.max(best, dp[i - k] + P[k]);
			}
			
			dp[i] = best;
		}

		System.out.println(dp[N]);
	}
}
