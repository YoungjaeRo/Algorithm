import java.io.*;
import java.util.*;

public class Solution {
	/**
	 * 배열의 각 원소들에 대해 자신보다 크면서, 가장 가까이 있는 수를 뒷 큰수라고 한다
	 * @param numbers
	 * @return
	 */
	public int[] solution(int[] numbers) {
		int n = numbers.length;

		// 기본 값은 전부 -1로 채워좀
		int[] answer = new int[n];
		Arrays.fill(answer, -1);

		// 스택(인덱스 저장)
		// 스택에는 아직 뒷큰수를 못 찾은 인덱스만 들어간다
		ArrayDeque<Integer> stack = new ArrayDeque<>();

		for(int i = 0; i < n; i++) {
			int cur = numbers[i];

			/**
			 * 스택 top(가장 최근에 쌓인 인덱스)의 값이
			 * 현재 값(cur)보다 작다면:
			 *   - top 인덱스 입장에선, 오른쪽에서 처음 만난 큰 수가 cur이다.
			 *   - 따라서 answer[top] = cur 로 확정하고 pop 한다.
			 */
			
			// [9, 1, 5, 3, 6, 2]
			//  0, 1, 2, 3, 4, 5
			while(!stack.isEmpty()) {
				int topIdx = stack.peekLast(); // 스택의 맨위
				int topVal = numbers[topIdx]; // 그 인덱스의 값

				// 현재 값이 top의 값보다 크면, top의 정답은 현재값
				if(topVal < cur) {
					answer[topIdx] = cur;
					stack.pollLast(); // top 제거
				} else {
					// 현재 값이 top보다 크지 않으면(같거나 작으면),
					// 더 아래에 있는 애들은 top보다 "더 왼쪽"이고,
					// 보통 top보다 값이 더 크거나 비슷한 상태라
					// cur로는 그 누구의 정답도 확정할 수 없다.
					// 그래서 while 종료.
					break;
				}
			}
			// 스택에는 값이 아닌, 인덱스만 삽입한다.
			stack.offerLast(i);
		}
		return answer;

	}
}
