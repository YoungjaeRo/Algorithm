import java.io.*;
import java.util.*;


public class Main {
	/**
	 * N과 M이 주어졌을때, 길이가 M인 수열을 모두 구하는 프로그램을 작성
	 * 중복해서 고르기 가능 --> visited 배열 불필요함
	 * 수열은 비 내림 차순 (오름차순) --> start? 를 사용해야하나
	 * 중복 불가 ㅇㅇ
	 */
	static int N;
	static int M;

	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		backtrack(0, 1); // depth는 0부터 시작, start는 1부터 ㅇㅇ

	}

	/**
	 *
	 * @param depth : 현재 채우고 있는 위치 0 ~ M-1
	 * @param start : 이번 위치에서 선택할 수 있는 수의 후보군
	 */
	static void backtrack(int depth, int start) {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}

		for(int i = start; i <= N; i++) {
			arr[depth] = i; // 현재 칸에 i를 넣고,
			backtrack(depth + 1, i);
		}
	}
}
