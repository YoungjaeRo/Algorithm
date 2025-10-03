class Solution {
   public int solution(int k, int[][] dungeons) {
		boolean[] used = new boolean[dungeons.length];

		return dfs(k, dungeons, used, 0);

	}

	// k: 현재 남은 피로도
	// dungeons[i][0]: i번 던전 입장 최소 필요 피로도
	// dungeons[i][1]: i번 던전 소모 피로도
	// used[i]: i번 던전을 이미 돌았는지
	// cnt: 지금까지 돈(클리어한) 던전 수


	private int dfs(int k, int[][] dungeons, boolean[] used, int cnt) {
		int best = cnt;


		for(int i = 0; i < dungeons.length; i++) {
			// 아직 안 갔고 입장 조건을 만족하면 시도
			if(!used[i] && k >= dungeons[i][0]) {
				used[i] = true;
				best = Math.max(best, dfs(k - dungeons[i][1], dungeons, used, cnt + 1));

				used[i] = false; // 백트래킹
			}

		}
		
		return best;
	}
}