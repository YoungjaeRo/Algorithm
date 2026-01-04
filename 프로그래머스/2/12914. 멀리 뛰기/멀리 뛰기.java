class Solution {
   /**
	 *  효진이는 1칸 또는 2칸씩 뛸 수 있다
	 *  즉 n칸까지 도달하기 위한 경우의 수는
	 *  n - 1 까지의 경우의 수  + n -2 까지의 경우의 수 이다 ㅇㅇ
	 *
	 *  dp[n] = dp[n- 1] + dp[n - 2]
	 */

	static int[] dp;

	public long solution(int n) {

		int MOD = 1234567;
		
		if(n == 1) {
			return 1;
		} else if(n == 2) {
			return 2;
		}
		
		// dp 초기값 설정
		dp = new int[n + 1];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i <=n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}
		
		return dp[n];
	}
}