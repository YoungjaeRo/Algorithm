import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int C;

	static int[] homes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		homes = new int[N];

		for(int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}

		// 이분 탐색을 위한 정렬
		Arrays.sort(homes);

		int left = 1;
		int right = homes[N - 1] - homes[0];

		int ans = 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			int count = isPossible(mid);

			if(count >= C) {
				// 더 크게도 설치가 가능한지 판별하기
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(ans);
	}

	static int isPossible(int dist) {

		int last = homes[0];
		int count = 1;

		for(int i = 1; i < N; i++) {
			if(homes[i] - last >= dist) {
				count++;
				last = homes[i];
			}
		}

		return count;
	}
}
