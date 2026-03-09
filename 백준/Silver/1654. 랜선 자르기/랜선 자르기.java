import java.io.*;
import java.util.*;


public class Main {

	/**
	 * 랜선 K개를 가지고, 같은 길이로 잘라서 최소N개 이상 만들 수 있을때
	 *
	 * 그 최대 길이를 구하시오
	 *
	 * 결과적으로 지금 구하려고 하는것은,
	 * 몇센치를 잘라서 모든 전선을 같은 길이로 만들고 싶은데,
	 * 그 자르는 길이를 최대로 하고 싶다는 뜻
	 *
	 * 결국 이분 탐색을 통한, 파라미터 서치이다
	 */

	static int K; // 랜선의 개수
	static int N; // 필요한 랜선의 개수


	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());

	K = Integer.parseInt(st.nextToken());
	N = Integer.parseInt(st.nextToken());

	long[] arr = new long[K];

	long maxLen = 0;
	for(int i = 0; i < K; i++) {
		arr[i] = Long.parseLong(br.readLine());

		if(arr[i] > maxLen) {
			maxLen = arr[i];
		}
	}


	// mid도 답이 될수 있기 때문에, left <= right 임

	long left = 1;
	long right = maxLen;

	long answer = 0;

	while(left <= right) {
		long mid = (left + right) / 2;

		long count = 0;

		for(int i = 0; i < K; i++) {
			count = count + (arr[i] / mid);
		}

		// 계속가 목표치 N개 이상이면, mid를 더 크게 해본다
		if(count >= N) {
			answer = mid;
			left = mid + 1;
		} else {
			right = mid - 1;
		}
	}

		System.out.println(answer);
	}
}
