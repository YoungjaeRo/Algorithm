import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
		// 큐에는 [우선순위, 처음 인덱스] 형태로 넣어둔다.
		//  - "처음 인덱스"를 들고 있어야 나중에 location 문서를 정확히 찾아낼 수 있음
		Queue<int[]> q = new ArrayDeque<>();

		// 우선순위(1~9)별 남은 개수 카운트 테이블
		//  freq[p] == 지금 시점에 우선순위 p인 문서가 몇 개 남아 있나

		int[] freq = new int[10];

		// 초기 로딩 : 큐 채우고, 우선 순위 빈도도 같이 세기
		for(int i = 0; i < priorities.length; i++) {
			q.offer(new int[]{priorities[i], i});
			freq[priorities[i]] ++;
		}

		// current = 현재 시점에 "남아있는 문서 중 최댓값 우선순위"
		//  9부터 내려오면서 실제로 남아있는(=freq>0) 지점에 멈춘다

		int current = 9;
		while(current > 0 && freq[current] == 0) {
			current--;
		}

		int order = 0; // 지금까지 몇 개 출력했는지(=정답)

		// 규칙 그래도 시뮬레이션, 앞에서 뽑아서 최대값이면 출력, 아니면 뒤로 보내기
		while(true) {
			int[] doc = q.poll();  // 맨 앞 문서
			int p = doc[0];  // 그 문서의 우선순위
			int idx = doc[1];  // 그 문서의 "처음 인덱스"

			// 만약 p < current라면?
			// 어디간에 나보다 큰 우선순위가 아직 남아있다는 뜻 --> 해당 프로세스는 지금 차례가 아님
			if(p < current) {
				q.offer(doc); // 다시 큐에 넣음
				continue; // 다음 문서를 보자
			}

			// 여기 오면 p == current → 지금이 이 우선순위들의 출력 차례
			order++; // 한개 출력
			freq[p]--; // 해당 우선순위의 문서 수량 하나 빼기

			// 내가 찾던 문서 (location)이면 끝!
			if(idx == location) {
				return order;
			}

			// 방금 출력한 우선순위가 다 소진됐으면, current를 아래로 내림
			while (current > 0 && freq[current] == 0) current--;

		}
	}
}