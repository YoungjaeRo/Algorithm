import java.io.*;
import java.util.*;

public class Main {
	static int N; // 나무 개수

	static int M; // 상근이가 가져가야 할 나무의 길이

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] trees = new int[N];

		st = new StringTokenizer(br.readLine());

		int maxHeight = 0;

		for(int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if(trees[i] > maxHeight) {
				maxHeight = trees[i];
			}
		}

		int left = 0;
		int right = maxHeight;
		int ans = 0;

		// 특정 값을 찾는것이기 때문에 while(left <= right)

		while(left <= right) {
			int mid = (left + right) / 2;

			long sum = 0; // 나무를 mid 만큼 잘랐을때, 얻는 나무의 총합

			// tree 배열에 있는, 각 길이들을 순회하면서 자르고 남는 길이에 대한 누적합
			for(int h : trees) {
				if (h > mid) {
					sum = sum + (h - mid);
				}
			}

				// 최소 M길이 만큼 얻었을 경우,
			if(sum >= M) {
				ans = mid;
				left = mid + 1; // 더 긴 길이로 시도해보기
			} else {
				right = mid - 1;
				}
		}

		System.out.println(ans);
	}

}
