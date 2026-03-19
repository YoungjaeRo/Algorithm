import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[] nums;

	static int[] pick;
	static boolean[] visited;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		visited = new boolean[N];


		pick = new int[M];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순으로 정렬하기
		Arrays.sort(nums);

		backtrack(0);

		System.out.println(sb);

	}

	static void backtrack(int depth) {

		if(depth == M) {
			for(int p : pick) {
				sb.append(p).append(" ");
			}
			sb.append("\n");

			return;
		}

		for(int i = 0; i < N; i++) {
			// 수열 + 방문을 허용하지 않기 때문에, visited 배열은 필수이다
			if(!visited[i]) {
				visited[i] = true;

				pick[depth] = nums[i];

				backtrack(depth + 1);

				// 원복을 꼭 해줘야 한다
				visited[i] = false;
			}
		}
	}
}
