import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 일단 순서가 주요하게 작용하므로, 해당 알고리즘은 수열로 해결한다.
	 * 중복이 허용되지 않기 때문에, visited 배열이 필요함
	 *
	 */

	static int N;
	static int M;
	static int[] arr;
	static int[] answer;

	static boolean[] visited;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		answer = new int[M];

		visited = new boolean[N];


		st = new StringTokenizer(br.readLine());

		// 배열에 값 입력하기
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		//사전 순으로 출력해야되므로, 오름차순으로 정렬해준다
		Arrays.sort(arr);

		backtrack(0); // depth = 0부터 시작

		System.out.println(sb);
	}

	static void backtrack(int depth) {
		if (depth == M) {
			for (int num : answer) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		int prev = Integer.MIN_VALUE; // 1

		for(int i = 0; i < N; i++) {
			if(visited[i]) {  // [1, 1, 2]
				continue;
			}

			if(arr[i] == prev) {
				continue;
			}

			visited[i] = true;
			prev = arr[i];

			answer[depth] = arr[i];

			backtrack(depth + 1);

			visited[i] = false;
		}
	}
}
