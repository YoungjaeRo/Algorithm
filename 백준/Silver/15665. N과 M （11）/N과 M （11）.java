import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 같은 수를 여러번 골라도된다, 순서가 중요하기 때문에, 수열 문제임
	 * 중복은 가능하기 때문에, visited배열은 필요가 없다
	 */

	static int N;
	static int M;

	static int[] numbers;
	static int[] answer;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		answer = new int[M];

		// 숫자 배열에 저장하기
		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		backtrack(0); // depth = 0

		System.out.println(sb.toString());

	}

	static void backtrack(int depth) {
		if(depth == M) {
			for(int a : answer) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
			return;
		}

		// 같은 depth에서 같은 값은 한번만 선택하게 만들기
		int prev = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			if(numbers[i] == prev) {
				continue;
			}

			prev = numbers[i];

			answer[depth] = numbers[i];
			backtrack(depth + 1);
		}
	}
}
