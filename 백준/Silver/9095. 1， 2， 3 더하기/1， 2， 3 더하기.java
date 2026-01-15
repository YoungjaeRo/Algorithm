import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1,2,3을 더해서 정수 4를 나타내는 법
	 *
	 * dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
	 */

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());

			int[] dp = new int[n + 1];

			dp[0] = 0;
			if (n >= 1) dp[1] = 1;
			if (n >= 2) dp[2] = 2;
			if (n >= 3) dp[3] = 4;


			for(int i = 4; i <= n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}

			sb.append(dp[n]).append("\n");
		}

		System.out.println(sb.toString());
	}
}
