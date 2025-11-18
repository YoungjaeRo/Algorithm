import java.io.*;
import java.util.*;

public class Main {
	static int N; // 수의 개수
	static int[] nums; // 숫자들
	static int[] ops = new int[4]; // 쁠마곱나

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());

		// 숫자 값을 입력함 ㅇㅇ
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			ops[i] = Integer.parseInt(st.nextToken()); // + - * / 순서
		}
		// 연산자는 총 N-1개, 연산자를 기준으로 DFS 돈다고 생각
		// idx = 0부터 시작 (0번째 연산자 선택 중), current = nums[0]
		dfs(0, nums[0]);

		System.out.println(max);
		System.out.println(min);
	}

	static void dfs(int idx, int cur) {
		if(idx == N - 1) {
			max = Math.max(max, cur);
			min = Math.min(min, cur);
			return;
		}

		int next = nums[idx + 1];

		// 덧셈을 할경우
		if(ops[0] > 0) {
			ops[0]--;
			dfs(idx + 1, cur + next);

			ops[0]++; // 백트래킹

		}

		if(ops[1] > 0) {
			ops[1]--;
			dfs(idx + 1, cur - next);
			ops[1]++;

		}

		if(ops[2] > 0) {
			ops[2]--;
			dfs(idx + 1, cur * next);
			ops[2]++;
		}

		if(ops[3] > 0) {
			ops[3]--;
			int result;
			if(cur < 0) {
				result =  - (Math.abs(cur) / next);
			} else {
				result = cur / next;
			}

			dfs(idx + 1, result);
			ops[3]++;
		}
	}
}
