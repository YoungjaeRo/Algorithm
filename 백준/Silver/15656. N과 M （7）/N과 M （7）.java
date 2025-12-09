import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 같은 수를 여러번 골라도 되고, 길이가 M (depth == M)
	 * 중복을 허용하기 때문에, visited 배열은 필요가 없음
	 * 해당 문제는 수열 문제이기 때문에, start또한 필요없다.
	 * 
	 * 수열 (중복 허용) : visited 배열 필요없음, only depth만
	 * 수열 (중복 X) : visited 배열 필요하고, 다음 dfs 이후 꼭 원복을 시행해줘야한다
	 * 
	 * 조합 (중복허용) : depth와 start 인자가 필요함, start를 다음 dfs에서 증가시키지 않음
	 * 조합 (중복 비허용) : depth와 start 인자가 필요함, start를 다음 dfs에서 증가시킴
	 */
	static int N;
	static int M;

	static int[] arr;

	static int[] answer;

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

		backtrack(0); // depth = 0, 시작 인덱스 0

		System.out.println(sb.toString());
	}

	static void backtrack(int depth) {
		if(depth == M) {
			for(int i : answer) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 0; i < N; i++) {
			answer[depth] = arr[i];

			backtrack(depth + 1);
		}
	}

}
