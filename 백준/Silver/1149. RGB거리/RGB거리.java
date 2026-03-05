import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[][] colors = new int[N + 1][3];
		int[][] dp = new int[N + 1][3];

		// 색깔 정보 입력 받기
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			colors[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			colors[i][1] = Integer.parseInt(st.nextToken()); // 초록
			colors[i][2] = Integer.parseInt(st.nextToken()); // 파랑
		}

		/**
		 * dp[i][0~2] : i번째 집에 0 ~ 2색을 칠할때의 최소 비용
		 */

		// 기저값 세팅하기 (첫번째 집을 해당 색깔로 칠할때의 최소 비용)
		dp[1][0] = colors[1][0];
		dp[1][1] = colors[1][1];
		dp[1][2] = colors[1][2];

		// dp 진행
		for(int i = 2; i <= N; i++) {
			// i번째 집을 빨강으로 색칠할 경우, 바로 전 집은 초록 또는 파란색이어야 한다
			dp[i][0] = colors[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);

			// i번째 집을 초록색으로 색칠할 경우,
			dp[i][1] = colors[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);

			// i번째 집을 파란색으로 색칠할 경우
			dp[i][2] = colors[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);

		}

		int answer = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));


		System.out.println(answer);


	}
}
