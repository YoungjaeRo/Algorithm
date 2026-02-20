import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 특정 수 a보다 작은것들을 찾기 위해선, 이분탐색으로 a 이상이 처음 나오는 지점을 찾으면
	 * 쉽게 풀 수 있다
	 */

	static int T;

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	T = Integer.parseInt(br.readLine());

	while(T-- > 0 ) {
		StringTokenizer st = new StringTokenizer(br.readLine());

		// A의 개수
		int N = Integer.parseInt(st.nextToken());

		int[] arrA = new int[N];


		// B의 개수
		int M = Integer.parseInt(st.nextToken());

		int[] arrB = new int[M];

		// A의 크기 정보 입력받기
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}


		// B의 크기 정보 입력받기
		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}


		// 이중 탐색을 할 B를 먼저 정렬해주기
		Arrays.sort(arrB);

		// A보다 작은 B의 원소들을 찾기(lowerBound)
		int ans = 0;

		for(int a : arrA) {
			ans = ans + lowerBound(arrB, a); // A보다 작은 숫자의 개수 구하기
		}

		System.out.println(ans);
		}
	;
	}

	static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;

		while(left < right) {
			int mid = (left + right) / 2;

			if(target <= arr[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}
}
