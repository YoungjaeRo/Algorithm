import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 다리가 1개 있음, 이를 n개의 트럭이 건너려고 한다.
	 * 트럭의 순서는 바꿀 수 없고, 무게는 서로 같지 않을 수 있다
	 * 다리 위에는 오로지 w개의 트럭만 '동시에' 올라갈 수 있다
	 *
	 * 다리의 길이는 w 단위길이,
	 * 각 트럭들은 하나의 단위시간에 하나의 단위길이만큼 이동 가능
	 *
	 * 다리 위에 올라가 있는 트럭들의 무게합은, 다리의 최대하중인 "L" 보다 작거나 같아야 한다 ㅇㅇ
	 *
	 * 다리위에 완전히 올라가지 못한 트럭의 무게는 다리위의 트럭들의 무게의 합에 포함시키지 않는다
	 *
	 * ex 다리의 길이 w = 2;
	 * 최대 하중 = L = 10;
	 * 다리를 건너려는 트럭의 무게 [7, 4, 5, 6]
	 * 순서대로 다리를 건너려할때, 모든 트럭이 다리는 건너는 최단 시간은 8
	 *
	 * 다리의 길이가 2라면, 이를 건너는 데 걸리는 시간은 한대당 2초임
	 * 그럼 사실상 1초마다 현재 다리의 무게에 실려 있는 무게를 확인해야하나?
	 *
	 * 그림은 잘보면 선입선출임으로 알 수 있기 때문에, Queue로 푼다
	 */

	static int n; // 트럭의 개수

	static int w; // 다리의 길이

	static int L; // 다리가 버틸 수 있는 최대 하중

	static int[] trucks; // 트럭의 무게들을 저장해놓을 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		trucks = new int[n];

		st = new StringTokenizer(br.readLine());

		// 트럭의 무게값 입력

		for(int i = 0; i < n; i++) {
		trucks[i] = Integer.parseInt(st.nextToken());
		}

		// 다리 생성 (큐 기반)
		Queue<Integer> q = new LinkedList<>();

		// 일단 첫 다리는 아무런 트럭이 올라와있지 않기 때문에, 0으로 초기화
		for(int i = 0; i < w; i++) {
			q.offer(0);
		}

		int time = 0; // 현재까지의 경과 시간
		int idx = 0; // trucks 배열을 가리키는 인덱스
		int totalWeight = 0; // 다리 위에 올라와있는 총 무게 합 (L 이하여야함)

		// 큐가 빌때까지 실행(마지막 트럭이 완전히 빠져 나갈때까지)
		while(!q.isEmpty()) {
			time++; // 시간 경과

			// 일단 첫 트럭 또는 0을 다리에서 뺀다.
			int truck = q.poll();

			totalWeight = totalWeight - truck; // 해당 트럭은 이제 다리에서 빠져 나갔으므로, 무게를 빼준다.

			// 아직 남아있는 트럭이 있다면, 시도해본다
			if(idx < n) {
				// 트럭을 올렸을때, 최대하중을 넘지 않는지 검증
				if(trucks[idx] + totalWeight <= L) {
					q.offer(trucks[idx]);

					totalWeight = totalWeight + trucks[idx];

					idx++;
				} else { // 하중 때문에 올릴수 없으면
					q.offer(0);
					totalWeight = totalWeight + 0;
				}
			}
		}

		System.out.println(time);

	}
}
