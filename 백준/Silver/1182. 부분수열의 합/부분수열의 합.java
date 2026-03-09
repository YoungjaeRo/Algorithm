import java.io.*;
import java.util.*;

public class Main {

	static int N; // 정수의 개수
	static int S; // 합

	static int[] nums;

	static int count = 0; // 조건을 만족하는 수열의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i= 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		backtrack(0, 0);

		// 공집합 제거
		if(S == 0) count--;
		
		
		System.out.println(count);
	}

	// idx를 통해 수열을 다 돌면서, 어떠한 수들을 선택할것인지 정한다.
	static void backtrack(int idx, int sum) {

		if(idx == N) {
			if(sum == S) {
				count++;
			}
			return;
		}

		// 1. 해당 수를 선택할 경우
		backtrack(idx + 1, sum + nums[idx]);

		// 2.  해당 수를 선택하지 않을시
		backtrack(idx + 1, sum);
	}
}
