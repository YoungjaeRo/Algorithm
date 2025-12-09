import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 종복 허용이므로, visited 배열은 필요없다.
	 * 수열, 근데 출력 예시를 보아하니, 순서는 중요하지 않아보인다-- > 조합문제임
	 * 수열은 비내림차순 --- > 오름차순이어야한다.
	 */

	static int N;
	static int M;

	static int[] arr;

	static int[] choice;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		choice = new int[M];

		st = new StringTokenizer(br.readLine());

		// 배열에 숫자 정보 입력하기
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순으로 정렬해야하기 떄문에, 정렬을 한번 해줘야 한다
		Arrays.sort(arr);

		backtrack(0, 0); // depth = 0, start = 0;

		System.out.println(sb.toString());

	}

	static void backtrack(int depth, int start) {
		if(depth == M) {
			for(int n : choice) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i < N; i++) {
			choice[depth] = arr[i];

			backtrack(depth + 1, i);
		}
	}
}
