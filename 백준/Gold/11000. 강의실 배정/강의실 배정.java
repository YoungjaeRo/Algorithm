import java.io.*;
import java.util.*;

public class Main {
	/**
	 * int[][]
	 * 0번 인덱스에는 시작 시간,
	 * 1 번 인덱스에는 종료시간
	 *
	 * 강의실을 최소 몇개 사용해야하는지 물어보는 문제다
	 * 최대 몇개를 넣을까가 아니라, 동시에 겹치는 개수가 몇개여서, 강의실이 몇개가 필요한지 구하는 것이다.
	 *
	 *
	 */

	static int N;
	static int[][] classes;

	static int num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;

		N = Integer.parseInt(br.readLine());

		classes = new int[N][2];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			// 각 강의의 시작하는 시간과 끝나는 시간을 입력해준다
			classes[i][0] = s;
			classes[i][1] = e;
		}

		// 끝나는 시간이 빠른대로 정렬
		Arrays.sort(classes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {

				// 근데 만약 시작 시간이 같다면, 종료가 빠른거부터 ㅇㅇ
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});

		// 최소힙으로 각 강의의 종료시간을 저장한다.
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			int start = classes[i][0];
			int end = classes[i][1];

			// pq.peek() // 현재 수업이 가장 빨리 끝나는 시간
			// 그 시간이 다른 수업 시작 시간보다 작으면(빨리 끝나면), 해당 끝나는 시간을 빼줌 -- >  새로운 시작 시간의 강의가 들어가야하기 때문에

			if(!pq.isEmpty() && pq.peek() <= start) {
				pq.poll(); // 비워진 강의실 하나 재사용 (끝난 수업을 제거함)
			}

			// 현재 수업의 종료 시간을 넣는다
			pq.offer(end);
		}

		// 힙에 남아있는 종료시간의 개수  == 동시에 사용중인 강의실 수 이자 최소 강의실 개수
		System.out.println(pq.size());
	}
}
