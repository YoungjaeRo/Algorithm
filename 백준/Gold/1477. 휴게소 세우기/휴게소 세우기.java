import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int L;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 기존 휴게소 개수
		M = Integer.parseInt(st.nextToken()); // 더 지을 휴게소 개수
		L = Integer.parseInt(st.nextToken()); // 고속도로 길이

		arr = new int[N + 2];

		arr[0] = 0;
		arr[N + 1] = L;

		if(N > 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(arr);

		int left = 1;
		int right = L;

		int answer = 0;

		while(left <= right) {
			// mid는 가능한 최대 구간 길이를 뜻한다.
			int mid = (left + right) / 2;
			int need = 0;

			// mid 이하로 구간을 만들기 위해 필요한 휴게소의 수 계산
			for(int i = 1; i < arr.length; i++) {
				int gap = arr[i] - arr[i - 1];
				need = need + (gap - 1) / mid;
			}

			// 필요 휴게소가 많다 -- > mid가 너무 작음
			if(need > M) {
				left = mid + 1;

			} else {
				answer = mid;
				right = mid - 1;
			}

		}

		System.out.println(answer);

	}
}
