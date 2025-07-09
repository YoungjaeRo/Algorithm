import java.io.*;
import java.util.*;


public class Main {
	/**
	 * 1부터 N까지 숫자 중에서  중복없이 M개를 뽑는 모든 순열
	 */


	static int N, M;

	static boolean[] visited; // 방문 체크 배열

	static int[] result; // 정답 출력 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		N = sc.nextInt();
		M = sc.nextInt();

		visited = new boolean[N + 1];
		result = new int[M];

		backTrack(0);

	}

	static void backTrack(int depth) {
		if(depth == M) {
			// 수열이 조건에 부합하게 꽉차면 출력
			for(int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}

			System.out.println();
			return;

		}

		// 1부터 N까지 모든 수를 하나씩 시도한다
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) { // 아직 사용되지 않은 수라면
				visited[i] = true; // 사용되었다고 처리
				result[depth] = i; // 현재 위치에 수를 저장

				backTrack(depth + 1);// 다음 위치를 채우기 위해 depth + 1로 백트래킹 재귀호출
				visited[i] = false; // 매우 중요 : 재귀 함수가 다 끝이나, 돌아온 후에는 숫자 사용처리 원상복구 (백트래킹 핵심)
			}
		}
	}



}
