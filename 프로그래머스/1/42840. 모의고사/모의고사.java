import java.util.*;


class Solution {
    public int[] solution(int[] answers) {
       // 각 수포자들의 패턴
		// pattern.length는 행의 개수다 == 3

		int[][] pattern = {
			{1, 2, 3, 4, 5},
			{2, 1, 2, 3, 2, 4, 2, 5},
			{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
		};

		// 수포자들의 점수를 저장할 배열
		int[] scores = new int[3];

		// 각 수포자들의 정답이 얼마나 일치하는지 확인
		for (int i = 0; i < answers.length; i++) { // i가 4일때,
			for (int j = 0; j < pattern.length; j++) {
				if (answers[i] == pattern[j][i % pattern[j].length]) {
					scores[j]++;
				}
			}
		}

		// 가장 높은 점수를 저장
		// 베열을 스트림으로 변환
		// 스트림은 배열의 요소들을 차례대로 처리 가능
		int maxScore = Arrays.stream(scores).max().getAsInt();

		// 가장 높은 점수를 가진 수포자들의 번호를 찾아서 리스트에 담음
		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] == maxScore) {
				answer.add(i + 1); // 실제로는 1,2,3번 수포자이기 때문에, +1을 함
			}
		}
		// Integer객체를 int 값으로 변환
		// List<Integer> 을 스트림으로 변환하면, 기본적으로, Stream<Integer>로 변환된다.
		// mapToInt는 Stream<Integer>를 IntStream으로 변환한다
		// IntStream은 기본 타입 int를 효율적으로 다룰 수 있는 스트림이다.

		// mapToInt는 단순히 모든 스트림 요소를 int로 반환한다는 뜻이고,
		// Integer :: intValue는 그 변환 방식을 지정하는 메서드이다.

		return answer.stream().mapToInt(Integer :: intValue).toArray(); 
        
    }
}