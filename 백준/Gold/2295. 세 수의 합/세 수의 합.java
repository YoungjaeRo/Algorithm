import java.io.*;
import java.util.*;

public class Main {

	/**
	 * a + b + c = d (U안에 d도 포함되어 있음)
	 * a + b = d - c
	 *
	 * 결국 a,b 두 수의 합에서 c를 뺀 값이 target이고, 해당 d가 그러한 값이 있는지를 판별하면 되는 문제이다.
	 */

	static int N; // 숫자의 개수

	static int[] arr; // 숫자 저장할 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 오름차순 정렬해주기
		Arrays.sort(arr);

		// 두 수의 합을 저장해놓을 리스트
		ArrayList<Integer> twoSum = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				twoSum.add(arr[i] + arr[j]);
			}
		}

		// 이분 탐색을 위한 정렬
		Collections.sort(twoSum);

		// 큰 수부터 보면서, 이분 탐색 시작하기
		for(int i = N - 1; i >= 0; i--) {
			int d = arr[i];

			for(int j = 0; j < N; j++) {
				int target = d - arr[j];

				if(binarySearch(twoSum,target)) {
					System.out.println(d);
					return;
				}
			}
		}
	}

	static boolean binarySearch(List<Integer> list, int target) {
		int left = 0;
		int right = list.size() - 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			int value = list.get(mid);

			if(value == target) {
				return true; // 찾음
			} else if(value < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		// 그래도 못찾았으면 false
		return false;
	}
}
