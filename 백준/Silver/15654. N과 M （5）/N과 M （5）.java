import java.io.*;
import java.util.*;

public class Main {
	static int N; // 숫자의 개수
	static int M; // 그중 골라야 할 개수

	static int[] arr; // 입력 숫자들
	static boolean[] visited; // 해당 숫자들을 썼는지 체크

	static int[] combination; // 만들게 될 수열

	/**
	 * 중복해서, 선택이 불가하기 때문에, visited 배열이 필수임
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		visited = new boolean[N];
		combination = new int[M];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 일단 오름차순으로 정렬하기


		backtrack(0); // depth = 0

	}

	static void backtrack(int depth) {
		// M개를 다 골랐다면 ㅇㅇ

		if(depth == M) {
			for(int i : combination) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}

		// 아직 M개를 다 고르지 못했다면,
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				//이미 사용한 숫자는 건너뜀
				continue;
			}

			visited[i] = true;
			combination[depth] = arr[i]; // 현재 depth에 값 주입

			backtrack(depth + 1); // 재귀함수를 실행

			visited[i] = false; // 원복
		}
	}
}
