import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
	/**
	 * 겹치는 선분들의 합집합(중복 제외)으로 합쳐서 전체 길이를 구하는 문제
	 *
	 */

	static class Line implements Comparable<Line> {
		int s; // 시작점
		int e; //  끝점

		Line(int s, int e) {
			this.s = s;
			this.e = e;
		}

		// 시작점을 기준으로 오름차순 정렬, 그후에 끝점 기준
		@Override
		public int compareTo(Line o) {
			if(this.s != o.s) {
				return Integer.compare(this.s, o.s);
			} else {
				return Integer.compare(this.e, o.e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Line[] lines = new Line[N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			lines[i] = new Line(x, y);
		}

		// 정렬 기준에 맞춰서 정렬하기
		Arrays.sort(lines);

		// 스와핑  + 구간 병합
		long answer = 0;

		// 현재 병합중인 구간
		int curL = lines[0].s;
		int curR = lines[0].e;

		for(int i = 1; i < N; i++) {
			int nextL = lines[i].s;
			int nextR = lines[i].e;
			/**
			 * 핵심 원리:
			 *
			 * (A) 다음 선분이 현재 구간과 겹치거나 이어지면 (nextL <= curR)
			 *     -> 아직 길이를 더하면 안 됨
			 *     -> 왜? 앞으로 더 확장될 수도 있어서 완성된 구간이 아니기 때문
			 *     -> 그래서 curR만 최대치로 늘려서 병합만 해둔다
			 *
			 * (B) 다음 선분이 현재 구간과 안 겹치면 (nextL > curR)
			 *     -> 이 순간, 현재 구간은 이제 절대 더 커질 수 없음
			 *        (정렬되어 있어서 이후 선분 시작점은 무조건 더 오른쪽!)
			 *     -> 그러니까 현재 구간 길이를 answer에 확정해서 더하고,
			 *        다음 선분으로 새로운 구간을 시작한다.
			 */

			// 겹치거나 이어지는 경우
			if(nextL <= curR) {


				/**
				 * 겹치면 answer를 갱신하지 않는다!
				 * 여기서 answer를 더해버리면:
				 * - 겹치는 부분이 중복 계산되거나
				 * - 나중에 더 확장되었을 때 다시 계산해야 해서 꼬임
				 *
				 * 그래서 병합만 한다:
				 * 현재 구간의 끝(curR)을 더 멀리 뻗을 수 있으면 늘림
				 */

				if(nextR > curR) {
					curR = nextR;
				}

			} else {
				/**
				 * 겹치지 않는 경우
				 */

				answer = answer + Math.abs(curR - curL);

				// 새로운 구간 시작
				curL = nextL;
				curR = nextR;
			}
		}

		/**
		 * 중요:
		 * for문 안에서는 안 겹칠 때만 길이를 더한다.
		 * 그런데 마지막 병합 구간은
		 * 안 겹치는 순간이 오지 않고 for문이 끝나버릴 수 있음.
		 *
		 * 그래서 마지막 구간 길이를 여기서 한 번 더 확정해서 더해준다.
		 */
		answer = answer + Math.abs(curR - curL);
		
		System.out.println(answer);

	}
}
