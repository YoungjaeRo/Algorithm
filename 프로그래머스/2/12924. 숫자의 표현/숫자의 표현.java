class Solution {
   public int solution(int n) {
		// 개수
		int answer = 0;

		// 투포인터 탐색 (투포인터
		int left = 1;

		int right = 1;

		int sum = 1;
		
		while(left <= n) {
			
			if(sum == n) {
				answer++;
				
				// 다음 구간을 보기 위해 이동
				sum = sum - left;
				left++;
				
			} else if(sum < n) {
				right++;
				sum = sum + right;
			} else {
				sum = sum - left;
				left++;
			}
		}
		
		return answer;

	}
}