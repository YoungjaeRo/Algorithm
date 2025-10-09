import java.io.*;
import java.util.*;


public class Main {

	static int N;  // 목표 상한값 (N 이하로 만들어야 함)
	static int[] digits;  // 사용할 수 있는 숫자들
	static int maxLen; // N의 자리수 (탐색 깊이 제한)
	static int answer = 0;  // 현재까지 만든 수 중 가장 최댓값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken());

		// 사용할 숫자 목록
		st = new StringTokenizer(br.readLine());
		digits = new int[K];

		for(int i = 0; i < K; i++) {
			digits[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(digits);

		// N의 자리수 (657 --> 3자리)
		maxLen = String.valueOf(N).length(); // 3

		// DFS 시작(현재값  = 0, 자리값도 0)
		DFS(0, 0);

		System.out.println(answer);

	}

	/**
	 * DFS: 가능한 모든 수를 만들어보기
	 *
	 * @param cur    지금까지 만든 수
	 * @param depth  현재까지 쌓은 자리수
	 */
	static void DFS(int cur, int depth) {
		// 더이상 자릿수를 늘릴수 없으면 종료
		if(depth == maxLen) {
			return; // 재귀 함수 종료
		}

		// 사용할 수 있는 모든 숫자들을 붙여본다
		for(int d : digits) {
			int next = 10 * cur + d; //  cur=5, d=7 → next=57

			if(next > N) {    // N을 초과하면 그 아래는 더 볼 필요 없음 (가지치기)
				continue;
			}

			// 유효한 수면, 현재 최댓값과 비교해서 갱신
			if(next > answer) {
				answer = next;
			}

			// 다음 자릿수로 더 붙이기
			DFS(next, depth + 1);

		}


	}
}
