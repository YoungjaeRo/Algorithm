import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
		int n = triangle.length;

		int[][] dp = new int[n][n];

		dp[0][0] = triangle[0][0]; // 시작점

		for(int i = 1; i < n; i++) {
			// 왼쪽 끝
			dp[i][0] = dp[i-1][0] + triangle[i][0];

			// 가운데
			for(int j = 1; j < i; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
			}

			// 오른쪽 끝

			dp[i][i] = dp[i-1][i-1] + triangle[i][i];
		}

		return Arrays.stream(dp[n - 1]).max().getAsInt();
	}
}