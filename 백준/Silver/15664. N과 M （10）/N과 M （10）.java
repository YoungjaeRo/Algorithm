import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 중복되는 입력 값이(prev로 검증) 있고, 조합이다(visited배열 필요없음), start가 대신 필요함
	 */
	static int N; // 숫자의 개수
	static int M; // 조합 개수

	static int[] nums;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		answer = new int[M];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순 정렬
		Arrays.sort(nums);

		backtrack(0, 0); // depth = 0, start = 0;

		System.out.println(sb);

	}

	public static void backtrack(int depth, int start){
		if(depth == M) {
			for(int a : answer) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
            return;
		}

		int prev = Integer.MIN_VALUE;

		// 조합 시작
		for(int i = start; i < N; i++) {
			if(prev == nums[i]) {
				continue; // 같은 depth의 중복제거
			}

			prev = nums[i];
			answer[depth] = nums[i];

			// 조합 중복 제거
			backtrack(depth + 1, i + 1);
		}
	}
}
