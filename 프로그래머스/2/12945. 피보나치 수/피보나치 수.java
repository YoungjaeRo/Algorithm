class Solution {
    
    static final int MOD = 1234567;

	public int solution(int n) {
		/**
		 * n이 10만까지이므로 오버플로우로 계산이 깨질수 있으니 dp 배열에 저장할때부터
		 * MOD로 나누지
		 */

		int[] dp = new int[n + 1];

		// 초기값 세팅
		dp[0] = 0;
		dp[1] = 1;

		for(int i = 2; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		int answer = dp[n];

		return answer;
	}
}