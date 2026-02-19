import java.io.*;
import java.util.*;

public class Main {
	/**
	 *  홍익이는 조카들에게 최대한 긴 과자를 나누어주려고 한다
	 *
	 * 	하지만, 나눠준 과자의 길이가 하나라도 다르면, 싸움이 난다
	 * 	그래서 모든 조카들에게 같은 길이의 과자를 나눠줘야 한다
	 *
	 *
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 조카 수
		int N = Integer.parseInt(st.nextToken()); // 과자의 수

		int[] cookies = new int[N];

		st = new StringTokenizer(br.readLine());

		int maxLen = 0;

		for (int i = 0; i < N; i++) {
			cookies[i] = Integer.parseInt(st.nextToken());

			if (maxLen < cookies[i]) {
				maxLen = cookies[i];
			}
		}

		//  이분탐색으로 mid 값으로 구하려는 것 : 줄 수 있는 최대한 길이
		int left = 1;
		int right = maxLen;

		int ans = 0;

		while(left <= right) {

			long cnt = 0;

			int mid = (left + right) / 2;

			for(int x : cookies) {
				cnt = cnt + (x / mid);
			}

			if(cnt >= M) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(ans);
	}
}
