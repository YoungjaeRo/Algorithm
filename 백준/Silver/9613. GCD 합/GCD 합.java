import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 정수 n개가 주어졌을때, 가능한 모든 쌍의 GCD의 합을 구하시오
	 * 조합 구히고, GCD 실행
	 */

	static int T;
	static int N;

	static int[] nums;
	static int[] pick;

	static long sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			nums = new int[N];
			pick = new int[2];
			sum = 0;

			// 숫자 정보 입력
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			backtrackGCD(0, 0); //depth = 0, start = 0;
			System.out.println(sum);
		}
	}

	static void backtrackGCD(int depth, int start) {
		if(depth == 2) {
			int a = pick[0];
			int b = pick[1];

			int result = GCD(a, b);
			sum = sum + result;
			return;
		}


		for(int i = start; i < nums.length; i++) {
			pick[depth] = nums[i];
			backtrackGCD(depth + 1, i + 1);
		}
	}


	static int GCD(int a, int b) {
		while(b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
}
