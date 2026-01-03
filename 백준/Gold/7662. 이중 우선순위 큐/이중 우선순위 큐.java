import java.io.*;
import java.util.*;

public class Main {
	/**
	 * PQ (우선순위) 와 똑같은데, 다른 점이 있다면,
	 * 선택적으로 가장 높은 우선순위 또는 가장 낮은 우선순위를 삭제할 수 있다
	 *
	 * PQ 두개를 쓰면, 동기화(삭제된 값 처리가 힘들어짊)
	 *
	 * 그래서 TreeMap을 사용한다
	 * TreeMap<Integer, Integer> -- >  자동으로 정렬해주는 Map
	 *
	 *   key 값. value 그 값이 몇개 들어있는지
	 *
	 *   I : 정수 삽입
	 *   D 1 : 최댓값 삭제
	 *   D -1 : 최솟값 삭제
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());

			// TreeMap: key(값)들을 "정렬된 상태"로 관리하는 Map
			// value는 해당 값이 들어있는 "개수"(중복 처리용)

			TreeMap<Integer, Integer> tp = new TreeMap<>();

			for(int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String cmd = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if(cmd.equals("I")) {
					// 삽입 : 같은 값이 있으면, 개수  + 1, 없으면 1로 시작함
					tp.put(num, tp.getOrDefault(num, 0) + 1);
				} else {
					// cmd : D일때,
					// map이 비어있으면, 해당 명령 무시
					if(tp.isEmpty()) {
						continue;
					}

					if(num == 1) { // 최댓값 삭제 : 정렬되어 있기 때문에, lastKey가 현재의 최댓값
						int maxKey = tp.lastKey();
						removeOne(tp, maxKey);

					} else if(num == -1) { // 최솟값 삭제 : 정렬되어 있기 때문에, firstKey가 현재의 최소값
						int minKey = tp.firstKey();
						removeOne(tp, minKey);
					}
				}

			}
			// 출력 : map이 비어있으면, EMPTY라고 출력하고 아니면, 현재 map에 남아있는 값중 최댓값 & 최소값
			if(tp.isEmpty()) {
				sb.append("EMPTY");
			} else {
				int maxKey = tp.lastKey();
				int minKey = tp.firstKey();
				sb.append(maxKey).append(" ").append(minKey);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	// map에서 key 값 하나를 삭제하는 동작:
	// - 해당 값이 1개면 key 자체 제거
	// - 2개 이상이면 개수만 1 감소
	static void removeOne(TreeMap<Integer, Integer> tp, int key) {
		int cnt = tp.get(key);

		if(cnt > 1) { // 값(value)만 하나 차감해주기
			tp.put(key, cnt - 1);
		} else {
			tp.remove(key);
		}
	}
}
