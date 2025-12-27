import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		/*
		 * tails[len] 의미:
		 * - 길이가 (len+1)인 증가 부분 수열을 만들 수 있을 때,
		 *   그 수열의 끝값(마지막 값)을 가능한 한 작게 유지한 대표값
		 *
		 * tails는 실제 LIS 수열이 아님!
		 * 길이 계산을 위해 "각 길이별로 끝값 최소"만 들고 있는 상태 배열임.
		 *
		 * 끝값이 작을수록 다음 숫자가 뒤에 붙을 가능성이 커져서 유리함.
		 */

		int[] tails = new int[N]; // 최대 N길이까지 가능

		int size = 0; // 현재까지 만든 LIS 길이

		for(int i = 0; i < N; i++) {
			int key = arr[i]; // 넣어볼 값

			// case 1) tails가 비었거나, 가장 긴 후보의 끝값보다 key가 크면
			//         -> 뒤에 붙여서 길이를 늘릴 수 있다

			if(size == 0 || tails[size -1] < key) {
				tails[size] = key;
				size++;
			}

			// case 2) 뒤에 못 붙이면
			//         -> tails에서 "key 이상(>= key)"이 처음 나오는 위치를 찾아 교체한다.
			//         -> 길이는 그대로지만 끝값을 더 작게 만들어 미래 확장 가능성을 높인다.

			else {
				int idx = lowerBound(tails, 0, size, key);
				tails[idx] = key;
			}

		}
        
        System.out.println(size);
	}


	static int lowerBound(int[] tails, int left, int right, int target) {
		while(left < right) {
			int mid = (left + right) / 2;

			if(tails[mid] < target) {
				left = mid + 1;
			} else {
				right = mid; // target 이상이면 더 왼쪽도 가능한지 확인
			}
		}

		return left;
	}
}
