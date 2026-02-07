import java.io.*;
import java.util.*;

public class Main {
	static int N; // 숫자 카드 개수

	static int M; // 정수 개수

	static StringBuilder sb = new StringBuilder();

	/**
	 * 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하시오
	 * 값의 여부를 물어보는 이분탐색이라서, while(left <= right)
	 *
	 *
	 */


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[] cards = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		// 이분 탐색을 위해서 정렬하기
		Arrays.sort(cards);

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			boolean result = binarySearch(cards, target);

			if(result) {
				sb.append("1").append(" ");
			} else {
				sb.append("0").append(" ");
			}
		}

		System.out.println(sb.toString());
	}

	// 이분 탐색 메서드
	static boolean binarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			if(arr[mid] == target) {
				return true;

			} else if(arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return false;
	}
}
