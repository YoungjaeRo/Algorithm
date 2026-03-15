import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] cookies;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		cookies = new int[N];

		st = new StringTokenizer(br.readLine());

		int MAX = Integer.MIN_VALUE;

		for(int i = 0; i < N; i++) {
			cookies[i] = Integer.parseInt(st.nextToken());
			if(cookies[i] > MAX) {
				MAX = cookies[i];
			}
		}

		// 나눠 줄 수 있는 과자의 길이는 양수이기 때문에
		long left = 1;
		long right = MAX;

		long answer = 0;

		while(left <= right) {
			long mid = (left + right) / 2;

			long count = 0 ;

			for(int cookie : cookies) {
				count = count + (cookie / mid);
			}

			// 조카들에게 충분히 과자를 나눠줄 수 있다면 더 큰값으로 시도해본다
			if(count >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		System.out.println(answer);

	}
}
