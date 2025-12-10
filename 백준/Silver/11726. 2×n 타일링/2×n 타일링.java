import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int n = Integer.parseInt(br.readLine());

		// dp[i] : 2 X i보드를 만들 수 있는 경우의 수
		int[] dp = new int[n + 2]; // n이 1일 수도 있기 때문에,

		// 기저값 주입하기
		dp[1] = 1;
		dp[2] = 2;

		/**
		 * 점화식 : dp[n] = dp[n-1] + dp[n-2]
		 */

		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}

		System.out.println(dp[n]);
	}
}
