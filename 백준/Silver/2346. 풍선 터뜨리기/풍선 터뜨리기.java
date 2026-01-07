import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 1 ~ N 번까지의 풍선이 원형으로 놓여져 있음
	 *
	 * 각 풍선 안에는 종이가 하나 들어있음.
	 *  -N <= 종이 <= N
	 *
	 *  우선 제일 처음에는 1번 풍선을 터트린다.
	 *  다음에는 풍선 안에 있는 종이를 꺼내어,
	 *  그 종이에 적혀있는 값만큼 이동하여 다음 풍선을 터트린다
	 *
	 *  양수가 적혀있을 경우에는 오른쪽으로,
	 *  음수가 적혀있을 경우에는 왼쪽으로,
	 *
	 *  이동할때, 이미 터진 풍선은 빼고 이동한다
	 *
	 *  일단 풍선의 번호와, 그 안에 들어있는 종이(숫자 값)을 저장해야한다.
	 *
	 */

	static class Balloon {
		int idx;
		int val;

		Balloon(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		N = Integer.parseInt(br.readLine());

		// 풍선의 인덱스와 종이 값을 저장할 Deque선언 (회전을 해야하기 때문에 양방향으로 삽입과 삭제가 가능한 자료구조 덱을 사용한다)
		Deque<Balloon> dq = new ArrayDeque<>();


		st = new StringTokenizer(br.readLine());

		for(int i = 1; i <= N; i++) {
			// 종이값
			int val = Integer.parseInt(st.nextToken());

			// 덱에 삽입
			dq.offerLast(new Balloon(i, val));
		}

		// 출력용
		StringBuilder sb = new StringBuilder();

		while(!dq.isEmpty()) {
			// 1번 풍선부터 터트린다.
			Balloon cur = dq.pollFirst();

			int idx = cur.idx;
			int val = cur.val;

			sb.append(idx).append(" ");

			// 마지막 풍선이 또 dp에서 불필요하게 poll을 하면 NPE가 터짐
			if(dq.isEmpty()) {
				break;
			}

			// val이 양수면, 오른쪽으로 이동
			if(val > 0) {
				// 하지만 방금 pollFirst()로 앞에 풍선을 하나 터트렸으므로
				// val - 1 만큼 회전
				for(int i = 0; i < val - 1; i++) {
					dq.offerLast(dq.pollFirst());
				}
			} else if(val < 0) {
				for(int i = 0; i < Math.abs(val); i++) {
					dq.offerFirst(dq.pollLast());
				}
			}

		}

		System.out.println(sb.toString());
	}
}
