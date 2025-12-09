import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 같은 수를 여러번 골라도 되고, 길이가 M (depth == M)
	 * 순서가 중요한 수열문제이기 때문에, visited 배열이 필요함
	 * 중복해서 선택이 가능한 수열인데, 과연 visited가 필요하나,,,
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

		st = new StringTokenizer(br.readLine());

		//  배열 값 입력 받기
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 사전순으로 증가하는 순서로 출력해야하기 때문에, 오름차순 정렬을 해준다
		Arrays.sort(arr);

		backtrack(0, 0); // depth = 0, 시작 인덱스 0

		System.out.println(sb.toString());
	}

	static void backtrack(int depth, int start) {
		if(depth == M) {
			for(int i : answer) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = start; i < N; i++) {
			answer[depth] = arr[i];

			backtrack(depth + 1, start);
		}
	}

}
