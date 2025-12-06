import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;

	static int[] arr;

	static int[] combi;

	/**
	 * 조합 문제이기 때문에, visited 배열이 필요가 없음 ㅇㅇ
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		combi = new int[M]; // M개를 뽑을것이기 때문에

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 오름차순 정렬해줌
		Arrays.sort(arr);

		backtrack(0, 0);
	}

	static void backtrack(int depth, int start) {
		if(depth == M) {
			for(int n : combi) {
				System.out.print(n + " ");
			}
			System.out.println();
			return;
		}

		for(int i = start; i < N; i++) {
			combi[depth] = arr[i];
			backtrack(depth + 1, i + 1);
		}
	}
}
