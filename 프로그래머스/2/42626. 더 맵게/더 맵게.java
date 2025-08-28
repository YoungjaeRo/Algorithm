import java.util.PriorityQueue;

public class Solution {

		public int solution(int[] scoville, int K) {
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int s : scoville) {
				pq.add(s);
			}

			int mixCount = 0; // 섞은 횟수 초기화
			while(!pq.isEmpty() && pq.peek() < K) {
				if(pq.size() < 2) {
					return -1; // 우선순위 큐에 있는것이 2개 미만이면 섞기가 불가능 ㅇㅇ
				}

				int a = pq.poll(); // 가장 스코빌 지수가 낮은것
				int b = pq.poll(); // 2번째로 낮은것

				int mixed = a + b * 2; // 새로운 스코빌 지수

				pq.add(mixed);
				mixCount++;

			}

			return mixCount;
		}

}
