import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int S;

	static int[] numbers;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		numbers = new int[N];

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		// 슬라이딩 윈도우 시작
		int left = 0;
		int right = 0;
		int sum = 0;

		while(true) {
			if(sum >= S) {
				int len = right - left;
				answer = Math.min(answer, len);
				sum = sum - numbers[left];
				left++;

			} else {
				if(right == N) {
					break;
				}
				sum = sum + numbers[right];
				right++;
			}
		}
		if(answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}
}
