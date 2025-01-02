import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);


		// 배열의 크기 N
		int N = sc.nextInt();

		// K번째 숫자
		int k = sc.nextInt();

		// 이진 탐색 범위 초기화
		long start = 1;
		long end = k;

		// 결과 값 저장 (k 번쨰 숫자를 저장할 변수)
		long answer = 0;

		/**
		 * K번째로 작은 숫자를 찾기 위해서 이진 탐색을 사용, mid 값을 기준으로 mid 이하의 숫자가 몇개인지 계산하여 K와 비교한다.
		 *  그리고 A 배열의 각 행마다, mid 이하의 숫자가 몇개인지 세는 방법이 필요하다
		 *  각각의 i 행에서 나오는 숫자는 i, 2i, 3i ,,,,Ni 이다.
		 */


		// 이진탐색 수행하기
		while(start <= end) {
			long mid = (start + end) / 2; // 중간값 계산

			// mid 이하의 숫자 개수 0으로 일단 초기화
			long count = 0;

			// 중앙값보다 작은 수는 몇개인지 계산하기
			for(int i = 1; i <= N; i++) {
				count = count + Math.min(mid / i, N);

			}

			if(count < k) {
				start = mid + 1;

			} else {
				answer = mid;
				end = mid - 1;

			}

		}
		System.out.println(answer);

	}
}

