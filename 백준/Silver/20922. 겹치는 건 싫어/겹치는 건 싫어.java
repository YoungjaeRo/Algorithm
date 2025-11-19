import java.util.*;
import java.io.*;

public class Main {

	static int N; // 숫자 개수
	static int K; // k개 이하의 원소만을 가지고 있어야함
	static int[] arr; // 실제 숫자 저장할 곳
	static int[] cnt; // 숫자의 개수 현황 저장할 곳

	static int max = Integer.MIN_VALUE;
	static int answer;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();


		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		cnt = new int[100001]; // 숫자 값이 인덱스

		st = new StringTokenizer(br.readLine());

		// 한쪽 배열에는 수열의 정보를, 하나의 배열에는 숫자의 빈도수를 저장해놓음
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 0번에 3을 집어 넣음
		}

		int left = 0;
		int right = 0;
		answer = 0;

		while(right < N) {
			int x = arr[right]; // 일단 배열에서 첫값을 꺼낸다.

			if(cnt[x] < K) {
				cnt[x]++;
				right++;

				answer = Math.max(answer, right - left);
			} else {
				// 더 이상 넣을수 없음
				int y = arr[left];
				cnt[y]--;
				left++;
			}
		}

		System.out.println(answer);

	}
}
