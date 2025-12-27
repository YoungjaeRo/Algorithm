import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 입력 배열 U를 정렬한다
	 * 모든 a + b 값을 twoSum에 넣는다
	 * twoSum을 정렬한다
	 *
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int [] U = new int[N];

		for(int i = 0; i < N; i++) {
			U[i] = Integer.parseInt(br.readLine());
		}

		// 1. U[i] 정렬하기, 나중에 d를 큰것부터 보기 위해서
		Arrays.sort(U);

		// 2. twoSum = 가능한 모든 (a + b) 값 저장
		int size = N * N;
		int[] twoSums = new int[size];

		int idx = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				twoSums[idx++] = U[i] + U[j];
			}
		}

		// 3. 이진 탐색을 위한 정렬
		Arrays.sort(twoSums);

		// 4) d를 큰 값부터 내려가면서 확인
		//    d = U[i]
		//    d - c 가 twoSums 안에 있으면 d = a+b+c 성립

		for(int i = N- 1; i >= 0; i--) { // d 후보를 큰것부터 탐색
			int d = U[i];

			// 모든 c에 대해 target = d - c
			for(int j = 0; j < N; j++) {
				int c = U[j];

				int target = d - c;

				if(binarySearch(twoSums, target)) {
					// 큰것부터 탐색했으니 해당 값이 가장 큰값
					System.out.println(d);
                    return;
				}
			}

		}
	}

	static boolean binarySearch(int[] sum, int target) {
		int left = 0;
		int right = sum.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			// 찾은 경우
			if(sum[mid] == target) {
				return true;
			}

			// target이 더 큰 경우
			if(sum[mid] < target) {
				left = mid + 1;
			}
			// target이 더 작은 경우
			else {
				right = mid - 1;
			}
		}
		
		// 끝까지 못찾았으면
		return false;
	}
}
