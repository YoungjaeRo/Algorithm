import java.io.*;
import java.util.*;

public class Main {
	/**
	 * dp[i][0] = i열에서 아무것도 안 뜯었을 때, 1~i열 최대 점수
	 * dp[i][1] = i열에서 위 스티커를 뜯었을 때, 1~i열 최대 점수
	 * dp[i][2] = i열에서 아래 스티커를 뜯었을 때, 1~i열 최대 점수
	 */

	static int T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {

			int n = Integer.parseInt(br.readLine());

			int[] up = new int[n + 1];
			int[] down = new int[n + 1];


			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 1; i<= n; i++) {
				up[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for(int i = 1; i <= n; i++) {
				down[i] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[n + 1][3];


			// 기저값 세팅하기
			dp[1][0] = 0; // 1번째 열에서 아무것도 때지 않았을때 ㅇㅇ
			dp[1][1] = up[1]; // 1번째 열에서 위에것을 떼어냈을때
			dp[1][2] = down[1]; // 1번째 열에서 아래것을 떼어냈을때

			for(int i = 2; i<= n; i++) {

				// 아무것도 뜯지 않았을때
				dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));

				// 위를 뜯을때
				dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + up[i];

				// 아래를 뜯을때
				dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + down[i];
			}

			int ans = Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
			System.out.println(ans);
		}
	}

}
