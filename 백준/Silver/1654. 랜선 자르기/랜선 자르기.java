import java.io.*;
import java.util.*;


public class Main {

	/**
	 * 랜선 K개를 가지고, 같은 길이로 잘라서 최소N개 이상 만들 수 있을때
	 *
	 * 그 최대 길이를 구하시오
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int k = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
		int N = Integer.parseInt(st.nextToken()); // 만들어야 하는 최소 랜선의 개수

		long[] arr = new long[k];

		long maxLen = 0; // 입력 랜선중 가장 긴 길이 (이분 탐색의 upperbound)

		for(int i = 0; i < k; i++) {
			arr[i] = Long.parseLong(br.readLine());

			if(arr[i] > maxLen) {
				maxLen = arr[i];
			}
		}

		// 이분 탐색 범위 : 길이는 0보다 큰 값, 즉 1부터 시작해야함
		long left = 1;
		long right = maxLen;

		// 조건을 만족하는 길이 중 최대값
		long answer = 0;

		while(left <= right) {

			long mid = (left + right) / 2;

			// mid 길이로 잘랐을때 총 몇개가 나오는지 계산하기
			long cnt = 0;

			for(int i = 0; i < k; i++) {
				cnt = cnt + (arr[i] / mid);
			}

			if (cnt >= N) {
				// N개 이상 만들 수 있으면, mid 는 가능
				answer = mid;
				left = mid + 1;
				
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(answer);
	}
}
