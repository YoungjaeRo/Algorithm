import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 해당 문제는, 순서가 중요한것이 아니기 때문에,
	 * 순열이 아닌 조합으로 해당 문제를 풀면 된다
	 *
	 * 독일 로또는 49개 숫자중 6개를 고른다
	 *
	 *
	 * 49 가지의 수 중, k개를 고르고,
	 *
	 * 뽑은 k의 개의 중 뽑은 숫자의 집합이 S
	 *
	 * 집합 S에서 고를 수 있는 경우의 수는?
	 *
	 */

	static int k; // 전체 숫자 개수
	static int[] nums; // 입력으로 주어진 숫자들
	static int[] pick; // 선택된 6가지 숫자

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			k = Integer.parseInt(st.nextToken());

			// 입력이 0이면 종료
			if(k == 0) {
				break;
			}

			nums = new int[k];
			pick = new int[6];

			for(int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0); // 조합 시작

			//각 케이스 이후에 줄바꿈
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	/**
	 *
	 * @param start : 다음에 선택할 수 있는 시작 인덱스
	 *              이전보다 뒤에서만 고르기 떄문에, 중복을 피할 수 있다
	 *
	 * @param depth : 현재까지 선택한 수의 개수
	 */

	static void dfs(int start, int depth) {
		if(depth == 6) {
			for(int num : pick) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i < nums.length; i++) {
			pick[depth] = nums[i]; // 현재 숫자를 선택
			dfs(i + 1, depth + 1);

		}
	}
}
