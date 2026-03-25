import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 연속된 수들이기 때문에, 백트래킹이 아니라, 투 포인터로 해당 문제를 풀어야한다.
	 */
	static int N;
	static int S;

	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;



		for(int right = 0; right < N; right++) {

			// 계속해서 오른쪽으로 늘려가며 누적합
			sum = sum + arr[right];

			// 더 짧은 길이도 가능한지 줄여보기
			while(sum >= S) {

				int length = right - left + 1;

				min = Math.min(min, length);

				sum = sum - arr[left];

				left++;
			}
			
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}

}
