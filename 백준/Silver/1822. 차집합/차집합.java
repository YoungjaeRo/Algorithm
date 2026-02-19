import java.io.*;
import java.util.*;

public class Main {
	/**
	 * 숫자의 범위가 너무 크기 때문에, 이분 탐색을 통해 답을 찾아내는게 더 빠름ㅇㅇ
	 */
	static int A;
	static int B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		int[] numberA = new int[A];
		int[] numberB = new int[B];

		st = new StringTokenizer(br.readLine());

		// 각 집합별로 숫자 정보 입력받기
		for(int i = 0; i < A; i++) {
			numberA[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		for(int i = 0; i < B; i++) {
			numberB[i] = Integer.parseInt(st.nextToken());
		}

		// 이분 탐색을 위한 정렬 먼저 시행
		Arrays.sort(numberA);
		Arrays.sort(numberB);

		List<Integer> answer = new ArrayList<>();

		// A에도 있고, B에도 있는거 거르기
		for(int target : numberA) {

			int left = 0;

			int right = numberB.length - 1;

			boolean found = false; // B에서 target을 찾았는지

			// 특정 값을 찾는 이분탐색이기 때문에, while(left <= right)

			while(left <= right) {

				int mid = (left + right) / 2;

				if(numberB[mid] == target) {
					found = true;
					break;

				} else if(numberB[mid] < target) {
					left = mid + 1;

				} else {
					right = mid - 1;
				}
			}

			// 못찾았으면, 답에 추가
			if(!found) {
				answer.add(target);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(answer.size()).append("\n");
		
		for(int a : answer) {
			sb.append(a).append(" ");
		}

		System.out.println(sb.toString());
	}
}
