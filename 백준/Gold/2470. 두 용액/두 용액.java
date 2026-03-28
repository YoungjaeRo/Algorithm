import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 서로 다른 두개의 용액이기 때문에, 이분탐색 보단, 투포인터를 활용해 문제를 해결한다.
	 */
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 투포인터 용 정렬해주기
		Arrays.sort(arr);

		// 서로 양끝에서 시작해야 올바른 탐색이 가능하다
		int left = 0;
		int right = N - 1;

		int answer1 = arr[left];
		int answer2 = arr[right];

		int min = Integer.MAX_VALUE;

		while(left < right) {

			int sum = arr[left] + arr[right];

			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				answer1 = arr[left];
				answer2 = arr[right];
			}

			// 검증 시작
			if(sum < 0) {
				left++;
			} else if(sum > 0) {
				right--;
			} else {
				// 합이 0이면 이미 답
				break;
			}


		}

		System.out.println(answer1 + " " + answer2);


	}
}
