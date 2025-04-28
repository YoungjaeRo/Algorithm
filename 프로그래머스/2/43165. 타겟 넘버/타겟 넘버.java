public class Solution {
	static int answer = 0; // 최종 정답을 저장할 변수

	public int solution(int[] numbers, int target) {
		dfs(numbers, target, 0, 0); // DFS 시작, 인덱스 0, 합 0부터 시작
		return answer; // DFS 가 끝나면, answer를 리턴


	}

	public void dfs(int[] numbers, int target, int idx, int sum) {
		// 재귀함수는 종료조건이 필요한다
		if(idx == numbers.length) {
			// 현재 합이 target 이면 answer 수치를 하나 증가
			if(sum == target) {
				answer++;

			}
			// 어찌됐든, 더이상 진행 핳 수 없으니 return
			return;

		}

		dfs(numbers, target, idx + 1, sum + numbers[idx]); // 지금 숫자를 더하는 선택을 한 경우, 다음 숫자 idx + 1로 이동하면서 합계에 numbers[idx]를 더함

		dfs(numbers, target, idx + 1, sum - numbers[idx]); // 그 반대
	}
}
