import java.io.*;
import java.util.*;

public class Main {

	static class Tower {
		int idx;
		int height;

		Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}


	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 스택 선언
		Stack<Tower> stack = new Stack<>();

		for(int i = 1; i <= N; i++) {
			int currentH = Integer.parseInt(st.nextToken());

			while(!stack.isEmpty()) {

				// 스택의 맨 위의 탑
				Tower top = stack.peek();

				// 만약 맨 위의 탑이 현재 탑보다 낮다면,
				if(top.height < currentH) {
					/*
					 * 이 탑은:
					 * - 현재 탑보다 낮고
					 *
					 * ⇒ 앞으로 어떤 탑이 와도
					 *    이 낮은 탑은 절대 레이저를 못 받음
					 *
					 * ⇒ 다시 볼 가치 없음 → 제거
					 */

					stack.pop(); // 해당 탑 스택에서 제거
				} else { // 나보다 높거나 같은 탑을 만나면
					break;
				}
			}

			/**
			 * 이제 상황은 둘 중 하나이다
			 * 왼쪽에 나보다 높은 탑이 아예 없거나
			 */

			if(stack.isEmpty()) {
				sb.append(0);

			} else {
				// 스택 맨 위가 왼쪽에서 가장 가까운 나보다 높은 탑
				sb.append(stack.peek().idx);
			}
			sb.append(" ");
			/*
			 * 현재 탑은
			 * 오른쪽 탑들에게는 "왼쪽 후보"가 될 수 있으므로
			 * 스택에 넣는다
			 */

			stack.push(new Tower(i, currentH));

		}

		System.out.println(sb.toString());
	}
}
